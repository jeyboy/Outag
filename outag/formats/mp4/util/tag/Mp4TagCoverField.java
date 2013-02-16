package outag.formats.mp4.util.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import outag.file_presentation.JBBuffer;
import outag.reference.ImageFormats;

/** Represents Cover Art
 * <br>Note:Within this library we have a separate TagCoverField for every image stored, however this does not map
 * very directly to how they are physically stored within a file, because all are stored under a single covr atom, so
 * a more complex conversion has to be done then for other fields when writing multiple images back to file. */
public class Mp4TagCoverField extends Mp4TagBinaryField {
    private Mp4FieldType imageType;

    //Contains the size of each atom including header, required because may only have data atom or
    //may have data and name atom
    private int dataAndHeaderSize;

//    /** Empty CoverArt Field */
//    public Mp4TagCoverField() { super(Mp4FieldKey.ARTWORK.getFieldName()); }

    /** @return data and header size */
    public int getDataAndHeaderSize() { return dataAndHeaderSize; }

    /** Construct CoverField by reading data from audio file
     * @param raw
     * @param imageType
     * @throws IOException */
    public Mp4TagCoverField(JBBuffer raw, Mp4FieldType imageType) throws IOException {
        super(Mp4FieldKey.ARTWORK.getFieldName(), raw);
        this.imageType = imageType;
//        if(!Mp4FieldType.isCoverArtType(imageType)) {
//            logger.warning(ErrorMessage.MP4_IMAGE_FORMAT_IS_NOT_TO_EXPECTED_TYPE.getMsg(imageType));
//        }
    }

    /** Construct new cover art with binary data provided
     * identifies the imageType by looking at the data
     * @param data
     * @throws UnsupportedEncodingException */
    public Mp4TagCoverField(byte[] data) {
        super(Mp4FieldKey.ARTWORK.getFieldName(), data);

        //Read signature
        if (ImageFormats.binaryDataIsPngFormat(data))
            imageType = Mp4FieldType.COVERART_PNG;
        else if (ImageFormats.binaryDataIsJpgFormat(data))
            imageType = Mp4FieldType.COVERART_JPEG;
        else if (ImageFormats.binaryDataIsGifFormat(data))
            imageType = Mp4FieldType.COVERART_GIF;
        else if (ImageFormats.binaryDataIsBmpFormat(data))
            imageType = Mp4FieldType.COVERART_BMP;
        else
            imageType = Mp4FieldType.COVERART_PNG;
    }

  
    /** Return field type, for artwork this also identifies the image type
     * @return field type */
    public Mp4FieldType getFieldType() { return imageType; }

    public boolean isBinary() { return true; }

    public String toString() {
        return imageType +":" + dataBytes.length + "bytes";
    }

    protected void build(JBBuffer raw) {
        Mp4BoxHeader header = new Mp4BoxHeader(raw);
        dataSize = header.getDataLength();
        dataAndHeaderSize = header.getLength();

        //Skip the version and length fields
        raw.position(raw.position() + Mp4DataBox.PRE_DATA_LENGTH);

        //Read the raw data into byte array
        this.dataBytes = new byte[dataSize - Mp4DataBox.PRE_DATA_LENGTH];
        raw.get(dataBytes, 0, dataBytes.length);

        //Is there room for another atom (remember actually passed all the data so unless Covr is last atom
        //there will be room even though more likely to be for the text top level atom)
        int positionAfterDataAtom = raw.position();
        if (raw.position() + Mp4BoxHeader.HEADER_LENGTH <= raw.limit()) {
            //Is there a following name field (not the norm)
            Mp4BoxHeader nameHeader = new Mp4BoxHeader(raw);
            if (nameHeader.getId().equals(Mp4NameBox.IDENTIFIER))
            {
                dataSize += nameHeader.getDataLength();
                dataAndHeaderSize += nameHeader.getLength();
            }
            else
                raw.position(positionAfterDataAtom);
        }

        //After returning buffers position will be after the end of this atom
    }

    /** @param imageType
     * @return the corresponding mime type */
    public static String getMimeTypeForImageType(Mp4FieldType imageType) {
    	switch(imageType) {
    		case COVERART_PNG: return ImageFormats.MIME_TYPE_PNG;
    		case COVERART_JPEG: return ImageFormats.MIME_TYPE_JPEG;
    		case COVERART_GIF: return ImageFormats.MIME_TYPE_GIF;
    		case COVERART_BMP: return ImageFormats.MIME_TYPE_BMP;
    		default: return null;
    	}
    }
}