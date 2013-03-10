package jtag.tags.id3v2.v4.io;

import java.io.IOException;

import jtag.io.Parseable;

public class HeadExtension {
	String ID;
	public int length;
	public int CRC;
	
	public HeadExtension(Parseable p) throws IOException {
		length = p.BESynchSafeInt();
		p.UByte(); // flags num - must equals 1
		byte flags = p.UByte();
		
		
//		if (Parseable.getBit(flags, 15) == 1)
//			CRC = p.UInt();
//		int padding = p.UInt();
//		p.skip(padding);
	}	
}


//The extended header contains information that can provide further
//insight in the structure of the tag, but is not vital to the correct
//parsing of the tag information; hence the extended header is
//optional.
//
//  Extended header size   4 * %0xxxxxxx
//  Number of flag bytes       $01
//  Extended Flags             $xx
//
//Where the 'Extended header size' is the size of the whole extended
//header, stored as a 32 bit synchsafe integer. An extended header can
//thus never have a size of fewer than six bytes.
//
//The extended flags field, with its size described by 'number of flag
//bytes', is defined as:
//
//  %0bcd0000
//
//Each flag that is set in the extended header has data attached, which
//comes in the order in which the flags are encountered (i.e. the data
//for flag 'b' comes before the data for flag 'c'). Unset flags cannot
//have any attached data. All unknown flags MUST be unset and their
//corresponding data removed when a tag is modified.
//
//Every set flag's data starts with a length byte, which contains a
//value between 0 and 128 ($00 - $7f), followed by data that has the
//field length indicated by the length byte. If a flag has no attached
//data, the value $00 is used as length byte.
//
//
//b - Tag is an update
//
//  If this flag is set, the present tag is an update of a tag found
//  earlier in the present file or stream. If frames defined as unique
//  are found in the present tag, they are to override any
//  corresponding ones found in the earlier tag. This flag has no
//  corresponding data.
//
//      Flag data length      $00
//
//c - CRC data present
//
//  If this flag is set, a CRC-32 [ISO-3309] data is included in the
//  extended header. The CRC is calculated on all the data between the
//  header and footer as indicated by the header's tag length field,
//  minus the extended header. Note that this includes the padding (if
//  there is any), but excludes the footer. The CRC-32 is stored as an
//  35 bit synchsafe integer, leaving the upper four bits always
//  zeroed.
//
//     Flag data length       $05
//     Total frame CRC    5 * %0xxxxxxx
//
//d - Tag restrictions
//
//  For some applications it might be desired to restrict a tag in more
//  ways than imposed by the ID3v2 specification. Note that the
//  presence of these restrictions does not affect how the tag is
//  decoded, merely how it was restricted before encoding. If this flag
//  is set the tag is restricted as follows:
//
//     Flag data length       $01
//     Restrictions           %ppqrrstt
//
//  p - Tag size restrictions
//
//    00   No more than 128 frames and 1 MB total tag size.
//    01   No more than 64 frames and 128 KB total tag size.
//    10   No more than 32 frames and 40 KB total tag size.
//    11   No more than 32 frames and 4 KB total tag size.
//
//  q - Text encoding restrictions
//
//    0    No restrictions
//    1    Strings are only encoded with ISO-8859-1 [ISO-8859-1] or
//         UTF-8 [UTF-8].
//
//  r - Text fields size restrictions
//
//    00   No restrictions
//    01   No string is longer than 1024 characters.
//    10   No string is longer than 128 characters.
//    11   No string is longer than 30 characters.
//
//    Note that nothing is said about how many bytes is used to
//    represent those characters, since it is encoding dependent. If a
//    text frame consists of more than one string, the sum of the
//    strungs is restricted as stated.
//
//  s - Image encoding restrictions
//
//    0   No restrictions
//    1   Images are encoded only with PNG [PNG] or JPEG [JFIF].
//
//  t - Image size restrictions
//
//    00  No restrictions
//    01  All images are 256x256 pixels or smaller.
//    10  All images are 64x64 pixels or smaller.
//    11  All images are exactly 64x64 pixels, unless required
//        otherwise.