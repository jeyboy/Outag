package outag.formats.mp4.util.tag;

import outag.file_presentation.JBBuffer;
import outag.formats.mp4.util.box.Mp4Box;
import outag.formats.mp4.util.box.Mp4DataBox;
import outag.reference.GenreTypes;

/** Represents the Genre field , when user has selected from the set list of genres<br>
 * This class allows you to retrieve either the internal genre id, or the display value */
public class Mp4GenreField extends Mp4TagTextNumberField {
    public Mp4GenreField(Mp4Box head, JBBuffer data) throws Exception {
        super(head, data);
    }

//    /** Precheck to see if the value is a valid genre or whether you should use a custom genre.
//     * @param genreId */
//    public static boolean isValidGenre(String genreId) {
//        //Is it an id (within old id3 range)      
//        try {
//            short genreVal = Short.parseShort(genreId);
//            if ((genreVal - 1) <= GenreTypes.getMaxStandardGenreId())
//                return true;
//        }
//        catch (NumberFormatException nfe) {/* Maybe string presentation */ }
//
//        //Is it the String value ?
//        Integer id3GenreId = GenreTypes.getCodeByName(genreId);
//        if (id3GenreId != null) {
//            if (id3GenreId <= GenreTypes.getMaxStandardGenreId())
//                return true;
//        }
//        return false;
//    }
//
//    /** Construct genre, if cant find match just default to first genre
//     * @param genreId key into ID3v1 list (offset by one) or String value in ID3list */
//    public Mp4GenreField(String genreId) {
//        super(Mp4FieldKey.GENRE.getFieldName(), genreId);
//
//        //Is it an id
//        try {
//            short genreVal = Short.parseShort(genreId);
//            if ((genreVal - 1) <= GenreTypes.getMaxStandardGenreId()) {
//                numbers = new ArrayList<Short>();
//                numbers.add(genreVal);
//                return;
//            }
//            //Default
//            numbers = new ArrayList<Short>();
//            numbers.add((short) (1));
//            return;
//        }
//        catch (NumberFormatException nfe) { /* Do Nothing test as String instead */ }
//
//        //Is it the String value ?
//        Integer id3GenreId = GenreTypes.getCodeByName(genreId);
//        if (id3GenreId != null) {
//            if (id3GenreId <= GenreTypes.getMaxStandardGenreId()) {
//                numbers = new ArrayList<Short>();
//                numbers.add((short) (id3GenreId + 1));
//                return;
//            }
//        }
//        numbers = new ArrayList<Short>();
//        numbers.add((short) (1));
//    }

    protected void build(Mp4Box head, JBBuffer data) throws Exception {
        //Data actually contains a 'Data' Box so process data using this
    	Mp4Box header = Mp4Box.init(data, false);
        Mp4DataBox databox = new Mp4DataBox(header, data);
        dataSize = header.getLength();
        numbers = databox.getNumbers();

        int genreId = numbers.get(0);
        //Get value, we have to adjust index by one because iTunes labels from one instead of zero
        content = GenreTypes.getNameByCode(genreId - 1);

        //Some apps set genre to invalid value, we don't disguise this by setting content to empty string we leave
        //as null so apps can handle if they wish, but we do display a warning to make them aware.
//        if (content == null) warning
    }
}
