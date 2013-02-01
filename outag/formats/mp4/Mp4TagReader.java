package outag.formats.mp4;

import outag.formats.exceptions.CannotReadException;
import outag.formats.generic.Utils;
import outag.formats.mp4.Mp4AtomIdentifier;
import outag.formats.mp4.utils.Mp4FieldKey;
import outag.formats.mp4.utils.Mp4NonStandardFieldKey;
import outag.formats.mp4.utils.Mp4Tag;
import outag.formats.mp4.utils.atom.Mp4DataBox;
import outag.formats.mp4.utils.field.*;

import outag.formats.mp4.atom.Mp4BoxHeader;
import outag.formats.mp4.atom.Mp4MetaBox;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/** Reads metadata from mp4<br>
 * <p>The metadata tags are usually held under the ilst atom as shown below<p/>
 * <p>Valid Exceptions to the rule:</p>
 * <p>Can be no udta atom with meta rooted immediately under moov instead<p/>
 * <p>Can be no udta/meta atom at all<p/>
 *
 * <pre>
 * |--- ftyp
 * |--- moov
 * |......|
 * |......|----- mvdh
 * |......|----- trak
 * |......|----- udta
 * |..............|
 * |..............|-- meta
 * |....................|
 * |....................|-- hdlr
 * |....................|-- ilst
 * |.........................|
 * |.........................|---- @nam (Optional for each metadatafield)
 * |.........................|.......|-- data
 * |.........................|....... ecetera
 * |.........................|---- ---- (Optional for reverse dns field)
 * |.................................|-- mean
 * |.................................|-- name
 * |.................................|-- data
 * |.................................... ecetere
 * |
 * |--- mdat
 * </pre> */
public class Mp4TagReader {

    /* The metadata is stored in the box under the hierachy moov.udta.meta.ilst
     * There are gaps between these boxes */
    public Mp4Tag read(RandomAccessFile raf) throws CannotReadException, IOException {
        Mp4Tag tag = new Mp4Tag();

        //Get to the facts everything we are interested in is within the moov box, so just load data from file
        //once so no more file I/O needed
        Mp4BoxHeader moovHeader = Mp4BoxHeader.seekWithinLevel(raf, Mp4AtomIdentifier.MOOV.getFieldName());
        if (moovHeader == null)
            throw new CannotReadException("Not find mp4 chunks");

        ByteBuffer moovBuffer = ByteBuffer.allocate(moovHeader.getLength() - Mp4BoxHeader.HEADER_LENGTH);
        raf.getChannel().read(moovBuffer);
        moovBuffer.rewind();

        //Level 2-Searching for "udta" within "moov"
        Mp4BoxHeader boxHeader = Mp4BoxHeader.seekWithinLevel(moovBuffer, Mp4AtomIdentifier.UDTA.getFieldName());
        if (boxHeader != null) {
            //Level 3-Searching for "meta" within udta
            boxHeader = Mp4BoxHeader.seekWithinLevel(moovBuffer, Mp4AtomIdentifier.META.getFieldName());
            if (boxHeader == null)
                return tag;

            Mp4MetaBox meta = new Mp4MetaBox(boxHeader, moovBuffer);
            meta.processData();

            //Level 4- Search for "ilst" within meta
            boxHeader = Mp4BoxHeader.seekWithinLevel(moovBuffer, Mp4AtomIdentifier.ILST.getFieldName());
             //This file does not actually contain a tag
            if (boxHeader == null)
                return tag;
        } else {
            //Level 2-Searching for "meta" not within udta
            boxHeader = Mp4BoxHeader.seekWithinLevel(moovBuffer, Mp4AtomIdentifier.META.getFieldName());
            if (boxHeader == null)
                return tag;

            Mp4MetaBox meta = new Mp4MetaBox(boxHeader, moovBuffer);
            meta.processData();

            //Level 3- Search for "ilst" within meta
            boxHeader = Mp4BoxHeader.seekWithinLevel(moovBuffer, Mp4AtomIdentifier.ILST.getFieldName());
            //This file does not actually contain a tag
            if (boxHeader == null)
                return tag;
        }

        //Size of metadata (exclude the size of the ilst parentHeader), take a slice starting at
        //metadata children to make things safer
        int length = boxHeader.getLength() - Mp4BoxHeader.HEADER_LENGTH;
        ByteBuffer metadataBuffer = moovBuffer.slice();
        //Datalength is longer are there boxes after ilst at this level?
        int read = 0;
        while (read < length) {
            //Read the boxHeader
            boxHeader.update(metadataBuffer);

            //Create the corresponding datafield from the id, and slice the buffer so position of main buffer wont get affected
            createMp4Field(tag, boxHeader, metadataBuffer.slice());

            //Move position in buffer to the start of the next parentHeader
            metadataBuffer.position(metadataBuffer.position() + boxHeader.getDataLength());
            read += boxHeader.getLength();
        }
        return tag;
    }

