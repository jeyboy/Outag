package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

//Format Variations
//The down side to the Wave file format's popularity is that out of the hundreds of programs that support it, many abuse or misuse it due to bad programming and/or poor documentation. Once some of these "naughty" programs get fairly popular and churn out millions of incorrect Wave files, the rest of the software industry is forced to deal with it and write code that can read the incorrect files. New code should never write these errors, but possibly read them. Below are a few exceptions that have been made to the strict/original Wave file format.
//
//    Incorrect Block Alignment value - this can be dealt with by calculating the Block Alignment with the formula mentioned above.
//    Incorrect Average Samples Per Second value - this can be dealt with by calculating the Average Samples Per Second with the formula mentioned above.
//    Missing word alignment padding - this can be difficult to deal with, but can be done by giving the user a warning when unrecognized chunk ID's are encountered where a one byte read offset produces a recognized chunk ID. This is not a concrete solution, but will usually work even if the program doesn't have a comprehensive list of legal IDs. 

public class Header extends RIFFChunk {
	public Header(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.Str(4).equals("WAVE");
	}
}