package jtag.formats.wav;

import java.io.IOException;

import jtag.formats.EncodingInfo;
import jtag.formats.wav.io.FmtChunk;
import jtag.formats.wav.io.Header;
import jtag.io.Parseable;

public class RIFFParser {
	public RIFFParser() {}
	
	public EncodingInfo read(Parseable p) throws IOException {
		EncodingInfo info = new EncodingInfo();
		String fourcc;
		Header head = null;

		while(p.available() > 0) {
			fourcc = p.Str(4);			
			
			switch(fourcc) {
				case "RIFF": head = new Header(p); break;
				case "fmt ": FmtChunk fmt = new FmtChunk(p);
					info.setPreciseLength(fmt.timeLength(head.length));
					info.setChannelNumber(fmt.channels);
					info.setSamplingRate(fmt.sampleRate);
					info.setEncodingType("WAV-RIFF " + fmt.compression);
					info.setBitrate(fmt.bitrate());
					info.setVbr(false);				
					return info;
				case "data": //DataChunk data;
					break;
				case "fact": //FactChunk fact; 
					break;
				case "ltxt": //LabeledTextChunk ltxt; 
					break;
				case "labl": //LabelChunk labl; 
					break;
				case "inst": //InstrumentChunk inst; 
					break;
				case "list": //DataListChunk list; 
					break;
				case "cue ": //CueChunk cue; 
					break;
				case "note": //NoteChunk note; 
					break;
				case "plst": //PlaylistChunk plst; 
					break;
				case "smpl": //SamplerChunk smpl; 
					break;
				case "slnt": //SilentChunk slnt; 
					break;
				case "wavl": //WaveListChunk wavl; 
					break;
				default: return info;
			}
		}
		return info;
	}
}