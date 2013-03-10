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

				case "IPL": /*IPL*/ break; // Involved people list

				case "LNK": /*LNK*/ break; // Linked information

				case "MCI": /*MIC*/ break; // Music CD Identifier
				case "MLL": /*MLL*/ break; // MPEG location lookup table

				case "PIC": /*PIC*/ break; // Attached picture
				case "POP": /*POP*/ break; // Popularimeter

				case "REV": /*REV*/ break; // Reverb
				case "RVA": /*RVA*/ break; // Relative volume adjustment

				case "SLT": /*SLT*/ break; // Synchronized lyric/text
				case "STC": /*STC*/ break; // Synced tempo codes

				case "TAL": /*T__*/ break; // Album/Movie/Show title
				case "TBP": /*T__*/ break; // BPM (Beats Per Minute)
				case "TCM": /*TCM*/ break; // Composer
				case "TCO": /*TCO*/ break; // Genres
				case "TCR": /*T__*/ break; // Copyright message
				case "TDA": /*TDA*/ break; // Date
				case "TDY": /*T__*/ break; // Playlist delay (silence between songs)
				case "TEN": /*T__*/ break; // Encoded by
				case "TFT": /*TFT*/ break; // File type
				case "TIM": /*TIM*/ break; // Time
				case "TKE": /*T__*/ break; // Initial key // jtag.reference.MusicalKey
				case "TLA": /*TLA*/ break; // Language(s)
				case "TLE": /*T__*/ break; // Length in ms
				case "TMT": /*TMT*/ break; // Media type
				case "TOA": /*TOA*/ break; // Original artist(s)/performer(s)
				case "TOF": /*T__*/ break; // Original filename
				case "TOL": /*TOL*/ break; // Original Lyricist(s)/text writer(s)
				case "TOR": /*T__*/ break; // Original release year
				case "TOT": /*T__*/ break; // Original album/Movie/Show title
				case "TP1": /*T__*/ break; // Lead artist(s)/Lead performer(s)/Soloist(s)/Performing group
				case "TP2": /*T__*/ break; // Band/Orchestra/Accompaniment
				case "TP3": /*T__*/ break; // Conductor/Performer refinement
				case "TP4": /*T__*/ break; // Interpreted, remixed, or otherwise modified by
				case "TPA": /*T__*/ break; // Part of a set
				case "TPB": /*T__*/ break; // Publisher
				case "TRC": /*T__*/ break; // ISRC (International Standard Recording Code)
				case "TRD": /*T__*/ break; // Recording dates
				case "TRK": /*T__*/ break; // Track number/Position in set
				case "TSI": /*T__*/ break; // Size in bytes
				case "TSS": /*T__*/ break; // Software/hardware and settings used for encoding
				case "TT1": /*T__*/ break; // Content group description
				case "TT2": /*T__*/ break; // Title/Songname/Content description
				case "TT3": /*T__*/ break; // Subtitle/Description refinement
				case "TXT": /*TXT*/ break; // Lyricist/text writer
				case "TXX": /*TXX*/ break; // User defined text information frame
				case "TYE": /*T__*/ break; // Year

				case "UFI": /*UFI*/ break; // Unique file identifier
				case "ULT": /*ULT*/ break; // Unsychronized lyric/text transcription

				case "WAF": /*W__*/ break; // Official audio file webpage
				case "WAR": /*W__*/ break; // Official artist/performer webpage
				case "WAS": /*W__*/ break; // Official audio source webpage
				case "WCM": /*W__*/ break; // Commercial information
				case "WCP": /*W__*/ break; // Copyright/Legal information
				case "WPB": /*W__*/ break; // Publishers official webpage
				case "WXX": /*WXX*/ break; // User defined URL link frame
			}
		}

		return tag;
	}
}