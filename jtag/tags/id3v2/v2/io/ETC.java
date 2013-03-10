package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class ETC extends Frame {	
	public ETC(Parseable p) throws IOException { 
		super(p);
		byte type = p.UByte();
//			$01  Absolute time, 32 bit sized, using MPEG [MPEG] frames as unit
//			$02  Absolute time, 32 bit sized, using milliseconds as unit

		p.skip(length - 1);
//		   Absolute time means that every stamp contains the time from the
//		   beginning of the file.
//
//	   Followed by a list of key events in the following format:
//
//	     Type of event   $xx
//	     Time stamp      $xx (xx ...)
//
//	   The 'Time stamp' is set to zero if directly at the beginning of the
//	   sound or after the previous event. All events should be sorted in
//	   chronological order. The type of event is as follows:
//
//	     $00  padding (has no meaning)
//	     $01  end of initial silence
//	     $02  intro start
//	     $03  mainpart start
//	     $04  outro start
//	     $05  outro end
//	     $06  verse begins
//	     $07  refrain begins
//	     $08  interlude
//	     $09  theme start
//	     $0A  variation
//	     $0B  key change
//	     $0C  time change
//	     $0D  unwanted noise (Snap, Crackle & Pop)
//
//	     $0E-$DF  reserved for future use
//
//	     $E0-$EF  not predefined sync 0-F
//
//	     $F0-$FC  reserved for future use
//
//	     $FD  audio end (start of silence)
//	     $FE  audio file ends
//	     $FF  one more byte of events follows (all the following bytes with
//	          the value $FF have the same function)
//
//	   The 'Not predefined sync's ($E0-EF) are for user events. You might
//	   want to synchronise your music to something, like setting of an
//	   explosion on-stage, turning on your screensaver etc.		
	}
}