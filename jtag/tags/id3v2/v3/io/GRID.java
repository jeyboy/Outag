package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.TextFrame;

public class GRID extends TextFrame {
	public String owner;
	public String groupData;
	
	public GRID(Parseable p) throws IOException {
		super(p);
//		Owner identifier     <text string> $00
//		Group symbol         $xx
//		Group dependent data <binary data>
//		
//		This frame enables grouping of otherwise unrelated frames. This can be used when some frames are to be
//		signed. To identify which frames belongs to a set of frames a group identifier must be registered in the
//		tag with this frame. The 'Owner identifier' is a null-terminated string with a URL containing an email 
//		address, or a link to a location where an email address can be found, that belongs to the organisation
//		responsible for this grouping. Questions regarding the grouping should be sent to the indicated email 
//		address. The 'Group symbol' contains a value that associates the frame with this group throughout the 
//		whole tag. Values below $80 are reserved. The 'Group symbol' may optionally be followed by some group 
//		specific data, e.g. a digital signature. There may be several "GRID" frames in a tag but only one containing
//		the same symbol and only one containing the same owner identifier. The group symbol must be used somewhere
//		in the tag. See section 3.3.1, flag j for more information. 		
	}
}