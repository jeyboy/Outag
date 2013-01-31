package outag.formats.real.utils;

import java.io.DataInputStream;

// TODO: Check which class correct ( At this moment no one realization is not correct)

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
	
	public byte [] packetData;
	public short streamID;
	public int timestamp;
	/**<ol start="0">
	 * 	<li>bit : reliable packet (refers to network transmission method)</li>
    	<li>bit : keyframe</li> 
	 * </ol> */
	public byte flags;
	
	public DataPacket(DataInputStream f) throws Exception {
		short packetVersion = f.readShort(); // 0 or 1
		if (packetVersion < 0 || packetVersion > 1) return; // Stream done if packet version not equal 0 or 1
		
		packetData = new byte[f.readShort()];
		streamID = f.readShort();
		timestamp = f.readInt();
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
	
	public byte [] packetData;
	public short streamID;
	public int timestamp;
	public byte flags;
	public byte packetGroup;
	public short ASM_rule;
	
	public DataPacket2(DataInputStream f) throws Exception {
		short packetVersion = f.readShort(); // 0 or 1
		if (packetVersion < 0 || packetVersion > 1) return; // Stream done if packet version not equal 0 or 1 
		
		packetData = new byte[f.readShort()];
		streamID = f.readShort();
		timestamp = f.readInt();
		
		if (packetVersion == 0)
			packetGroup = f.readByte();
		else
			ASM_rule = f.readShort();
		flags = f.readByte();

		f.readFully(packetData);
	}	
}