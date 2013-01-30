package outag.formats.real.utils;

import java.io.RandomAccessFile;

// TODO: Check which class correct

/** Variant 1 */
public class DataPacket {
//	 word   Packet version (0 or 1 in available samples)
//	 word   Packet size
//	 word   Stream number
//	 dword  Timestamp (in ms)
//	 byte   Unknown
//	 byte   Flags (bitfield, see below)
//	#if version == 1
//	 byte   Unknown
//	#endif
//	 byte[]  Stream-specific data
	
	byte [] packetData;
	int streamID;
	long timestamp;
	/**<ol start="0">
	 * 	<li>bit : reliable packet (refers to network transmission method)</li>
    	<li>bit : keyframe</li> 
	 * </ol> */
	byte flags;
	
	public DataPacket(RandomAccessFile f) throws Exception {
		int packetVersion = f.readInt(); // 0 or 1
		packetData = new byte[f.readInt()];
		streamID = f.readInt();
		timestamp = f.readLong();
		f.read(); // unknow byte
		flags = f.readByte();
		if (packetVersion == 1)
			f.read(); // unknow byte
		f.readFully(packetData);
	}
}

/** Alternative variant (official documentation) */
class DataPacket2 {
//	 word   Packet version
//	 word   Packet size
//	 word   Stream number
//	 dword  Timestamp
//	#if version == 0
//	 byte   Packet group
//	 byte   Flags
//	#endif
//	#if version == 1
//	 word   ASM rule
//	 byte   ASM flags
//	#endif
//	 byte[]  Stream-specific data
	
	byte [] packetData;
	int streamID;
	long timestamp;
	byte flags;
	byte packetGroup;
	int ASM_rule;
	
	public DataPacket2(RandomAccessFile f) throws Exception {
		int packetVersion = f.readInt(); // 0 or 1
		packetData = new byte[f.readInt()];
		streamID = f.readInt();
		timestamp = f.readLong();
		
		if (packetVersion == 0)
			packetGroup = f.readByte();
		else
			ASM_rule = f.readInt();
		flags = f.readByte();

		f.readFully(packetData);
	}	
}