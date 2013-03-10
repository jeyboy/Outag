package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class InstrumentChunk extends RIFFChunk {
	public InstrumentChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.Byte(); // Unshifted Note 	0 - 127 // has the same meaning as the sampler chunk's MIDI Unity Note which specifies the musical note at which the sample will be played at it's original sample rate (the sample rate specified in the format chunk). 
		p.Byte(); // Fine Tune (dB) 	-50 - +50 // how much the sample's pitch should be altered when the sound is played back in cents (1/100 of a semitone). A negative value means that the pitch should be played lower and a positive value means that it should be played at a higher pitch. 
		p.Byte(); // Gain 	-64 - +64 // specifies the number of decibels to adjust the output when it is played. A value of 0dB means no change, 6dB means double the amplitude of each sample and -6dB means to halve the amplitude of each sample. Every additional +/-6dB will double or halve the amplitude again
		p.Byte(); // Low Note 	0 - 127 //  specify the MIDI note range for which the waveform should be played when receiving MIDI note events (from software or triggered by a MIDI controller). This range does not need to include the Unshifted Note value. 
		p.Byte(); // High Note 	0 - 127 // 
		p.Byte(); // Low Velocity 	1 - 127 // specify the range of MIDI velocities that should cause the waveform to be played. 1 being the lightest amount and 127 being the hardest. 
		p.Byte(); // High Velocity 	1 - 127
	}
	
	public boolean isValidLength() { return length == 7;}
}