package outag.formats.real;

import outag.formats.exceptions.CannotReadException;
import outag.formats.generic.AudioFileReader;
import outag.formats.generic.GenericTag;
import outag.formats.generic.Utils;
import outag.formats.EncodingInfo;
import outag.formats.Tag;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/** TODO Check and clean this peace of s... */
/** Real Media File Format: Major Chunks: .RMF PROP MDPR CONT DATA INDX 
 * Warning : Not checked*/
public class RealFileReader extends AudioFileReader {
    protected EncodingInfo getEncodingInfo(RandomAccessFile raf) throws CannotReadException, IOException {
        final EncodingInfo rv = new EncodingInfo();
//        final RealChunk prop = findPropChunk(raf);
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

    protected Tag getTag(RandomAccessFile raf) throws CannotReadException, IOException {
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