package outag.formats.real.utils;

import java.io.RandomAccessFile;

import outag.formats.exceptions.InvalidSequenceException;
import outag.formats.exceptions.UnsupportedException;
import outag.formats.real.RealTag;

public class AudioInfo {
//	 byte[4]  Header signature ('.', 'r', 'a', 0xfd)
//	 word     Version (3, 4 or 5)
//	 
//	#if version == 3
//	 word     Header size, not including first 8 bytes
//	 byte[10] Unknown
//	 dword    Data size
//	 byte     Title string length
//	 byte[]   Title string
//	 byte     Author string length
//	 byte[]   Author string
//	 byte     Copyright string length
//	 byte[]   Copyright string
//	 byte     Comment string length
//	 byte[]   Comment string
//	 byte     Unknown *
//	 byte     Fourcc string length (always 4) *
//	 byte[]   Fourcc string (always "lpcJ") *
//	#elseif version == 4 or version == 5
//	 word     Unused (always 0)
//	 byte[4]  ra signature (".ra4" or ".ra5", depending on version)
//	 dword    Unknown (maybe data size)
//	 word     Version2 (always equal to version)
//	 dword    Header size
//	 word     Codec flavor
//	 dword    Coded frame size
//	 byte[12] Unknown
//	 word     Sub packet h
//	 word     Frame size
//	 word     Subpacket size
//	 word     Unknown
//	 #if version == 5
//	 	byte[6]  Unknown
//	 #endif
//	 word     Samplerate
//	 word     Unknown
//	 word     Sample size
//	 word     Channels
//	 #if version == 4
//	  byte     Interleaver ID string length (always 4)
//	  byte[]   Interleaver ID string
//	  byte     FourCC string length (always 4)
//	  byte[]   FourCC string
//	 #endif
//	 #if version == 5
//	  dword    Interleaver ID
//	  dword    FourCC
//	 #endif
//	 byte[3]  Unknown
//	 #if version == 5
//	  byte     Unknown
//	 #endif
	
//	 #if version == 4
//	 byte     Title string length
//	 byte[]   Title string
//	 byte     Author string length
//	 byte[]   Author string
//	 byte     Copyright string length
//	 byte[]   Copyright string
//	 byte     Comment string length
//	 byte[]   Comment string
//	 #endif	
	
//	 dword    Codec extradata length
//	 byte[]   Codec extradata
//	#endif	
	
	/** possible values : 3,4,5 */
	int version;
	RealTag tag = null;
	CodecInfo codecInfo = null;
	
	public AudioInfo(RandomAccessFile f, int blockLength) throws Exception {
		byte [] buffer;
		
		buffer = new byte[4]; f.read(buffer);
//		buffer equals ['.', 'r', 'a', 0xfd]
		
		version = f.readInt(); //word     Version (3, 4 or 5)
		if (version < 3 || version > 5) throw new UnsupportedException("Wrong or unsupported version");

		if (version == 3) {
			int headerSize = f.readInt(); // not including first 8 bytes
			buffer = new byte[10]; f.read(buffer); //	 byte[10] Unknown
			long dataSize = f.readLong(); // Data size
			
			tag = new RealTag();
			
			buffer = new byte[f.read()]; f.read(buffer);
			tag.addTitle(new String(buffer));
			
			buffer = new byte[f.read()]; f.read(buffer);
			tag.addArtist(new String(buffer));			

			buffer = new byte[f.read()]; f.read(buffer);
			tag.setCopyright(new String(buffer));			

			buffer = new byte[f.read()]; f.read(buffer);
			tag.addComment(new String(buffer));			

			f.read(); //    Unknown *
			buffer = new byte[f.read()]; f.read(buffer);
			if (!new String(buffer).equals("lpcJ"))
				throw new InvalidSequenceException("AudioInfo sequence corrupted"); 
		} else {
			codecInfo = new CodecInfo();
			
			f.readInt(); // Unused (always 0)
			buffer = new byte[4]; f.read(buffer);
//		 	byte[4]  ra signature (".ra4" or ".ra5", depending on version)
			
			long dataSize = f.readLong(); // possible data size - 0x27
			f.readInt(); //  Version2 (always equal to version)
			f.readLong();//  Header size - 16
			
			codecInfo.setcodecFlavor(f.readInt());
			codecInfo.setcodecFrameSize(f.readLong());
			buffer = new byte[12]; f.read(buffer); //Unknow

			codecInfo.setsubpacketH(f.readInt());
			codecInfo.setframeSize(f.readInt());
			codecInfo.setsubpacketSize(f.readInt());
			
			f.readInt(); //   Unknown
			
			if (version == 5) {
				buffer = new byte[6]; f.read(buffer); //Unknow
			}
				
			codecInfo.setsampleRate(f.readInt());
			f.readInt(); // Unknow
			codecInfo.setsampleSize(f.readInt());
			codecInfo.setchannels(f.readInt());

			if (version == 4) {
				buffer = new byte[f.read()]; f.read(buffer); //Interleaver ID string // always 4 bytes
				buffer = new byte[f.read()]; f.read(buffer); //FourCC string // always 4 bytes
			} else {
				f.readLong(); // Interleaver ID / maybe need convert value to string 
				f.readLong(); // FourCC // FourCC is four character constant / maybe need convert value to string 
			}
			
			buffer = new byte[3]; f.read(buffer); //Unknow
			if (version == 5)
				f.read(); //Unknow

//TODO: Check exist of next block for version 4 (maybe also include in 5 version)			
			if (version == 4) {
				tag = new RealTag();
				
				buffer = new byte[f.read()]; f.read(buffer);
				tag.addTitle(new String(buffer));
				
				buffer = new byte[f.read()]; f.read(buffer);
				tag.addArtist(new String(buffer));			

				buffer = new byte[f.read()]; f.read(buffer);
				tag.setCopyright(new String(buffer));			

				buffer = new byte[f.read()]; f.read(buffer);
				tag.addComment(new String(buffer));						
			}

//TODO: Check exist of next inclusions (maybe only for 5 version) 			 			
			buffer = new byte[(int)f.readLong()]; f.read(buffer);
			codecInfo.setcodecExtraData(buffer);
		}			
	}
}