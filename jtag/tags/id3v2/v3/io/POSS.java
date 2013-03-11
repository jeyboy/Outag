package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.Base;

public class POSS extends Base {	
	public POSS(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//		Time stamp format   $xx
//		Position            $xx (xx ...)
//
//		Where time stamp format is:
//
//		$01 Absolute time, 32 bit sized, using MPEG frames as unit
//		$02 Absolute time, 32 bit sized, using milliseconds as unit
//
//		and position is where in the audio the listener starts to
//		receive, i.e. the beginning of the next frame. If this frame
//		is used in the beginning of a file the value is always 0. There may only be one "POSS" frame in each tag. 
	}
}