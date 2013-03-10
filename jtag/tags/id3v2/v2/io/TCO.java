package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class TCO extends T__ {
	public TCO(Parseable p) throws IOException { super(p); }
	
	public String [] genres() {
//		   The content type, which previously (in ID3v1.1, see appendix A) was
//		   stored as a one byte numeric value only, is now a numeric string. You
//		   may use one or several of the types as ID3v1.1 did or, since the
//		   category list would be impossible to maintain with accurate and up to
//		   date categories, define your own.
//		   References to the ID3v1 genres can be made by, as first byte, enter
//		   "(" followed by a number from the genres list (section A.3.) and
//		   ended with a ")" character. This is optionally followed by a
//		   refinement, e.g. "(21)" or "(4)Eurodisco". Several references can be
//		   made in the same frame, e.g. "(51)(39)". If the refinement should
//		   begin with a "(" character it should be replaced with "((", e.g. "((I
//		   can figure out any genre)" or "(55)((I think...)". The following new
//		   content types is defined in ID3v2 and is implemented in the same way
//		   as the numerig content types, e.g. "(RX)".
//		   
//		     RX  Remix
//		     CR  Cover
		return info.split("\\(([0-9]+|RX|CR)\\)([^(]*)?");
	}
}