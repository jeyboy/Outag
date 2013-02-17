package outag.formats.mp4.util.tag;

import java.util.ArrayList;

import outag.file_presentation.JBBuffer;
import outag.formats.mp4.util.box.Mp4Box;
import outag.formats.mp4.util.box.Mp4DataBox;

/** Represents the Track No field <br>
 * <p>There are a number of reserved fields making matters more complicated
 * Reserved:2 bytes
 * Track Number:2 bytes
 * No of Tracks:2 bytes (or zero if not known)
 * PlayListTitleReserved: 1 byte
 * playtitlenameReserved:0 bytes</p> */
public class Mp4TrackField extends Mp4TagTextNumberField {
    private static final int TRACK_NO_INDEX = 1;
    private static final int TRACK_TOTAL_INDEX = 2;

    /** Create new Track Field parsing the String for the trackno/total
     * @param trackValue
     * @throws Exception 
     * @throws org.jaudiotagger.tag.FieldDataInvalidException */
    public Mp4TrackField(String trackValue) throws Exception {
        super(Mp4FieldKey.TRACK.getFieldName(), trackValue);

        numbers = new ArrayList<Short>();
        numbers.add(new Short("0"));

        String values[] = trackValue.split("/");
        switch (values.length) {
            case 1:
                try { numbers.add(Short.parseShort(values[0])); }
                catch (NumberFormatException nfe) {
                    throw new Exception("Value of:" + values[0] + " is invalid for field:" + id);
                }
                numbers.add(new Short("0"));
                numbers.add(new Short("0"));
                break;

            case 2:
                try { numbers.add(Short.parseShort(values[0])); }
                catch (NumberFormatException nfe) {
                    throw new Exception("Value of:" + values[0] + " is invalid for field:" + id);
                }
                try { numbers.add(Short.parseShort(values[1])); }
                catch (NumberFormatException nfe) {
                    throw new Exception("Value of:" + values[1] + " is invalid for field:" + id);
                }
                numbers.add(new Short("0"));
                break;

            default:
                throw new Exception("Value is invalid for field:" + id);
        }
    }


    /** Create new Track Field with only track No
     * @param trackNo */
    public Mp4TrackField(int trackNo) {	this(trackNo, 0); }

    /** Create new Track Field with track No and total tracks
     * @param trackNo
     * @param total */
    public Mp4TrackField(int trackNo, int total) {
        super(Mp4FieldKey.TRACK.getFieldName(), String.valueOf(trackNo));
        numbers = new ArrayList<Short>();
        numbers.add(new Short("0"));
        numbers.add((short) trackNo);
        numbers.add((short) total);
        numbers.add(new Short("0"));
    }

    /** Construct from file data
     * @param id
     * @param data
     * @throws Exception */
    public Mp4TrackField(String id, JBBuffer data) throws Exception {
        super(id, data);
    }


    protected void build(JBBuffer data) throws Exception {
        //Data actually contains a 'Data' Box so process data using this
    	Mp4Box header = Mp4Box.init(data, false);
        Mp4DataBox databox = new Mp4DataBox(header, data);
        dataSize = header.getLength();
        numbers = databox.getNumbers();
        //Track number always hold three values, we can discard the first one, the second one is the track no
        //and the third is the total no of tracks so only use if not zero
        StringBuffer sb = new StringBuffer();
        if(numbers!=null) {
            if ((numbers.size() > TRACK_NO_INDEX) && (numbers.get(TRACK_NO_INDEX) > 0))
                sb.append(numbers.get(TRACK_NO_INDEX));
            
            if ((numbers.size() > TRACK_TOTAL_INDEX) && (numbers.get(TRACK_TOTAL_INDEX) > 0))
                sb.append("/").append(numbers.get(TRACK_TOTAL_INDEX));
        }
        content = sb.toString();
    }

    public Short getTrackNo() { return numbers.get(TRACK_NO_INDEX); }

    public Short getTrackTotal() {
        if(numbers.size()<=TRACK_TOTAL_INDEX)
            return 0;

        return numbers.get(TRACK_TOTAL_INDEX);
    }

     /** Set Track No
     * @param trackNo */
    public void setTrackNo(int trackNo) {
        numbers.set(TRACK_NO_INDEX, (short) trackNo);
    }

    /** Set total number of tracks
     * @param trackTotal */
    public void setTrackTotal(int trackTotal) {
       numbers.set(TRACK_TOTAL_INDEX, (short) trackTotal);
    }
}