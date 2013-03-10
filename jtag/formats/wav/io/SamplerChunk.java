package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;
import outag.reference.Manufacturers;

public class SamplerChunk extends RIFFChunk {
	public SamplerChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		String manufacturer = Manufacturers.getManufacturerByCode(p.UInt()).getName(); // manufacturer field specifies the MIDI Manufacturer's Association (MMA) Manufacturer code for the sampler intended to receive this file's waveform. Each manufacturer of a MIDI product is assigned a unique ID which identifies the company. If no particular manufacturer is to be specified, a value of 0 should be used.
		
		p.UInt(); //Product
		p.UInt(); //Sample Period //duration of time that passes during the playback of one sample in nanoseconds (normally equal to 1 / Samplers Per Second, where Samples Per Second is the value found in the format chunk).  
		p.UInt(); //MIDI Unity Note //	0 - 127 // has the same meaning as the instrument chunk's MIDI Unshifted Note field which specifies the musical note at which the sample will be played at it's original sample rate (the sample rate specified in the format chunk). 
		p.UInt(); //MIDI Pitch Fraction // fraction of a semitone up from the specified MIDI unity note field. A value of 0x80000000 means 1/2 semitone (50 cents) and a value of 0x00000000 means no fine tuning between semitones. 
		int format = p.UInt(); //SMPTE Format 	0, 24, 25, 29, 30
		String formatInfo;
		
		switch(format) {
			case 0: formatInfo = "no SMPTE offset"; break;
			case 24: formatInfo = "24 frames per second"; break;
			case 25: formatInfo = "25 frames per second"; break;
			case 29: formatInfo = "30 frames per second with frame dropping (30 drop)"; break;
			case 30: formatInfo = "30 frames per second"; break;
		}
		
		p.UInt(); //SMPTE Offset // specifies the time offset to be used for the synchronization / calibration to the first sample in the waveform. This value uses a format of 0xhhmmssff where hh is a signed value that specifies the number of hours (-23 to 23), mm is an unsigned value that specifies the number of minutes (0 to 59), ss is an unsigned value that specifies the number of seconds (0 to 59) and ff is an unsigned value that specifies the number of frames (0 to -1). 
		p.UInt(); //Num Sample Loops // number Sample Loop definitions in the following list. This value may be set to 0 meaning that no sample loops follow. 
		p.UInt(); // Sampler Data // The sampler data value specifies the number of bytes that will follow this chunk (including the entire sample loop list). This value is greater than 0 when an application needs to save additional information. This value is reflected in this chunks data size value. 				
		
		p.skip(length - 36); // skip List of Sample Loops
	}
	
	public boolean isValidLength() {return true; } // length == (36 + (Num Sample Loops * 24) + Sampler Data)
}