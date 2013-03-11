package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.EFrame;

public class USLT extends EFrame {	
	public String descriptor;
	public String lyrics;
	
	public USLT(Parseable p) throws IOException { 
		super(p);
		int language = p.UBEInt24();
	
		String content = p.Str(length - 4, encInfo.name);
		String [] vals = content.split(encInfo.null_terminate);
		descriptor = vals[0]; lyrics = vals[1];		
		
//		   This frame contains the lyrics of the song or a text transcription of
//		   other vocal activities. The head includes an encoding descriptor and
//		   a content descriptor. The body consists of the actual text. The
//		   'Content descriptor' is a terminated string. If no descriptor is
//		   entered, 'Content descriptor' is $00 (00) only. Newline characters
//		   are allowed in the text. Maximum length for the descriptor is 64
//		   bytes. There may be more than one lyrics/text frame in each tag, but
//		   only one with the same language and content descriptor.
	}
}