package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class FmtChunk extends RIFFChunk {
	public String compression;
	public short channels; // Mono = 1, Stereo = 2, etc.
	public int sampleRate; // 8000, 44100, etc.
	public int byterate; //== SampleRate * NumChannels * BitsPerSample/8
	public short blockAlign; //== NumChannels * BitsPerSample/8
            //The number of bytes for one sample including all channels.
	public short bitsPerSample; //8 bits = 8, 16 bits = 16, etc.
	
	public FmtChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {	
		int compressionVal = p.UIShort(); 
		switch (compressionVal) {
			case 0: compression = "Unknow"; 					break;
			case 1: compression = "PCM/Uncompressed";			break;
			case 2: compression = "Microsoft ADPCM"; 			break;
			case 6: compression = "ITU G.711 a-law"; 			break;
			case 7: compression = "ITU G.711 Am-law"; 			break;
			case 17: compression = "IMA ADPCM"; 				break;
			case 20: compression = "ITU G.723 ADPCM (Yamaha)"; 	break;
			case 49: compression = "GSM 6.10"; 					break;
			case 64: compression = "ITU G.721 ADPCM"; 			break;
			case 80: compression = "MPEG"; 						break;
			case 65535: compression = "Experimental"; 			break;			
			default: compression = "Unclassified"; 				break;
		}
		
		channels = p.UShort();
		sampleRate = p.UInt();
		byterate = p.UInt();
		blockAlign = p.UShort();
		bitsPerSample = p.UShort();
		
//		channels = buff.get(10);
//		sampleRate = Parseable.toU(buff.get(15)) * 16777216 + Parseable.toU(buff.get(14)) * 65536 + Parseable.toU(buff.get(13)) * 256 + Parseable.toU(buff.get(12));
//		bytesPerSecond = Parseable.toU(buff.get(19)) * 16777216 + Parseable.toU(buff.get(18)) * 65536 + Parseable.toU(buff.get(17)) * 256 + Parseable.toU(buff.get(16));
//		bytesPerSample = Parseable.toU(buff.get(22));		
		
		if (length > 16) {
			short extraParamsSize = p.UShort();
			p.skip(extraParamsSize);
		}
	}
	
	public float timeLength(int fileLength) { return (float)(fileLength - 36) / byterate;}
	
	public int bitrate() { return byterate * 8 / 1000; }
		
	public boolean isValidLength() { return true;} // length == 16 + extraParamsSize
}