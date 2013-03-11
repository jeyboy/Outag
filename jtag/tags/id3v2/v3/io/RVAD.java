package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.Base;

public class RVAD extends Base {
	public RVAD(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//		Increment/decrement             %00xxxxxx
//		Bits used for volume descr.     $xx
//		Relative volume change, right   $xx xx (xx ...)
//		Relative volume change, left    $xx xx (xx ...)
//		Peak volume right               $xx xx (xx ...)
//		Peak volume left                $xx xx (xx ...)
//
//		In the increment/decrement field bit 0 is used to indicate the right channel and bit 1 is used to indicate the left channel. 1 is increment and 0 is decrement.
//
//		The 'bits used for volume description' field is normally $10 (16 bits) for MPEG 2 layer I, II and III and MPEG 2.5. This value may not be $00. The volume is always represented with whole bytes, padded in the beginning (highest bits) when 'bits used for volume description' is not a multiple of eight.
//
//		This datablock is then optionally followed by a volume definition for the left and right back channels. If this information is appended to the frame the first two channels will be treated as front channels. In the increment/decrement field bit 2 is used to indicate the right back channel and bit 3 for the left back channel.
//
//		Relative volume change, right back      $xx xx (xx ...)
//		Relative volume change, left back       $xx xx (xx ...)
//		Peak volume right back                  $xx xx (xx ...)
//		Peak volume left back                   $xx xx (xx ...)
//
//		If the center channel adjustment is present the following is appended to the existing frame, after the left and right back channels. The center channel is represented by bit 4 in the increase/decrease field.
//
//		Relative volume change, center  $xx xx (xx ...)
//		Peak volume center              $xx xx (xx ...)
//
//		If the bass channel adjustment is present the following is appended to the existing frame, after the center channel. The bass channel is represented by bit 5 in the increase/decrease field.
//
//		Relative volume change, bass    $xx xx (xx ...)
//		Peak volume bass                $xx xx (xx ...)	
	}
}