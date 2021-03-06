package outag.formats.mp4.util.box;

import outag.file_presentation.JBBuffer;

/** Mp4aBox ( sample (frame encoding) description box) <br>
 * At first glance appears to hold no of channels but actually always returns 2 even for mono recordings
 * so just need to skip over data in order to get to child atom esds <br>
 * <p>4 bytes version/flags = byte hex version + 24-bit hex flags (current = 0) <p/>
 * -> 6 bytes reserved = 48-bit value set to zero
 * -> 2 bytes data reference index
 * = short unsigned index from 'dref' box
 * -> 2 bytes QUICKTIME audio encoding version = short hex version
 * - default = 0 ; audio data size before decompression = 1
 * -> 2 bytes QUICKTIME audio encoding revision level
 * = byte hex version
 * - default = 0 ; video can revise this value
 * -> 4 bytes QUICKTIME audio encoding vendor
 * = long ASCII text string
 * - default = 0
 * -> 2 bytes audio channels = short unsigned count
 * (mono = 1 ; stereo = 2)
 * -> 2 bytes audio sample size = short unsigned value
 * (8 or 16)
 * -> 2 bytes QUICKTIME audio compression id = short integer value
 * - default = 0
 * -> 2 bytes QUICKTIME audio packet size = short value set to zero
 * -> 4 bytes audio sample rate = long unsigned fixed point rate
 */
public class Mp4Mp4aBox {
    public static final int RESERVED_POS = 0;
    public static final int REFERENCE_INDEX_POS = 6;
    public static final int AUDIO_ENCODING_POS = 8;
    public static final int AUDIO_REVISION_POS = 10;
    public static final int AUDIO_ENCODING_VENDOR_POS = 12;
    public static final int CHANNELS_POS = 16;
    public static final int AUDIO_SAMPLE_SIZE_POS = 18;
    public static final int AUDIO_COMPRESSION_ID_POS = 20;
    public static final int AUDIO_PACKET_SIZE_POS = 22;
    public static final int AUDIO_SAMPLE_RATE_POS = 24;

    public static final int RESERVED_LENGTH = 6;
    public static final int REFERENCE_INDEX_LENGTH = 2;
    public static final int AUDIO_ENCODING_LENGTH = 2;
    public static final int AUDIO_REVISION_LENGTH = 2;
    public static final int AUDIO_ENCODING_VENDOR_LENGTH = 4;
    public static final int CHANNELS_LENGTH = 2;
    public static final int AUDIO_SAMPLE_SIZE_LENGTH = 2;
    public static final int AUDIO_COMPRESSION_ID_LENGTH = 2;
    public static final int AUDIO_PACKET_SIZE_LENGTH = 2;
    public static final int AUDIO_SAMPLE_RATE_LENGTH = 4;

    public static final int TOTAL_LENGTH = RESERVED_LENGTH + REFERENCE_INDEX_LENGTH + AUDIO_ENCODING_LENGTH + AUDIO_REVISION_LENGTH + AUDIO_ENCODING_VENDOR_LENGTH + CHANNELS_LENGTH + AUDIO_SAMPLE_SIZE_LENGTH + AUDIO_COMPRESSION_ID_LENGTH + AUDIO_PACKET_SIZE_LENGTH + AUDIO_SAMPLE_RATE_LENGTH;

    public Mp4Mp4aBox(JBBuffer data) { data.skip(TOTAL_LENGTH); }
}