package outag.formats.mp4.util;

import java.io.RandomAccessFile;
import outag.file_presentation.JBBuffer;
import outag.file_presentation.JBFile;
import outag.formats.Tag;
import outag.formats.mp4.Mp4Tag;
import outag.formats.mp4.util.box.Mp4Box;
import outag.formats.mp4.util.box.Mp4BoxIdentifier;
import outag.formats.mp4.util.box.Mp4MetaBox;

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
	public Tag read(RandomAccessFile raf) throws Exception {
		Mp4Tag tag = new Mp4Tag();
        Mp4Box box = new Mp4Box();
        JBFile file = new JBFile(raf);
        
        box.find(file, true, Mp4BoxIdentifier.MOOV.getName());       
        JBBuffer moovBuffer = file.Buffer(box.getLength());		
		       
        if (box.find(moovBuffer, false, Mp4BoxIdentifier.UDTA.getName(), Mp4BoxIdentifier.META.getName())) {
        	switch(box.getId()) {
        		case "udta" :
        			box.find(moovBuffer, true, Mp4BoxIdentifier.META.getName());
        			new Mp4MetaBox(moovBuffer);       			
        			box.find(moovBuffer, true, Mp4BoxIdentifier.ILST.getName());
        			
        			break;
        		case "meta" :
        			new Mp4MetaBox(moovBuffer);
        			box.find(moovBuffer, true, Mp4BoxIdentifier.ILST.getName());        			
        			
        			break;
        	}
        	
        	while(box.find(moovBuffer, false)) {
        		tag.add(createMp4Field(box.getId(), b));
        	}
        }
        else return tag;

//
//        //Size of metadata (exclude the size of the ilst parentHeader), take a slice starting at
//        //metadata children to make things safer
//        int length = boxHeader.getLength() - Mp4BoxHeader.HEADER_LENGTH;
//        ByteBuffer metadataBuffer = moovBuffer.slice();
//        //Datalength is longer are there boxes after ilst at this level?
//
//        int read = 0;
//        while (read < length)
//        {
//            //Read the boxHeader
//            boxHeader.update(metadataBuffer);
//
//            //Create the corresponding datafield from the id, and slice the buffer so position of main buffer
//            //wont get affected
//            createMp4Field(tag, boxHeader, metadataBuffer.slice());
//
//            //Move position in buffer to the start of the next parentHeader
//            metadataBuffer.position(metadataBuffer.position() + boxHeader.getDataLength());
//            read += boxHeader.getLength();
//        }
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