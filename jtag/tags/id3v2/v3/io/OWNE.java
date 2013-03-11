package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.EFrame;

public class OWNE extends EFrame {	
	public String text;
	
	public OWNE(Parseable p) throws IOException { 
		super(p);
	
		text = p.Str(length - 1, encInfo.name);
		
//		Price payed     <text string> $00
//		Date of purch.  <text string>
//		Seller          <text string according to encoding>
//		
//		The ownership frame might be used as a reminder of a made transaction or, if signed, as proof. 
//		Note that the "USER" and "TOWN" frames are good to use in conjunction with this one. The frame begins,
//		after the frame ID, size and encoding fields, with a 'price payed' field. The first three characters of
//		this field contains the currency used for the transaction, encoded according to ISO-4217 alphabetic currency
//		code. Concatenated to this is the actual price payed, as a numerical string using "." as the decimal 
//		separator. Next is an 8 character date string (YYYYMMDD) followed by a string with the name of the 
//		seller as the last field in the frame. There may only be one "OWNE" frame in a tag. 		
	}
}