package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.EFrame;

public class SLT extends EFrame {	
	public SLT(Parseable p) throws IOException { 
		super(p);
		
		int language = p.UBEInt24();
		
		byte time_stamp = p.UByte();
//			$01  Absolute time, 32 bit sized, using MPEG [MPEG] frames as unit
//			$02  Absolute time, 32 bit sized, using milliseconds as unit

		byte content_type = p.UByte();
//		Content type:   
//		   $00 is other
//           $01 is lyrics
//           $02 is text transcription
//           $03 is movement/part name (e.g. "Adagio")
//           $04 is events (e.g. "Don Quijote enters the stage")
//           $05 is chord (e.g. "Bb F Fsus")		
		String content = p.Str(length - 6, encInfo.name);
		
//		   The text that follows the frame header differs from that of the
//		   unsynchronised lyrics/text transcription in one major way. Each
//		   syllable (or whatever size of text is considered to be convenient by
//		   the encoder) is a null terminated string followed by a time stamp
//		   denoting where in the sound file it belongs. Each sync thus has the
//		   following structure:
//
//		     Terminated text to be synced (typically a syllable)
//		     Sync identifier (terminator to above string)   $00 (00)
//		     Time stamp                                     $xx (xx ...)
//
//		   The 'time stamp' is set to zero or the whole sync is omitted if
//		   located directly at the beginning of the sound. All time stamps should
//		   be sorted in chronological order. The sync can be considered as a
//		   validator of the subsequent string.
//
//		   Newline characters are allowed in all "SLT" frames and should be used
//		   after every entry (name, event etc.) in a frame with the content type
//		   $03 - $04.
//
//		   A few considerations regarding whitespace characters: Whitespace
//		   separating words should mark the beginning of a new word, thus
//		   occurring in front of the first syllable of a new word. This is also
//		   valid for new line characters. A syllable followed by a comma should
//		   not be broken apart with a sync (both the syllable and the comma
//		   should be before the sync).
//
//		   An example: The "ULT" passage
//
//		     "Strangers in the night" $0A "Exchanging glances"
//
//		   would be "SLT" encoded as:
//
//		     "Strang" $00 xx xx "ers" $00 xx xx " in" $00 xx xx " the" $00 xx xx
//		     " night" $00 xx xx 0A "Ex" $00 xx xx "chang" $00 xx xx "ing" $00 xx
//		     xx "glan" $00 xx xx "ces" $00 xx xx		
	}
}