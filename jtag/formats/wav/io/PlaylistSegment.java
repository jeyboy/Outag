package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class PlaylistSegment {
	public PlaylistSegment(Parseable p) throws IOException {
		int cuePointID = p.UInt();
		int lengthInSamples = p.UInt(); //specifies the number of samples to play/loop from the starting sample defined by the associated Cue Point
		int repeatsNum = p.UInt(); //determines how many times this segment should be looped before playback should continue onto the next segment. 
	}
}