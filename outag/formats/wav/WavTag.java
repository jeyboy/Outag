package outag.formats.wav;

import outag.formats.generic.GenericTag;

public class WavTag extends GenericTag {
	public String toString() {
		String output = "WAV "+super.toString();
		return output;
	}
}