package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class REV extends Frame {	
	public REV(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     Reverb left (ms)                 $xx xx
//	     Reverb right (ms)                $xx xx
//	     Reverb bounces, left             $xx
//	     Reverb bounces, right            $xx
//	     Reverb feedback, left to left    $xx
//	     Reverb feedback, left to right   $xx
//	     Reverb feedback, right to right  $xx
//	     Reverb feedback, right to left   $xx
//	     Premix left to right             $xx
//	     Premix right to left             $xx		
		
//		   Yet another subjective one. You may here adjust echoes of different
//		   kinds. Reverb left/right is the delay between every bounce in ms.
//		   Reverb bounces left/right is the number of bounces that should be
//		   made. $FF equals an infinite number of bounces. Feedback is the amount
//		   of volume that should be returned to the next echo bounce. $00 is 0%,
//		   $FF is 100%. If this value were $7F, there would be 50% volume
//		   reduction on the first bounce, yet 50% on the second and so on. Left
//		   to left means the sound from the left bounce to be played in the left
//		   speaker, while left to right means sound from the left bounce to be
//		   played in the right speaker.
//
//		   'Premix left to right' is the amount of left sound to be mixed in the
//		   right before any reverb is applied, where $00 id 0% and $FF is 100%.
//		   'Premix right to left' does the same thing, but right to left. Setting
//		   both premix to $FF would result in a mono output (if the reverb is
//		   applied symmetric). There may only be one "REV" frame in each tag.		
	}
}