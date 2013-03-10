package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.EFrame;

public class PIC extends EFrame {	
	public PIC(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     Image format       $xx xx xx
//	     Picture type       $xx 							// jtag.reference.PictureTypes
//	     Description        <textstring> $00 (00)			// encInfo
//	     Picture data       <binary data>		

//		   This frame contains a picture directly related to the audio file.
//		   Image format is preferably "PNG" [PNG] or "JPG" [JFIF]. Description
//		   is a short description of the picture, represented as a terminated
//		   textstring. The description has a maximum length of 64 characters,
//		   but may be empty. There may be several pictures attached to one file,
//		   each in their individual "PIC" frame, but only one with the same
//		   content descriptor. There may only be one picture with the picture
//		   type declared as picture type $01 and $02 respectively. There is a
//		   possibility to put only a link to the image file by using the 'image
//		   format' "-->" and having a complete URL [URL] instead of picture data.
//		   The use of linked files should however be used restrictively since
//		   there is the risk of separation of files.		
	}
}