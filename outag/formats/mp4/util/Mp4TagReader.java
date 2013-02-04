package outag.formats.mp4.util;

import java.io.IOException;
import java.io.RandomAccessFile;

import outag.formats.Tag;
import outag.formats.exceptions.CannotReadException;
import outag.formats.mp4.Mp4Tag;

/**
 * Reads metadata from mp4,
 * <p/>
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
 * </pre
 */
public class Mp4TagReader {
	public Tag read(RandomAccessFile raf) throws CannotReadException, IOException {
		Mp4Tag tag = new Mp4Tag();
		

		return tag;
	} 
}


///*
// * Tree a descendre:
// * moov
// *  udta
// *   meta
// *    ilst
// *     [
// *      xxxx <-  id info
// *       data
// *      ]
// */
//public Mp4Tag read( RandomAccessFile raf ) throws CannotReadException, IOException {
//    Mp4Tag tag = new Mp4Tag();
//    
////    Mp4Box box = new Mp4Box();
////    byte[] b = new byte[4];
////    
////    //Get to the facts
////    //1-Searching for "moov"
////    seek(raf, box, "moov");
////    
////    //2-Searching for "udta"
////    seek(raf, box, "udta");
////    
////    //3-Searching for "meta"
////    seek(raf, box, "meta");
////    
////    //4-skip the meta flags
////    raf.read(b);
////    if(b[0] != 0)
////        throw new CannotReadException();
////    
////    //5-Seek the "ilst"
////    seek(raf, box, "ilst");
////    int length = box.getOffset() - 8;
////    
////    int read = 0;
////    while(read < length) {
////        b = new byte[8];
////        raf.read(b);
////        box.update(b);
////                       
////        int fieldLength = box.getOffset() - 8;
////        b = new byte[fieldLength];
////        raf.read(b);
////        
////        tag.add(createMp4Field(box.getId(), b));
////        read += 8+fieldLength;
////    }
////    
////    System.out.println(tag);
//    return tag;
//}
//
////private void seek(RandomAccessFile raf, Mp4Box box, String id) throws IOException {
////    byte[] b = new byte[8];
////    raf.read(b);
////    box.update(b);
////    while(!box.getId().equals(id)) {
////        raf.skipBytes(box.getOffset()-8);
////        raf.read(b);
////        box.update(b);
////    }
////}
//}