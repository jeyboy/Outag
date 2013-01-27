package outag.audioformats.asf.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import outag.audioformats.asf.data.AudioStreamChunk;
import outag.audioformats.asf.data.Chunk;
import outag.audioformats.asf.data.GUID;
import outag.audioformats.asf.data.StreamChunk;
import outag.audioformats.asf.data.VideoStreamChunk;
import outag.audioformats.asf.util.Utils;

/** Reads and interprets the data of the audio or video stream information chunk. */
public class StreamChunkReader {
	/** Shouldn't be used for now. */
	protected StreamChunkReader() { /* Nothin todo */ }

	/** Reads audio and video stream information.
	 * @param raf - input source.
	 * @param candidate - possible audio stream chunk
	 * @return Audio Stream Information. <code>null</code> if its not an audio stream object.
	 * @throws IOException - read errors */
	public static StreamChunk read(RandomAccessFile raf, Chunk candidate)
			throws IOException {
		if (raf == null || candidate == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		if (GUID.GUID_STREAM.equals(candidate.getGuid())) {
			raf.seek(candidate.getPosition());
			return new StreamChunkReader().parseData(raf);
		}
		return null;
	}

	/** Reads audio and video stream information.
	 * @param raf - input source.
	 * @return Audio Stream Information. <code>null</code> if its not an audio stream object.
	 * @throws IOException - read errors */
	private StreamChunk parseData(RandomAccessFile raf) throws IOException {
		StreamChunk result = null;
		long chunkStart = raf.getFilePointer();
		GUID guid = Utils.readGUID(raf);
		if (GUID.GUID_STREAM.equals(guid)) {
			BigInteger chunkLength = Utils.readBig64(raf);
			// Now comes GUID indicating whether stream content type is audio or
			// video
			GUID streamTypeGUID = Utils.readGUID(raf);
			if (GUID.GUID_AUDIOSTREAM.equals(streamTypeGUID)
					|| GUID.GUID_VIDEOSTREAM.equals(streamTypeGUID)) {

				// A guid is indicating whether the stream is error
				// concealed
				GUID errorConcealment = Utils.readGUID(raf);
				/*
				 * Read the Time Offset
				 */
				long timeOffset = Utils.readUINT64(raf);

				long typeSpecificDataSize = Utils.readUINT32(raf);
				long streamSpecificDataSize = Utils.readUINT32(raf);

				/*
				 * Read a bitfield. (Contains streamnumber, and whether
				 * the stream content is encrypted.)
				 */
				int mask = Utils.readUINT16(raf);
				int streamNumber = mask & 127;
				boolean contentEncrypted = (mask & (1 << 15)) != 0;
				
				/*
				 * Skip a reserved field
				 */
				raf.skipBytes(4);

				if (GUID.GUID_AUDIOSTREAM.equals(streamTypeGUID)) {
					/*
					 * Reading audio specific information
					 */
					AudioStreamChunk audioStreamChunk = new AudioStreamChunk(
							chunkStart, chunkLength);
					result = audioStreamChunk;

					/*
					 * read WAVEFORMATEX and format extension.
					 */
					long compressionFormat = Utils.readUINT16(raf);
					long channelCount = Utils.readUINT16(raf);
					long samplingRate = Utils.readUINT32(raf);
					long avgBytesPerSec = Utils.readUINT32(raf);
					long blockAlignment = Utils.readUINT16(raf);
					int bitsPerSample = Utils.readUINT16(raf);
					int codecSpecificDataSize = Utils.readUINT16(raf);
					byte[] codecSpecificData = new byte[codecSpecificDataSize];
					raf.readFully(codecSpecificData);

					audioStreamChunk.setCompressionFormat(compressionFormat);
					audioStreamChunk.setChannelCount(channelCount);
					audioStreamChunk.setSamplingRate(samplingRate);
					audioStreamChunk.setAverageBytesPerSec(avgBytesPerSec);
					audioStreamChunk.setErrorConcealment(errorConcealment);
					audioStreamChunk.setBlockAlignment(blockAlignment);
					audioStreamChunk.setBitsPerSample (bitsPerSample);
					audioStreamChunk.setCodecData (codecSpecificData);
				} else if (GUID.GUID_VIDEOSTREAM.equals(streamTypeGUID)) {
					/*
					 * Reading video specific information
					 */
					VideoStreamChunk videoStreamChunk = new VideoStreamChunk(
							chunkStart, chunkLength);
					result = videoStreamChunk;

					long pictureWidth = Utils.readUINT32(raf);
					long pictureHeight = Utils.readUINT32(raf);

					// Skipt unknown field
					raf.skipBytes(1);

					/*
					 * Now read the format specific data
					 */
					// Size of the data section
					Utils.readUINT16(raf);
					
					raf.skipBytes(16);
					byte[] fourCC = new byte[4];
					raf.read(fourCC);
	
					videoStreamChunk.setPictureWidth(pictureWidth);
					videoStreamChunk.setPictureHeight(pictureHeight);
					videoStreamChunk.setCodecId(fourCC);
				}

				/*
				 * Setting common values for audio and video
				 */
				result.setStreamNumber(streamNumber);
				result.setStreamSpecificDataSize(streamSpecificDataSize);
				result.setTypeSpecificDataSize(typeSpecificDataSize);
				result.setTimeOffset(timeOffset);
				result.setContentEncrypted(contentEncrypted);
			}
		}
		return result;
	}
}