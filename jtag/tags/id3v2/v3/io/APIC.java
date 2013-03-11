package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.EFrame;

public class APIC extends EFrame {	
	public APIC(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//		MIME type       <text string> $00
//		Picture type    $xx
//		Description     <text string according to encoding> $00 (00)
//		Picture data    <binary data>

//		This frame contains a picture directly related to the audio file. Image format is the MIME type and subtype for the image.
//		In the event that the MIME media type name is omitted, "image/" will be implied. The "image/png" or "image/jpeg" picture format
//		should be used when interoperability is wanted. Description is a short description of the picture, represented as a terminated
//		textstring. The description has a maximum length of 64 characters, but may be empty. There may be several pictures attached to
//		one file, each in their individual "APIC" frame, but only one with the same content descriptor. There may only be one picture
//		with the picture type declared as picture type $01 and $02 respectively. There is the possibility to put only a link to the
//		image file by using the 'MIME type' "-->" and having a complete URL instead of picture data. The use of linked files should
//		however be used sparingly since there is the risk of separation of files. 	
	}
}