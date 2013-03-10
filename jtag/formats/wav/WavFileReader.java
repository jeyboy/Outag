package jtag.formats.wav;

import java.io.IOException;

import jtag.formats.AudioFileReader;
import jtag.formats.EncodingInfo;
import jtag.formats.Tag;
import jtag.io.JBFile;

public class WavFileReader extends AudioFileReader {	
	protected EncodingInfo getEncodingInfo(JBFile f) throws IOException {
		return new RIFFParser().read(f);
	}
	
	protected Tag getTag(JBFile raf) {
		return new Tag();
	}
}