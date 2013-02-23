package outag.formats.wv.util;

import outag.file_presentation.JBFile;
import outag.formats.EncodingInfo;
import outag.formats.wv.io.HeadChunk;

import java.io.*;

public class WvInfoReader {
	public EncodingInfo read(RandomAccessFile raf) throws IOException, Exception {
		EncodingInfo info = new EncodingInfo();
		JBFile f = new JBFile(raf);
		
		HeadChunk head = new HeadChunk(f);
		
		info.setVbr(!head.flags.isLossless);
		info.setChannelNumber(head.flags.isStereo ? 2 : 1);
		info.setSamplingRate(head.flags.getSampleRate());
		info.setPreciseLength(head.getDuration());
//		info.setBitrate(head.getAudioBitrate(stream_length));

		return info;
	}
}