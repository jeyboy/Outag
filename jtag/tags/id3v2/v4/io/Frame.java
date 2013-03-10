package jtag.tags.id3v2.v4.io;

import java.io.IOException;

import jtag.io.Parseable;

public class Frame {
	public int length;

	
	public Frame(Parseable p) throws IOException {
		length = p.UBEInt();
		
//		byte flag = p.Byte();
//		tag_preservation = Parseable.getBit(flag, 7) == 0; 
//		file_preservation  = Parseable.getBit(flag, 6) == 0; 
//		read_only = Parseable.getBit(flag, 5) == 1;
//		
//		flag = p.Byte();
//		compressed = Parseable.getBit(flag, 7) == 1;
//		encripted = Parseable.getBit(flag, 6) == 1;
//		has_grouping_identity = Parseable.getBit(flag, 5) == 1;
	}
}

//In the frame header the size descriptor is followed by two flag
//bytes. All unused flags MUST be cleared. The first byte is for
//'status messages' and the second byte is a format description. If an
//unknown flag is set in the first byte the frame MUST NOT be changed
//without that bit cleared. If an unknown flag is set in the second
//byte the frame is likely to not be readable. Some flags in the second
//byte indicates that extra information is added to the header. These
//fields of extra information is ordered as the flags that indicates
//them. The flags field is defined as follows (l and o left out because
//ther resemblence to one and zero):
//
//  %0abc0000 %0h00kmnp
//
//Some frame format flags indicate that additional information fields
//are added to the frame. This information is added after the frame
//header and before the frame data in the same order as the flags that
//indicates them. I.e. the four bytes of decompressed size will precede
//the encryption method byte. These additions affects the 'frame size'
//field, but are not subject to encryption or compression.
//
//The default status flags setting for a frame is, unless stated
//otherwise, 'preserved if tag is altered' and 'preserved if file is
//altered', i.e. %00000000.
//
//
//4.1.1. Frame status flags
//
//a - Tag alter preservation
//
//  This flag tells the tag parser what to do with this frame if it is
//  unknown and the tag is altered in any way. This applies to all
//  kinds of alterations, including adding more padding and reordering
//  the frames.
//
//  0     Frame should be preserved.
//  1     Frame should be discarded.
//
//
//b - File alter preservation
//
//  This flag tells the tag parser what to do with this frame if it is
//  unknown and the file, excluding the tag, is altered. This does not
//  apply when the audio is completely replaced with other audio data.
//
//  0     Frame should be preserved.
//  1     Frame should be discarded.
//
//
//c - Read only
//
//   This flag, if set, tells the software that the contents of this
//   frame are intended to be read only. Changing the contents might
//   break something, e.g. a signature. If the contents are changed,
//   without knowledge of why the frame was flagged read only and
//   without taking the proper means to compensate, e.g. recalculating
//   the signature, the bit MUST be cleared.
//
//
//4.1.2. Frame format flags
//
//h - Grouping identity
//
//   This flag indicates whether or not this frame belongs in a group
//   with other frames. If set, a group identifier byte is added to the
//   frame. Every frame with the same group identifier belongs to the
//   same group.
//
//   0     Frame does not contain group information
//   1     Frame contains group information
//
//
//k - Compression
//
//   This flag indicates whether or not the frame is compressed.
//   A 'Data Length Indicator' byte MUST be included in the frame.
//
//   0     Frame is not compressed.
//   1     Frame is compressed using zlib [zlib] deflate method.
//         If set, this requires the 'Data Length Indicator' bit
//         to be set as well.
//
//
//m - Encryption
//
//   This flag indicates whether or not the frame is encrypted. If set,
//   one byte indicating with which method it was encrypted will be
//   added to the frame. See description of the ENCR frame for more
//   information about encryption method registration. Encryption
//   should be done after compression. Whether or not setting this flag
//   requires the presence of a 'Data Length Indicator' depends on the
//   specific algorithm used.
//
//   0     Frame is not encrypted.
//   1     Frame is encrypted.
//
//n - Unsynchronisation
//
//   This flag indicates whether or not unsynchronisation was applied
//   to this frame. See section 6 for details on unsynchronisation.
//   If this flag is set all data from the end of this header to the
//   end of this frame has been unsynchronised. Although desirable, the
//   presence of a 'Data Length Indicator' is not made mandatory by
//   unsynchronisation.
//
//   0     Frame has not been unsynchronised.
//   1     Frame has been unsyrchronised.
//
//p - Data length indicator
//
//   This flag indicates that a data length indicator has been added to
//   the frame. The data length indicator is the value one would write
//   as the 'Frame length' if all of the frame format flags were
//   zeroed, represented as a 32 bit synchsafe integer.
//
//   0      There is no Data Length Indicator.
//   1      A data length Indicator has been added to the frame.