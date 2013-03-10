package jtag.tags.id3v2.io;

import java.util.Date;

public class ID3Utils {
	/** TCO, TCON */
	public String [] genres(String str) {
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
		return str.split("\\(([0-9]+|RX|CR)\\)([^(]*)?");
	}
	
	/** TDA, TDAT */
	public Date date(String str) {
		int day = Integer.getInteger(str.substring(0, 1));
		int month = Integer.getInteger(str.substring(2, 3));		
		return new Date(0, month, day);
//		java.util.Calendar c = java.util.Calendar.getInstance();
//		c.set(0, month, day);
	}
	
	/** TIM, TIME */
	public Date time(String str) {
		int hours = Integer.getInteger(str.substring(0, 1));
		int minutes = Integer.getInteger(str.substring(2, 3));		
		return new Date(0, 0, 0, hours, minutes);
	}
	
	/** TCM , TOA, TOL, TXT */
	public String [] split(String str) { return str.split("/");	}
}