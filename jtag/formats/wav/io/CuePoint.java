package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class CuePoint {
	public CuePoint(Parseable p) throws IOException {
		p.skip(4); //ID //Each cue point has a unique identification value used to associate cue points with information in other chunks
		p.skip(4); //position // position specifies the sample offset associated with the cue point 
		p.skip(4); //Data Chunk ID 	RIFF ID of corresponding data chunk
		p.skip(4); //Chunk Start 	Byte Offset of Data Chunk *
		p.skip(4); //Block Start 	Byte Offset to sample of First Channel
		p.skip(4); //Sample Offset 	Byte Offset to sample byte of First Channel
	}
}