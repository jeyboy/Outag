package outag.formats.wv.io;

//Flags for ID:
//
//	 0x20 - decoder may ignore data contained here
//	 0x40 - data size is odd
//	 0x80 - data size is large
//
//	IDs:
//
//	 * 0x00 - dummy (used for padding)
//	 * 0x02 - decorrelation terms
//	 * 0x03 - decorrelation weights
//	 * 0x04 - decorrelation samples
//	 * 0x05 - entropy info
//	 * 0x06 - hybrid profile
//	 * 0x07 - noise shaping profile (wvc file)
//	 * 0x08 - floating-point data profile
//	 * 0x09 - large or shifted integer profile
//	 * 0x0A - packed samples
//	 * 0x0B - packed correction data (wvc file)
//	 * 0x0C - packed overflow bits from floating-point or large integers
//	 * 0x0D - multichannel information (including Microsoft channel mask)
//
//	 * 0x20 - RIFF header for .wav files (before audio)
//	 * 0x21 - RIFF trailer for .wav files (after audio)
//	 * 0x25 - some encoding details for info purposes
//	 * 0x26 - 16-byte MD5 sum of raw audio data
//	 * 0x27 - non-standard sampling rate
//
//	[edit]
//	Decorrelation terms
//
//	Decorrelation terms are stored in one byte, lower 5 bits indicate predictor type, high 3 bits contain delta value.
//
//	Possible predictor values:
//
//	 0-5 - predictors for stereo, only predictors 2-4 are implemented
//	 6-12 - predictor uses 1-7 samples for prediction
//	 13-16 - reserved
//	 17-18 - predictor does prediction by two samples
//
//	[edit]
//	Decorrelation weights
//
//	Each decorrelation term should have one or two weights depending on channels. Each weight is packed into one byte and can be restored in this way:
//
//	 n = getchar() << 3;
//	 if(n > 0) n += (n + 64) >> 7;

public class Metadata {
	
}