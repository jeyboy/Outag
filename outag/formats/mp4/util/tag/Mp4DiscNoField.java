package outag.formats.mp4.util.tag;

import outag.file_presentation.JBBuffer;
import outag.formats.mp4.util.box.Mp4Box;
import outag.formats.mp4.util.box.Mp4DataBox;

/** Represents the Disc No field<br>
 * Contains some reserved fields that we currently ignore
 * <br>Reserved:2 bytes
 * <br>Disc Number:2 bytes
 * <br>Total no of Discs:2 bytes */
public class Mp4DiscNoField extends Mp4TagTextNumberField {
    private static final int DISC_NO_INDEX = 1;
    private static final int DISC_TOTAL_INDEX = 2;

//    /** Create new Disc Field parsing the String for the discno/total
//     * @param discValue
//     * @throws Exception 
//     * @throws org.jaudiotagger.tag.FieldDataInvalidException */
//    public Mp4DiscNoField(String discValue) throws Exception {
//        super(Mp4FieldKey.DISCNUMBER.getFieldName(), discValue);
//
//        numbers = new ArrayList<Short>();
//        numbers.add(new Short("0"));
//
//        String values[] = discValue.split("/");
//        switch (values.length) {
//            case 1:
//                try { numbers.add(Short.parseShort(values[0])); }
//                catch (NumberFormatException nfe) {
//                    throw new Exception("Value of:" + values[0] + " is invalid for field:" + getId());
//                }
//                numbers.add(new Short("0"));
//                break;
//            case 2:
//                try { numbers.add(Short.parseShort(values[0])); }
//                catch (NumberFormatException nfe) {
//                    throw new Exception("Value of:" + values[0] + " is invalid for field:" + getId());
//                }
//                try { numbers.add(Short.parseShort(values[1])); }
//                catch (NumberFormatException nfe) {
//                    throw new Exception("Value of:" + values[1] + " is invalid for field:" + getId());
//                }
//                break;
//            default:
//                throw new Exception("Value is invalid for field:" + getId());
//        }
//    }
//
//
//    /** Create new Disc No field with only discNo
//     * @param discNo */
//    public Mp4DiscNoField(int discNo) {	this(discNo, 0); }
//
//    /** Create new Disc No Field with Disc No and total number of discs
//     * @param discNo
//     * @param total */
//    public Mp4DiscNoField(int discNo, int total) {
//        super(Mp4FieldKey.DISCNUMBER.getFieldName(), String.valueOf(discNo));
//        numbers = new ArrayList<Short>();
//        numbers.add(new Short("0"));
//        numbers.add((short) discNo);
//        numbers.add((short) total);
//    }

    public Mp4DiscNoField(Mp4Box head, JBBuffer data) throws Exception {
        super(head, data);
    }

    protected void build(JBBuffer data) throws Exception {
        //Data actually contains a 'Data' Box so process data using this
    	Mp4Box header = Mp4Box.init(data, false);
        Mp4DataBox databox = new Mp4DataBox(header, data);
        dataSize = header.getLength();
        numbers = databox.getNumbers();

        //Disc number always hold four values, we can discard the first one and last one, the second one is the disc no
        //and the third is the total no of discs so only use if not zero
        StringBuffer sb = new StringBuffer();
        if ((numbers.size() > DISC_NO_INDEX) && (numbers.get(DISC_NO_INDEX) > 0))
            sb.append(numbers.get(DISC_NO_INDEX));
        
        if ((numbers.size() > DISC_TOTAL_INDEX) && (numbers.get(DISC_TOTAL_INDEX) > 0))
            sb.append("/").append(numbers.get(DISC_TOTAL_INDEX));

        content = sb.toString();
    }

    public Short getDiscNo() { return numbers.get(DISC_NO_INDEX); }

    /** Set Disc No
     * @param discNo */
    public void setDiscNo(int discNo) {
        numbers.set(DISC_NO_INDEX, (short) discNo);
    }

    public Short getDiscTotal() {
        if(numbers.size() <= DISC_TOTAL_INDEX)
            return 0;

        return numbers.get(DISC_TOTAL_INDEX);
    }

    /** Set total number of discs
     * @param discTotal */
    public void setDiscTotal(int discTotal) {
        numbers.set(DISC_TOTAL_INDEX, (short) discTotal);
    }
}