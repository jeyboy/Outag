package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class BUF extends Frame {	
	public BUF(Parseable p) throws IOException { 
		super(p);
		p.skip(length);


//		   Sometimes the server from which a audio file is streamed is aware of
//		   transmission or coding problems resulting in interruptions in the
//		   audio stream. In these cases, the size of the buffer can be
//		   recommended by the server using this frame. If the 'embedded info
//		   flag' is true (1) then this indicates that an ID3 tag with the
//		   maximum size described in 'Buffer size' may occur in the audiostream.
//		   In such case the tag should reside between two MPEG [MPEG] frames, if
//		   the audio is MPEG encoded. If the position of the next tag is known,
//		   'offset to next tag' may be used. The offset is calculated from the
//		   end of tag in which this frame resides to the first byte of the header
//		   in the next. This field may be omitted. Embedded tags is currently not
//		   recommended since this could render unpredictable behaviour from
//		   present software/hardware. The 'Buffer size' should be kept to a
//		   minimum. There may only be one "BUF" frame in each tag.
//
//		     Recommended buffer size   "BUF"
//		     Frame size                $xx xx xx
//		     Buffer size               $xx xx xx
//		     Embedded info flag        %0000000x
//		     Offset to next tag        $xx xx xx xx
	}
}