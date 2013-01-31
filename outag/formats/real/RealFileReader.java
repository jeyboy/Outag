package outag.formats.real;

import outag.formats.exceptions.UnsupportedException;
import outag.formats.generic.AudioFileReader;
import outag.formats.generic.GenericTag;
import outag.formats.real.io.ContentDescriptionChunk;
import outag.formats.real.io.DataChunk;
import outag.formats.real.io.FileHeaderChunk;
import outag.formats.real.io.FilePropertiesChunk;
import outag.formats.real.io.GenericChunk;
import outag.formats.real.io.IndexChunk;
import outag.formats.real.io.MediaPropertiesChunk;
import outag.formats.EncodingInfo;
import outag.formats.Tag;

import java.io.RandomAccessFile;

/** Real Media File Format: Major Chunks: .RMF PROP MDPR CONT DATA INDX */
public class RealFileReader extends AudioFileReader {
    protected EncodingInfo getEncodingInfo(RandomAccessFile raf) throws Exception {
        EncodingInfo rv = new EncodingInfo();
        GenericChunk chunk;
        boolean cycle = true;
        long fileSize = raf.getChannel().size();
        
        while(cycle) {
        	if (fileSize == raf.getFilePointer()) break;
        	chunk = new GenericChunk(raf);
        	
        	switch(chunk.obj_id) {
        		case ".RMF":
        			FileHeaderChunk fhc = new FileHeaderChunk(chunk.data);
        			break;
        		case "PROP":
        			FilePropertiesChunk fpc = new FilePropertiesChunk(chunk.data);
        			break;
        		case "MDPR": //TODO: finish logical-structure element and finish tests
        			MediaPropertiesChunk mpc = new MediaPropertiesChunk(chunk.data);
        			break;
        		case "CONT":
        			ContentDescriptionChunk cdc = new ContentDescriptionChunk(chunk.data);
        			break;
        		case "DATA":
        			DataChunk dc = new DataChunk(chunk.data);
//        			cycle = false; 
        			break;
        		case "INDX":
        			IndexChunk ic = new IndexChunk(chunk.data);
//        			cycle = false; 
        			break;
        		default: throw new UnsupportedException("Wrong chunk type : " + chunk.obj_id);
        	}
        }

        
//        int y = 0;
//        final DataInputStream dis = prop.getDataInputStream();
//        final int objVersion = Utils.readUint16(dis);
//        if (objVersion == 0)
//        {
//            final long maxBitRate = Utils.readUint32(dis) / 1000;
//            final long avgBitRate = Utils.readUint32(dis) / 1000;
//            final long maxPacketSize = Utils.readUint32(dis);
//            final long avgPacketSize = Utils.readUint32(dis);
//            final long packetCnt = Utils.readUint32(dis);
//            final int duration = Utils.readUint32AsInt(dis) / 1000;
//            final long preroll = Utils.readUint32(dis);
//            final long indexOffset = Utils.readUint32(dis);
//            final long dataOffset = Utils.readUint32(dis);
//            final int numStreams = Utils.readUint16(dis);
//            final int flags = Utils.readUint16(dis);
//            rv.setBitrate((int) avgBitRate);
//            rv.setLength(duration);
//            rv.setVbr(maxBitRate != avgBitRate);
//        }
        return rv;
    }
//
//    private RealChunk findPropChunk(RandomAccessFile raf) throws IOException, CannotReadException {
//        final RealChunk rmf = RealChunk.readChunk(raf);
//        final RealChunk prop = RealChunk.readChunk(raf);
//        return prop;
//    }

//    private RealChunk findContChunk(RandomAccessFile raf) throws IOException, CannotReadException {
//        final RealChunk rmf = RealChunk.readChunk(raf);
//        final RealChunk prop = RealChunk.readChunk(raf);
//        RealChunk rv = RealChunk.readChunk(raf);
//        while (!rv.isCONT())
//			rv = RealChunk.readChunk(raf);
//        return rv;
//    }

    protected Tag getTag(RandomAccessFile raf) throws Exception {
//        final RealChunk cont = findContChunk(raf);
//        final DataInputStream dis = cont.getDataInputStream();
//        final String title = Utils.readString(dis, Utils.readUint16(dis));
//        final String author = Utils.readString(dis, Utils.readUint16(dis));
//        final String copyright = Utils.readString(dis, Utils.readUint16(dis));
//        final String comment = Utils.readString(dis, Utils.readUint16(dis));
        final GenericTag rv = new GenericTag();
//        // NOTE: frequently these fields are off-by-one, thus the crazy
//        // logic below...
//        try {
//        	rv.addArtist(title.length() == 0 ? copyright : author);
//        	rv.addComment(comment);
//        	rv.addTitle((title.length() == 0 ? author : title));
//        }
//        catch(Exception fdie) { throw new RuntimeException(fdie); }
        return rv;
    }
}