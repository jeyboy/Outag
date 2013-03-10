package jtag.tags;

import java.io.IOException;

import jtag.formats.Tag;
import jtag.io.Parseable;

/** ID3v1/ID3v1.1 implementation <br><b>(Not checked)</b> */
public class ID3v1PLUS {	
	public ID3v1PLUS() {}
	
	public Tag read(Parseable p, Tag tag) throws IOException {
		p.pos(p.length() - 355);
		
		if (p.Str(4).equals("TAG+")) {
			tag.appendTitle(p.Str(60));
			tag.appendArtist(p.Str(60));
			tag.appendAlbum(p.Str(60));
			
			switch(p.Byte()) {
				case 1: tag.add("speed", "slow"); break;
				case 2: tag.add("speed", "medium"); break;
				case 3: tag.add("speed", "fast"); break;
				case 4: tag.add("speed", "hardcore"); break;
				default: tag.add("speed", "unset"); 
			}

			tag.addGenre(p.Str(30));
			tag.add("start_time", p.Str(6)); // mmm:ss
			tag.add("end_time", p.Str(6)); // mmm:ss
		}
		
		return tag;
	}
}