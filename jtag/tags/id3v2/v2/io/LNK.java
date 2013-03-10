package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class LNK extends Frame {	
	public LNK(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
//	     Frame identifier    $xx xx xx
//	     URL                 <textstring> $00 (00)
//	     Additional ID data  <textstring(s)>

//		   To keep space waste as low as possible this frame may be used to link
//		   information from another ID3v2 tag that might reside in another audio
//		   file or alone in a binary file. It is recommended that this method is
//		   only used when the files are stored on a CD-ROM or other circumstances
//		   when the risk of file seperation is low. The frame contains a frame
//		   identifier, which is the frame that should be linked into this tag, a
//		   URL [URL] field, where a reference to the file where the frame is
//		   given, and additional ID data, if needed. Data should be retrieved
//		   from the first tag found in the file to which this link points. There
//		   may be more than one "LNK" frame in a tag, but only one with the same
//		   contents. A linked frame is to be considered as part of the tag and
//		   has the same restrictions as if it was a physical part of the tag
//		   (i.e. only one "REV" frame allowed, whether it's linked or not).
//		   
//		   Frames that may be linked and need no additional data are "IPL",
//		   "MCI", "ETC", "LLT", "STC", "RVA", "EQU", "REV", "BUF", the text
//		   information frames and the URL link frames.
//		   
//		   The "TXX", "PIC", "GEO", "CRM" and "CRA" frames may be linked with the
//		   content descriptor as additional ID data.
//		   
//		   The "COM", "SLT" and "ULT" frames may be linked with three bytes of
//		   language descriptor directly followed by a content descriptor as
//		   additional ID data.
	}
}