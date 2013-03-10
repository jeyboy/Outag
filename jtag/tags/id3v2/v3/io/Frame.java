package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;

public class Frame {
	public int length;
	public boolean tag_preservation; //tells the software what to do with this frame if it is unknown and the tag is altered in any way. This applies to all kinds of alterations, including adding more padding and reordering the frames. 
	public boolean file_preservation; // tells the software what to do with this frame if it is unknown and the file, excluding the tag, is altered. This does not apply when the audio is completely replaced with other audio data. 
	public boolean read_only;
	
	public boolean compressed; // using [#ZLIB zlib] with 4 bytes for 'decompressed size' appended to the frame header
	public boolean encripted;
	public boolean has_grouping_identity;
	
//	Some flags indicates that the frame header is extended with additional information. This information will be added to the frame header
//	in the same order as the flags indicating the additions. I.e. the four bytes of decompressed size will precede the encryption method byte.
//	These additions to the frame header, while not included in the frame header size but are included in the 'frame size' field, are not subject to encryption or compression. 	
	
	public Frame(Parseable p) throws IOException {
		length = p.UBEInt();
		
		byte flag = p.Byte();
		tag_preservation = Parseable.getBit(flag, 7) == 0; 
		file_preservation  = Parseable.getBit(flag, 6) == 0; 
		read_only = Parseable.getBit(flag, 5) == 1;
		
		flag = p.Byte();
		compressed = Parseable.getBit(flag, 7) == 1;
		encripted = Parseable.getBit(flag, 6) == 1;
		has_grouping_identity = Parseable.getBit(flag, 5) == 1;
	}
}