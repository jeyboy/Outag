package jtag.tags;

import java.io.IOException;

import jtag.formats.Tag;
import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.ID3v22;

/** ID3v2 implementation <br><b>(Not checked)</b> */
public class ID3v2 {	
	public Tag read(Parseable p) throws IOException {
		p.pos(0);
		String flag = p.Str(3);
		short version = p.UShort();
		Tag tag = null;
		
		if (flag.equals("ID3")) {
			switch(version) {
				case 2: tag = new ID3v22().read(p); break;
				case 3: break;
				case 4: break;
				default: tag = new Tag(); 
			}
		}
		
		return tag;
	}
}