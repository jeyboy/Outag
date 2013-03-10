package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class CRA extends Frame {	
	public CRA(Parseable p) throws IOException { 
		super(p);
		p.skip(length);


//		   This frame indicates if the actual audio stream is encrypted, and by
//		   whom. Since standardisation of such encrypion scheme is beyond this
//		   document, all "CRA" frames begin with a terminated string with a
//		   URL containing an email address, or a link to a location where an
//		   email address can be found, that belongs to the organisation
//		   responsible for this specific encrypted audio file. Questions
//		   regarding the encrypted audio should be sent to the email address
//		   specified. If a $00 is found directly after the 'Frame size' and the
//		   audiofile indeed is encrypted, the whole file may be considered
//		   useless.
//
//		   After the 'Owner identifier', a pointer to an unencrypted part of the
//		   audio can be specified. The 'Preview start' and 'Preview length' is
//		   described in frames. If no part is unencrypted, these fields should be
//		   left zeroed. After the 'preview length' field follows optionally a
//		   datablock required for decryption of the audio. There may be more than
//		   one "CRA" frames in a tag, but only one with the same 'Owner
//		   identifier'.
//
//		     Audio encryption   "CRA"
//		     Frame size         $xx xx xx
//		     Owner identifier   <textstring> $00 (00)
//		     Preview start      $xx xx
//		     Preview length     $xx xx
//		     Encryption info    <binary data>
	}
}