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
				case "AENC": break; // Audio encryption
				case "APIC": break; // Attached picture
				case "COMM": break; // Comments
				case "COMR": break; // Commercial frame
				case "ENCR": break; // Encryption method registration
				case "EQUA": break; // Equalization
				case "ETCO": break; // Event timing codes
				case "GEOB": break; // General encapsulated object
				case "GRID": break; // Group identification registration
				case "IPLS": break; // Involved people list
				case "LINK": break; // Linked information
				case "MCDI": break; // Music CD identifier
				case "MLLT": break; // MPEG location lookup table
				case "OWNE": break; // Ownership frame
				case "PRIV": break; // Private frame
				case "PCNT": break; // Play counter
				case "POPM": break; // Popularimeter
				case "POSS": break; // Position synchronisation frame
				case "RBUF": break; // Recommended buffer size
				case "RVAD": break; // Relative volume adjustment
				case "RVRB": break; // Reverb
				case "SYLT": break; // Synchronized lyric/text
				case "SYTC": break; // Synchronized tempo codes
				case "TALB": /*ETextFrame*/ break; // Album/Movie/Show title
				case "TBPM": /*ETextFrame*/ break; // BPM (beats per minute)]
				case "TCOM": /*ID3Utils.split*//*ETextFrame*/ break; // Composer
				case "TCON": /*ID3Utils.genres*//*ETextFrame*/ break; // Genres
				case "TCOP": /*ETextFrame*/ break; // Copyright message
				case "TDAT": /*ID3Utils.date*//*ETextFrame*/ break; // Date
				case "TDLY": /*ETextFrame*/ break; // Playlist delay
				case "TENC": /*ETextFrame*/ break; // Encoded by
				case "TEXT": /*ID3Utils.split*//*ETextFrame*/ break; // Lyricist/Text writer
				case "TFLT": break; // File type
				case "TIME": /*ID3Utils.time*//*ETextFrame*/ break; // Time
				case "TIT1": /*ETextFrame*/ break; // Content group description
				case "TIT2": /*ETextFrame*/ break; // Title/songname/content description
				case "TIT3": /*ETextFrame*/ break; // Subtitle/Description refinement
				case "TKEY": /*ETextFrame*/ break; // Initial key
				case "TLAN": break; // Language(s)
				case "TLEN": /*ETextFrame*/ break; // Length
				case "TMED": break; // Media type
				case "TOAL": /*ETextFrame*/ break; // Original album/movie/show title
				case "TOFN": break; // Original filename
				case "TOLY": break; // Original lyricist(s)/text writer(s)
				case "TOPE": break; // Original artist(s)/performer(s)
				case "TORY": break; // Original release year
				case "TOWN": break; // File owner/licensee
				case "TPE1": break; // Lead performer(s)/Soloist(s)
				case "TPE2": break; // Band/orchestra/accompaniment
				case "TPE3": break; // Conductor/performer refinement
				case "TPE4": break; // Interpreted, remixed, or otherwise modified by
				case "TPOS": break; // Part of a set
				case "TPUB": break; // Publisher
				case "TRCK": break; // Track number/Position in set
				case "TRDA": break; // Recording dates
				case "TRSN": break; // Internet radio station name
				case "TRSO": break; // Internet radio station owner
				case "TSIZ": break; // Size
				case "TSRC": break; // ISRC (international standard recording code)
				case "TSSE": break; // Software/Hardware and settings used for encoding
				case "TYER": break; // Year
				case "TXXX": break; // User defined text information frame
				case "UFID": break; // Unique file identifier
				case "USER": break; // Terms of use
				case "USLT": break; // Unsychronized lyric/text transcription
				case "WCOM": break; // Commercial information
				case "WCOP": break; // Copyright/Legal information
				case "WOAF": break; // Official audio file webpage
				case "WOAR": break; // Official artist/performer webpage
				case "WOAS": break; // Official audio source webpage
				case "WORS": break; // Official internet radio station homepage
				case "WPAY": break; // Payment
				case "WPUB": break; // Publishers official webpage
				case "WXXX": break; // User defined URL link frame
			}
		}

		return tag;
	}
}