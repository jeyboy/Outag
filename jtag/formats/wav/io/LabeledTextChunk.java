package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class LabeledTextChunk extends RIFFChunk {
	public LabeledTextChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.UInt(); // CUE Point ID
		p.UInt(); //sample length
		p.UInt(); //purpose ID
		//The purpose field specifies what the text is used for. For example a value of "scrp" means script text, and "capt" 
		//means close-caption. There are several more purpose IDs, but they are meant to be used with other types of RIFF files (not usually found in WAVE files). 
		
		p.UShort(); //country
		p.UShort(); //language
		p.UShort(); //dialect
		p.UShort(); //code page
		
		//Country, Language, Dialect and Code Page
		//These fields are used to specify information about the location and language used by the text and are typically
		//used for queries to obtain information from the operating system. 
		
		p.skip(length - 20); // skip text
		//The text is a null terminated string of characters. If the number of characters in the string is not even,
		//padding must be appended to the string. The appended padding is not considered in the label chunk's chunk size field.
	}
}