    /** Process the field and add to the tag <br>
     * Note:In the case of coverart MP4 holds all the coverart within individual data items all within
     * a single covr atom, we will add separate mp4field for each image.
     * @param tag
     * @param header
     * @param raw
     * @throws UnsupportedEncodingException */
    private void createMp4Field(Mp4Tag tag, Mp4BoxHeader header, ByteBuffer raw) throws UnsupportedEncodingException {
        //Reverse Dns Atom
        if (header.getId().equals(Mp4TagReverseDnsField.IDENTIFIER)) {
        	try {
                TagField field = new Mp4TagReverseDnsField(header, raw);
                tag.addField(field);
            }
            catch (Exception e) {
                TagField field = new Mp4TagRawBinaryField(header, raw);
                tag.addField(field);
            }
        }
        //Normal Parent with Data atom
        else {
            int currentPos = raw.position();
            boolean isDataIdentifier = Utils.getString(raw, Mp4BoxHeader.IDENTIFIER_POS, Mp4BoxHeader.IDENTIFIER_LENGTH, "ISO-8859-1").equals(Mp4DataBox.IDENTIFIER);
            raw.position(currentPos);
            if (isDataIdentifier) {
                //Need this to decide what type of Field to create
                int type = Utils.getIntBE(raw, Mp4DataBox.TYPE_POS_INCLUDING_HEADER, Mp4DataBox.TYPE_POS_INCLUDING_HEADER + Mp4DataBox.TYPE_LENGTH - 1);
                Mp4FieldType fieldType = Mp4FieldType.getFieldType(type);
                logger.config("Box Type id:" + header.getId() + ":type:" + fieldType);

                //Special handling for some specific identifiers otherwise just base on class id
                if (header.getId().equals(Mp4FieldKey.TRACK.getFieldName()))
                {
                    TagField field = new Mp4TrackField(header.getId(), raw);
                    tag.addField(field);
                }
                else if (header.getId().equals(Mp4FieldKey.DISCNUMBER.getFieldName()))
                {
                    TagField field = new Mp4DiscNoField(header.getId(), raw);
                    tag.addField(field);
                }
                else if (header.getId().equals(Mp4FieldKey.GENRE.getFieldName()))
                {
                    TagField field = new Mp4GenreField(header.getId(), raw);
                    tag.addField(field);
                }
                else if (header.getId().equals(Mp4FieldKey.ARTWORK.getFieldName()) || Mp4FieldType.isCoverArtType(fieldType))
                {
                    int processedDataSize = 0;
                    int imageCount = 0;
                    //The loop should run for each image (each data atom)
                    while (processedDataSize < header.getDataLength())
                    {
                        //There maybe a mixture of PNG and JPEG images so have to check type
                        //for each subimage (if there are more than one image)
                        if (imageCount > 0)
                        {
                            type = Utils.getIntBE(raw, processedDataSize + Mp4DataBox.TYPE_POS_INCLUDING_HEADER,
                                    processedDataSize + Mp4DataBox.TYPE_POS_INCLUDING_HEADER + Mp4DataBox.TYPE_LENGTH - 1);
                            fieldType = Mp4FieldType.getFieldType(type);
                        }
                        Mp4TagCoverField field = new Mp4TagCoverField(raw,fieldType);
                        tag.addField(field);
                        processedDataSize += field.getDataAndHeaderSize();
                        imageCount++;
                    }
                }
                else if (fieldType == Mp4FieldType.TEXT)
                {
                    TagField field = new Mp4TagTextField(header.getId(), raw);
                    tag.addField(field);
                }
                else if (fieldType == Mp4FieldType.IMPLICIT)
                {
                    TagField field = new Mp4TagTextNumberField(header.getId(), raw);
                    tag.addField(field);
                }
                else if (fieldType == Mp4FieldType.INTEGER)
                {
                    TagField field = new Mp4TagByteField(header.getId(), raw);
                    tag.addField(field);
                }
                else
                {
                    boolean existingId = false;
                    for (Mp4FieldKey key : Mp4FieldKey.values())
                    {
                        if (key.getFieldName().equals(header.getId()))
                        {
                            //The parentHeader is a known id but its field type is not one of the expected types so
                            //this field is invalid. i.e I received a file with the TMPO set to 15 (Oxf) when it should
                            //be 21 (ox15) so looks like somebody got their decimal and hex numbering confused
                            //So in this case best to ignore this field and just write a warning
                            existingId = true;
                            logger.warning("Known Field:" + header.getId() + " with invalid field type of:" + type + " is ignored");
                            break;
                        }
                    }

                    //Unknown field id with unknown type so just create as binary
                    if (!existingId)
                    {
                        logger.warning("UnKnown Field:" + header.getId() + " with invalid field type of:" + type + " created as binary");
                        TagField field = new Mp4TagBinaryField(header.getId(), raw);
                        tag.addField(field);
                    }
                }
            }
            //Special Cases
            else
            {
                //MediaMonkey 3 CoverArt Attributes field, does not have data items so just
                //copy parent and child as is without modification
                if (header.getId().equals(Mp4NonStandardFieldKey.AAPR.getFieldName()))
                {
                    TagField field = new Mp4TagRawBinaryField(header, raw);
                    tag.addField(field);
                }
                //Default case
                else
                {
                    TagField field = new Mp4TagRawBinaryField(header, raw);
                    tag.addField(field);
                }
            }
        }

    }
}
