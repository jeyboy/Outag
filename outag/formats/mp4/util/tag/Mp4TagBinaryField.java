package outag.formats.mp4.util.tag;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import outag.file_presentation.JBBuffer;
import outag.formats.mp4.util.box.Mp4Box;

/**
 * Represents binary data
 * <p/>
 * <p>Subclassed by cover art field,
 * TODO unaware of any other binary fields at the moment
 */
public class Mp4TagBinaryField extends Mp4TagField
{
    protected int dataSize;
    protected byte[] dataBytes;
    protected boolean isBinary = false;

    /**
     * Construct an empty Binary Field
     *
     * @param id
     */
    public Mp4TagBinaryField(Mp4Box head)
    {
        super(head);
    }

    /**
     * Construct new binary field with binarydata provided
     *
     * @param id
     * @param data
     * @throws UnsupportedEncodingException
     */
    public Mp4TagBinaryField(String id, byte[] data)
    {
        super(id);
        this.dataBytes = data;
    }

    /**
     * Construct binary field from rawdata of audio file
     *
     * @param id
     * @param raw
     * @throws UnsupportedEncodingException
     */
    public Mp4TagBinaryField(String id, ByteBuffer raw) throws UnsupportedEncodingException
    {
        super(id, raw);
    }

    public Mp4FieldType getFieldType()
    {
        //TODO dont know what value this should be do we actually have any binary fields other
        //than cover art
        return Mp4FieldType.IMPLICIT;
    }

    /**
     * Used when creating raw content
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    protected byte[] getDataBytes() throws UnsupportedEncodingException
    {
        return dataBytes;
    }

    protected void build(ByteBuffer raw)
    {
        Mp4BoxHeader header = new Mp4BoxHeader(raw);
        dataSize = header.getDataLength();

        //Skip the version and length fields
        raw.position(raw.position() + Mp4DataBox.PRE_DATA_LENGTH);

        //Read the raw data into byte array
        this.dataBytes = new byte[dataSize - Mp4DataBox.PRE_DATA_LENGTH];
        for (int i = 0; i < dataBytes.length; i++)
        {
            this.dataBytes[i] = raw.get();
        }

        //After returning buffers position will be after the end of this atom
    }

    public boolean isBinary()
    {
        return isBinary;
    }

    public boolean isEmpty()
    {
        return this.dataBytes.length == 0;
    }

    public int getDataSize()
    {
        return dataSize;

    }

    public byte[] getData()
    {
        return this.dataBytes;
    }

    public void setData(byte[] d)
    {
        this.dataBytes = d;
    }

    public void copyContent(TagField field)
    {
        if (field instanceof Mp4TagBinaryField)
        {
            this.dataBytes = ((Mp4TagBinaryField) field).getData();
            this.isBinary = field.isBinary();
        }
    }
}
