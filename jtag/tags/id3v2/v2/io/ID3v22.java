package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.formats.Tag;
import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.*;

/** ID3v2 implementation <br><b>(Not checked)</b> */
public class ID3v22 {		
	public Tag read(Parseable p) throws IOException {
		Tag tag = new Tag();
		p.pos(0);
		String flag;
		ID3 head = new ID3(p);
		
		while(p.available() > 0) {
			flag = p.Str(3);
			switch(flag) {
				case "BUF": /*BUF*/ break; // Recommended buffer size
				case "CNT": /*CNT*/ break; // Play counter
				case "COM": /*COM*/ break; // Comments
				case "CRA": /*CRA*/ break; // Audio encryption
				case "CRM": /*CRM*/ break; // Encrypted meta frame

				case "ETC": /*ETC*/ break; // Event timing codes
				case "EQU": /*EQU*/ break; // Equalization

				case "GEO": /*GEO*/ break; // General encapsulated object

				case "IPL": /*ETextFrame*/ break; // Involved people list

				case "LNK": /*LNK*/ break; // Linked information

				case "MCI": /*TextFrame*/ break; // Music CD Identifier
				case "MLL": /*MLL*/ break; // MPEG location lookup table

				case "PIC": /*PIC*/ break; // Attached picture
				case "POP": /*POP*/ break; // Popularimeter

				case "REV": /*REV*/ break; // Reverb
				case "RVA": /*RVA*/ break; // Relative volume adjustment

				case "SLT": /*SLT*/ break; // Synchronized lyric/text
				case "STC": /*STC*/ break; // Synced tempo codes

				case "TAL": /*ETextFrame*/ break; // Album/Movie/Show title
				case "TBP": /*ETextFrame*/ break; // BPM (Beats Per Minute)
				case "TCM": /*ID3Utils.split*//*T__*/ break; // Composer
				case "TCO": /*ID3Utils.genres*//*T__*/ break; // Genres
				case "TCR": /*ETextFrame*/ break; // Copyright message
				case "TDA": /*ID3Utils.date*//*T__*/ break; // Date
				case "TDY": /*ETextFrame*/ break; // Playlist delay (silence between songs)
				case "TEN": /*ETextFrame*/ break; // Encoded by
				case "TFT": /*TFT*/ break; // File type
				case "TIM": /*ID3Utils.time*//*T__*/ break; // Time
				case "TKE": /*ETextFrame*/ break; // Initial key // jtag.reference.MusicalKey
				case "TLA": /*TLA*/ break; // Language(s)
				case "TLE": /*ETextFrame*/ break; // Length in ms
				case "TMT": /*TMT*/ break; // Media type
				case "TOA": /*ID3Utils.split*//*T__*/ break; // Original artist(s)/performer(s)
				case "TOF": /*ETextFrame*/ break; // Original filename
				case "TOL": /*ID3Utils.split*//*T__*/ break; // Original Lyricist(s)/text writer(s)
				case "TOR": /*ETextFrame*/ break; // Original release year
				case "TOT": /*ETextFrame*/ break; // Original album/Movie/Show title
				case "TP1": /*ETextFrame*/ break; // Lead artist(s)/Lead performer(s)/Soloist(s)/Performing group
				case "TP2": /*ETextFrame*/ break; // Band/Orchestra/Accompaniment
				case "TP3": /*ETextFrame*/ break; // Conductor/Performer refinement
				case "TP4": /*ETextFrame*/ break; // Interpreted, remixed, or otherwise modified by
				case "TPA": /*ETextFrame*/ break; // Part of a set
				case "TPB": /*ETextFrame*/ break; // Publisher
				case "TRC": /*ETextFrame*/ break; // ISRC (International Standard Recording Code)
				case "TRD": /*ETextFrame*/ break; // Recording dates
				case "TRK": /*ETextFrame*/ break; // Track number/Position in set
				case "TSI": /*ETextFrame*/ break; // Size in bytes
				case "TSS": /*ETextFrame*/ break; // Software/hardware and settings used for encoding
				case "TT1": /*ETextFrame*/ break; // Content group description
				case "TT2": /*ETextFrame*/ break; // Title/Songname/Content description
				case "TT3": /*ETextFrame*/ break; // Subtitle/Description refinement
				case "TXT": /*ID3Utils.split*//*T__*/ break; // Lyricist/text writer
				case "TXX": /*TXX*/ break; // User defined text information frame
				case "TYE": /*ETextFrame*/ break; // Year

				case "UFI": /*UFI*/ break; // Unique file identifier
				case "ULT": /*ULT*/ break; // Unsychronized lyric/text transcription

				case "WAF": /*TextFrame*/ break; // Official audio file webpage
				case "WAR": /*TextFrame*/ break; // Official artist/performer webpage
				case "WAS": /*TextFrame*/ break; // Official audio source webpage
				case "WCM": /*TextFrame*/ break; // Commercial information
				case "WCP": /*TextFrame*/ break; // Copyright/Legal information
				case "WPB": /*TextFrame*/ break; // Publishers official webpage
				case "WXX": /*WXX*/ break; // User defined URL link frame
			}
		}

		return tag;
	}
}