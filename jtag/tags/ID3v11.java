package jtag.tags;

import java.io.IOException;

import jtag.formats.Tag;
import jtag.io.Parseable;
import jtag.reference.GenreTypes;
import jtag.tags.id3v2.io.Encodings;

/** ID3v1/ID3v1.1 implementation <br><b>(Not checked)</b> */
public class ID3v11 {	
	public ID3v11() {}
	
	public Tag read(Parseable p) throws IOException {
		Tag tag = new Tag();
		p.pos(p.length() - 128);
		
		if (p.Str(3).equals("TAG")) {
			tag.addTitle(p.Str(30));
			tag.addArtist(p.Str(30));
			tag.addAlbum(p.Str(30));
			tag.addYear(p.Str(4));
			
			String comment = p.Str(28);
			String piece = p.Str(2);
			
			if (piece.charAt(0) != Encodings.nil)
				comment += piece;
			else tag.addTrack(piece.substring(1));
			
			tag.addComment(comment);
			tag.addGenre(GenreTypes.getNameByCode(p.UByte()));
			
			new ID3v1PLUS().read(p, tag);
		}
		
		return tag;
	}
}