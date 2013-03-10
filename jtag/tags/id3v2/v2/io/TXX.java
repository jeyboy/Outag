package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class TXX extends T__ {
	public String description;
	public String value;
	
	public TXX(Parseable p) throws IOException {
		super(p);
		String [] vals = info.split(encInfo.null_terminate);
		description = vals[0]; value = vals[1];		
	}
}