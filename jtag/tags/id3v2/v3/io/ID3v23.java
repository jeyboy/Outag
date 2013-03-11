package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.formats.Tag;
import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.*;

/** ID3v2 implementation <br><b>(Not checked)</b> */
public class ID3v23 {		
	public Tag read(Parseable p) throws IOException {
		Tag tag = new Tag();
		p.pos(0);
		String flag;
		ID3 head = new ID3(p);
		
		while(p.available() > 0) {
			flag = p.Str(4);
			switch(flag) {
				case "AENC": /*AENC*/ break; // Audio encryption
				case "APIC": /*APIC*/ break; // Attached picture
				case "COMM": /*COMM*/ break; // Comments
				case "COMR": /*COMR*/ break; // Commercial frame
				case "ENCR": /*ENCR*/ break; // Encryption method registration
				case "EQUA": /*EQUA*/ break; // Equalization
				case "ETCO": /*ETCO*/ break; // Event timing codes
				case "GEOB": /*GEOB*/ break; // General encapsulated object
				case "GRID": /*GRID*/ break; // Group identification registration
				case "IPLS": /*ETextFrame*/ break; // Involved people list
				case "LINK": /*LINK*/ break; // Linked information
				case "MCDI": /*TextFrame*/ break; // Music CD identifier
				case "MLLT": /*MLLT*/ break; // MPEG location lookup table
				case "OWNE": /*OWNE*/ break; // Ownership frame
				case "PRIV": /*PRIV*/ break; // Private frame
				case "PCNT": /*PCNT*/ break; // Play counter
				case "POPM": /*POPM*/ break; // Popularimeter
				case "POSS": /*POSS*/ break; // Position synchronisation frame
				case "RBUF": /*RBUF*/ break; // Recommended buffer size
				case "RVAD": /*RVAD*/ break; // Relative volume adjustment
				case "RVRB": /*RVRB*/ break; // Reverb
				case "SYLT": /*SYLT*/ break; // Synchronized lyric/text
				case "SYTC": /*SYTC*/ break; // Synchronized tempo codes
				case "TALB": /*ETextFrame*/ break; // Album/Movie/Show title
				case "TBPM": /*ETextFrame*/ break; // BPM (beats per minute)]
				case "TCOM": /*ID3Utils.split*//*ETextFrame*/ break; // Composer
				case "TCON": /*ID3Utils.genres*//*ETextFrame*/ break; // Genres
				case "TCOP": /*ETextFrame*/ break; // Copyright message
				case "TDAT": /*ID3Utils.date*//*ETextFrame*/ break; // Date
				case "TDLY": /*ETextFrame*/ break; // Playlist delay
				case "TENC": /*ETextFrame*/ break; // Encoded by
				case "TEXT": /*ID3Utils.split*//*ETextFrame*/ break; // Lyricist/Text writer
				case "TFLT": /*TFLT*/ break; // File type
				case "TIME": /*ID3Utils.time*//*ETextFrame*/ break; // Time
				case "TIT1": /*ETextFrame*/ break; // Content group description
				case "TIT2": /*ETextFrame*/ break; // Title/songname/content description
				case "TIT3": /*ETextFrame*/ break; // Subtitle/Description refinement
				case "TKEY": /*ETextFrame*/ break; // Initial key
				case "TLAN": /*TLAN*/ break; // Language(s)
				case "TLEN": /*ETextFrame*/ break; // Length
				case "TMED": /*TMED*/ break; // Media type
				case "TOAL": /*ETextFrame*/ break; // Original album/movie/show title
				case "TOFN": /*ETextFrame*/ break; // Original filename
				case "TOLY": /*ID3Utils.split*//*ETextFrame*/ break; // Original lyricist(s)/text writer(s)
				case "TOPE": /*ID3Utils.split*//*ETextFrame*/ break; // Original artist(s)/performer(s)
				case "TORY": /*ETextFrame*/ break; // Original release year
				case "TOWN": /*ETextFrame*/ break; // File owner/licensee
				case "TPE1": /*ID3Utils.split*//*ETextFrame*/ break; // Lead performer(s)/Soloist(s)
				case "TPE2": /*ETextFrame*/ break; // Band/orchestra/accompaniment
				case "TPE3": /*ETextFrame*/ break; // Conductor/performer refinement
				case "TPE4": /*ETextFrame*/ break; // Interpreted, remixed, or otherwise modified by
				case "TPOS": /*ETextFrame*/ break; // Part of a set
				case "TPUB": /*ETextFrame*/ break; // Publisher
				case "TRCK": /*ETextFrame*/ break; // Track number/Position in set
				case "TRDA": /*ETextFrame*/ break; // Recording dates
				case "TRSN": /*ETextFrame*/ break; // Internet radio station name
				case "TRSO": /*ETextFrame*/ break; // Internet radio station owner
				case "TSIZ": /*ETextFrame*/ break; // Size
				case "TSRC": /*ETextFrame*/ break; // ISRC (international standard recording code)
				case "TSSE": /*ETextFrame*/ break; // Software/Hardware and settings used for encoding
				case "TYER": /*ETextFrame*/ break; // Year
				case "TXXX": /*TXXX*/ break; // User defined text information frame
				case "UFID": /*UFID*/ break; // Unique file identifier
				case "USER": /*USER*/ break; // Terms of use
				case "USLT": /*USLT*/ break; // Unsychronized lyric/text transcription
				case "WCOM": /*TextFrame*/ break; // Commercial information
				case "WCOP": /*TextFrame*/ break; // Copyright/Legal information
				case "WOAF": /*TextFrame*/ break; // Official audio file webpage
				case "WOAR": /*TextFrame*/ break; // Official artist/performer webpage
				case "WOAS": /*TextFrame*/ break; // Official audio source webpage
				case "WORS": /*TextFrame*/ break; // Official internet radio station homepage
				case "WPAY": /*TextFrame*/ break; // Payment
				case "WPUB": /*TextFrame*/ break; // Publishers official webpage
				case "WXXX": /*WXXX*/ break; // User defined URL link frame
			}
		}

		return tag;
	}
}