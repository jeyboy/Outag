package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.ETextFrame;

public class WXXX extends ETextFrame {
	public String description;
	public String value;	
	
	public WXXX(Parseable p) throws IOException {
		super(p);
		String [] vals = info.split(encInfo.null_terminate);
		description = vals[0]; value = vals[1];		
	}
}