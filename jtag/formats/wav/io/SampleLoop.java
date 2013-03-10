package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class SampleLoop {
	public SampleLoop(Parseable p) throws IOException {
		p.skip(4); //Cue Point ID
		p.skip(4); //type //defines how the waveform samples will be looped. 
	//		0 	Loop forward (normal)
	//		1 	Alternating loop (forward/backward, also known as Ping Pong)
	//		2 	Loop backward (reverse)
	//		3 - 31 	Reserved for future standard types
	//		32 - 0xFFFFFFFF 	Sampler specific types (defined by manufacturer)
		
		
		p.skip(4); //start // specifies the byte offset into the waveform data of the first sample to be played in the loop. 
		p.skip(4); //end // specifies the byte offset into the waveform data of the last sample to be played in the loop. 
		p.skip(4); //fraction // specifies a fraction of a sample at which to loop. This allows a loop to be fine tuned at a resolution greater than one sample. The value can range from 0x00000000 to 0xFFFFFFFF. A value of 0 means no fraction, a value of 0x80000000 means 1/2 of a sample length. 0xFFFFFFFF is the smallest fraction of a sample that can be represented. 
		p.skip(4); //play count // determines the number of times to play the loop. A value of 0 specifies an infinite sustain loop. An infinite sustain loop will continue looping until some external force interrupts playback, such as the musician releasing the key that triggered the wave's playback. All other values specify an absolute number of times to loop. 
	}
}