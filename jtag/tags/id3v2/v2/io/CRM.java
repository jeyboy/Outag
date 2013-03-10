package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class CRM extends Frame {	
	public CRM(Parseable p) throws IOException { 
		super(p);
		p.skip(length);


//		   This frame contains one or more encrypted frames. This enables
//		   protection of copyrighted information such as pictures and text, that
//		   people might want to pay extra for. Since standardisation of such an
//		   encryption scheme is beyond this document, all "CRM" frames begin with
//		   a terminated string with a URL [URL] containing an email address, or a
//		   link to a location where an email adress can be found, that belongs to
//		   the organisation responsible for this specific encrypted meta frame.
//
//		   Questions regarding the encrypted frame should be sent to the
//		   indicated email address. If a $00 is found directly after the 'Frame
//		   size', the whole frame should be ignored, and preferably be removed.
//		   The 'Owner identifier' is then followed by a short content description
//		   and explanation as to why it's encrypted. After the
//		   'content/explanation' description, the actual encrypted block follows.
//
//		   When an ID3v2 decoder encounters a "CRM" frame, it should send the
//		   datablock to the 'plugin' with the corresponding 'owner identifier'
//		   and expect to receive either a datablock with one or several ID3v2
//		   frames after each other or an error. There may be more than one "CRM"
//		   frames in a tag, but only one with the same 'owner identifier'.
//
//		     Encrypted meta frame  "CRM"
//		     Frame size            $xx xx xx
//		     Owner identifier      <textstring> $00 (00)
//		     Content/explanation   <textstring> $00 (00)
//		     Encrypted datablock   <binary data>
	}
}