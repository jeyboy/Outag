package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.Base;

public class POPM extends Base {	
	public POPM(Parseable p) throws IOException { 
		super(p);
		p.skip(length);

//	     Email to user   <textstring> $00
//	     Rating          $xx
//	     Counter         $xx xx xx xx (xx ...)		

//		   The purpose of this frame is to specify how good an audio file is.
//		   Many interesting applications could be found to this frame such as a
//		   playlist that features better audiofiles more often than others or it
//		   could be used to profile a persons taste and find other 'good' files
//		   by comparing people's profiles. The frame is very simple. It contains
//		   the email address to the user, one rating byte and a four byte play
//		   counter, intended to be increased with one for every time the file is
//		   played. The email is a terminated string. The rating is 1-255 where
//		   1 is worst and 255 is best. 0 is unknown. If no personal counter is
//		   wanted it may be omitted.  When the counter reaches all one's, one
//		   byte is inserted in front of the counter thus making the counter
//		   eight bits bigger in the same away as the play counter ("CNT").
//		   There may be more than one "POP" frame in each tag, but only one with
//		   the same email address.	
	}
}