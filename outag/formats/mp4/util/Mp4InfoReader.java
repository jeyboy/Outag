package outag.formats.mp4.util;

import java.io.RandomAccessFile;
import outag.file_presentation.JBBuffer;
import outag.file_presentation.JBFile;
import outag.formats.EncodingInfo;
import outag.formats.mp4.util.box.Mp4Box;
import outag.formats.mp4.util.box.Mp4BoxIdentifier;
import outag.formats.mp4.util.box.Mp4FtypBox;
import outag.formats.mp4.util.box.Mp4MvhdBox;

public class Mp4InfoReader {
	
//    private boolean isTrackAtomVideo(Mp4BoxHeader boxHeader, ByteBuffer mvhdBuffer) throws IOException {
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MDIA.getFieldName());
//        if (boxHeader == null)
//        {
//            return false;
//        }
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MDHD.getFieldName());
//        if (boxHeader == null)
//        {
//            return false;
//        }
//        mvhdBuffer.position(mvhdBuffer.position() + boxHeader.getDataLength());
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MINF.getFieldName());
//        if (boxHeader == null)
//        {
//            return false;
//        }
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.VMHD.getFieldName());
//        if (boxHeader != null)
//        {
//            return true;
//        }
//        return false;
//    }	
	
	/** Read audio info from file. <br>
	 * The info is held in the mvdh and mdhd fields as shown below
	 * <pre>
	 * |--- ftyp
	 * |--- moov
	 * |......|
	 * |......|----- mvdh
	 * |......|----- trak
	 * |...............|----- mdia
	 * |.......................|---- mdhd
	 * |.......................|---- minf
	 * |..............................|---- smhd
	 * |..............................|---- stbl
	 * |......................................|--- stsd
	 * |.............................................|--- mp4a
	 * |......|----- udta
	 * |
	 * |--- mdat
	 * </pre> 
	 * @throws Exception */	
    public EncodingInfo read(RandomAccessFile raf) throws Exception {
        EncodingInfo info = new EncodingInfo();
        Mp4Box box = new Mp4Box();
        JBFile file = new JBFile(raf);

        box.find(file, Mp4BoxIdentifier.FTYP.getName());

        Mp4FtypBox ftyp = new Mp4FtypBox(file.Buffer(box.getLength()));
        info.setEncodingType(ftyp.getMajorBrand());

        //Get to the facts everything we are interested in is within the moov box, so just load data from file
        //once so no more file I/O needed        
        box.find(file, Mp4BoxIdentifier.MOOV.getName());       

        JBBuffer moovBuffer = file.Buffer(box.getLength());

        //Level 2-Searching for "mvhd" somewhere within "moov", we make a slice after finding header
        //so all get() methods will be relative to mvdh positions
        
        box.find(moovBuffer, Mp4BoxIdentifier.MVHD.getName());
        
        JBBuffer mvhdBuffer = moovBuffer.slice();
        Mp4MvhdBox mvhd = new Mp4MvhdBox(mvhdBuffer);
        info.setLength(mvhd.getLength());        

        //Advance position, TODO should we put this in box code ?
//        mvhdBuffer.position(mvhdBuffer.position() + boxHeader.getDataLength());
        mvhdBuffer.pos(box.getLength());

        //Level 2-Searching for "trak" within "moov"
        box.find(mvhdBuffer, Mp4BoxIdentifier.TRAK.getName());
        int endOfFirstTrackInBuffer = mvhdBuffer.pos() + box.getLength();

        //Level 3-Searching for "mdia" within "trak"
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MDIA.getFieldName());
//        if (boxHeader == null)
//        {
//            throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//        }
//        //Level 4-Searching for "mdhd" within "mdia"
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MDHD.getFieldName());
//        if (boxHeader == null)
//        {
//            throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//        }
//        Mp4MdhdBox mdhd = new Mp4MdhdBox(boxHeader, mvhdBuffer.slice());
//        info.setSamplingRate(mdhd.getSampleRate());
//
//        //Level 4-Searching for "hdlr" within "mdia"
//        /*We dont currently need to process this because contains nothing we want
//        mvhdBuffer.position(mvhdBuffer.position() + boxHeader.getDataLength());
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4NotMetaFieldKey.HDLR.getFieldName());
//        if (boxHeader == null)
//        {
//            throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//        }
//        Mp4HdlrBox hdlr = new Mp4HdlrBox(boxHeader, mvhdBuffer.slice());
//        hdlr.processData();
//        */
//
//        //Level 4-Searching for "minf" within "mdia"
//        mvhdBuffer.position(mvhdBuffer.position() + boxHeader.getDataLength());
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MINF.getFieldName());
//        if (boxHeader == null)
//        {
//            throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//        }
//
//        //Level 5-Searching for "smhd" within "minf"
//        //Only an audio track would have a smhd frame
//        int pos = mvhdBuffer.position();
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.SMHD.getFieldName());
//        if (boxHeader == null)
//        {
//            mvhdBuffer.position(pos);
//            boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.VMHD.getFieldName());
//            //try easy check to confirm that it is video
//            if(boxHeader!=null)
//            {
//                throw new CannotReadVideoException(ErrorMessage.MP4_FILE_IS_VIDEO.getMsg());
//            }
//            else
//            {
//                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//            }
//        }
//        mvhdBuffer.position(mvhdBuffer.position() + boxHeader.getDataLength());
//
//        //Level 5-Searching for "stbl within "minf"
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.STBL.getFieldName());
//        if (boxHeader == null)
//        {
//            throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
//        }
//
//        //Level 6-Searching for "stsd within "stbl" and process it direct data, dont think these are mandatory so dont throw
//        //exception if unable to find
//        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.STSD.getFieldName());
//        if (boxHeader != null)
//        {
//            Mp4StsdBox stsd = new Mp4StsdBox(boxHeader, mvhdBuffer);
//            stsd.processData();
//            int positionAfterStsdHeaderAndData = mvhdBuffer.position();
//
//            ///Level 7-Searching for "mp4a within "stsd"
//            boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.MP4A.getFieldName());
//            if (boxHeader != null)
//            {
//                ByteBuffer mp4aBuffer = mvhdBuffer.slice();
//                Mp4Mp4aBox mp4a = new Mp4Mp4aBox(boxHeader, mp4aBuffer);
//                mp4a.processData();
//                //Level 8-Searching for "esds" within mp4a to get No Of Channels and bitrate
//                boxHeader = Mp4BoxHeader.seekWithinLevel(mp4aBuffer, Mp4AtomIdentifier.ESDS.getFieldName());
//                if (boxHeader != null)
//                {
//                    Mp4EsdsBox esds = new Mp4EsdsBox(boxHeader, mp4aBuffer.slice());
//
//                    //Set Bitrate in kbps
//                    info.setBitrate(esds.getAvgBitrate() / 1000);
//
//                    //Set Number of Channels
//                    info.setChannelNumber(esds.getNumberOfChannels());
//
//                    info.setKind(esds.getKind());
//                    info.setProfile(esds.getAudioProfile());
//
//                    info.setEncodingType(EncoderType.AAC.getDescription());
//                }
//            }
//            else
//            {
//                //Level 7 -Searching for drms within stsd instead (m4p files)
//                mvhdBuffer.position(positionAfterStsdHeaderAndData);
//                boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.DRMS.getFieldName());
//                if (boxHeader != null)
//                {
//                    Mp4DrmsBox drms = new Mp4DrmsBox(boxHeader, mvhdBuffer);
//                    drms.processData();
//
//                    //Level 8-Searching for "esds" within drms to get No Of Channels and bitrate
//                    boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.ESDS.getFieldName());
//                    if (boxHeader != null)
//                    {
//                        Mp4EsdsBox esds = new Mp4EsdsBox(boxHeader, mvhdBuffer.slice());
//
//                        //Set Bitrate in kbps
//                        info.setBitrate(esds.getAvgBitrate() / 1000);
//
//                        //Set Number of Channels
//                        info.setChannelNumber(esds.getNumberOfChannels());
//
//                        info.setKind(esds.getKind());
//                        info.setProfile(esds.getAudioProfile());
//
//                        info.setEncodingType(EncoderType.DRM_AAC.getDescription());
//                    }
//                }
//                //Level 7-Searching for alac (Apple Lossless) instead
//                else
//                {
//                    mvhdBuffer.position(positionAfterStsdHeaderAndData);
//                    boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.ALAC.getFieldName());
//                    if (boxHeader != null)
//                    {
//                        //Process First Alac
//                        Mp4AlacBox alac = new Mp4AlacBox(boxHeader, mvhdBuffer);
//                        alac.processData();
//                        
//                        //Level 8-Searching for 2nd "alac" within box that contains the info we really want
//                        boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.ALAC.getFieldName());
//                        if (boxHeader != null)
//                        {
//                            alac = new Mp4AlacBox(boxHeader, mvhdBuffer);
//                            alac.processData();
//                            info.setEncodingType(EncoderType.APPLE_LOSSLESS.getDescription());
//                            info.setChannelNumber(alac.getChannels());
//                            info.setBitrate(alac.getBitRate()/1000);
//                        }
//                    }
//                }
//            }
//        }
//        //Set default channels if couldn't calculate it
//        if (info.getChannelNumber() == -1)
//        {
//            info.setChannelNumber(2);
//        }
//
//        //Set default bitrate if couldnt calculate it
//        if (info.getBitRateAsNumber() == -1)
//        {
//            info.setBitrate(128);
//        }
//
//        //This is the most likely option if cant find a match
//        if (info.getEncodingType().equals(""))
//        {
//            info.setEncodingType(EncoderType.AAC.getDescription());
//        }
//
//        logger.config(info.toString());
//
//        //Level 2-Searching for others "trak" within "moov", if we find any traks containing video
//        //then reject it if no track if not video then we allow it because many encoders seem to contain all sorts
//        //of stuff that you wouldn't expect in an audio track
//        mvhdBuffer.position(endOfFirstTrackInBuffer);
//        while(mvhdBuffer.hasRemaining())
//        {
//            boxHeader = Mp4BoxHeader.seekWithinLevel(mvhdBuffer, Mp4AtomIdentifier.TRAK.getFieldName());
//            if (boxHeader != null)
//            {
//                if(isTrackAtomVideo(ftyp,boxHeader,mvhdBuffer))
//                {
//                    throw new CannotReadVideoException(ErrorMessage.MP4_FILE_IS_VIDEO.getMsg());
//                }
//            }
//            else
//            {
//                break;
//            }
//        }

       
        
        
//        //Get to the facts
//        //1-Searching for "moov"
//        seek(raf, box, "moov");
//        
//        //2-Searching for "udta"
//        seek(raf, box, "mvhd");
//        
//        byte[] b = new byte[box.getOffset()];
//        raf.read(b);
//        
//        Mp4MvhdBox mvhd = new Mp4MvhdBox(b);
//        info.setLength(mvhd.getLength());

        return info;
    }
    
//    private void seek(RandomAccessFile raf, Mp4Box box, String id) throws IOException {
//        byte[] b = new byte[8];
//        raf.read(b);
//        box.update(b);
//        while(!box.getId().equals(id)) {
//            raf.skipBytes(box.getOffset());
//            raf.read(b);
//            box.update(b);
//        }
//    }
    
    /**
     * Reads metadata from mp4,
     * <p/>
     * <p>The metadata tags are usually held under the ilst atom as shown below<p/>
     * <p>Valid Exceptions to the rule:</p>
     * <p>Can be no udta atom with meta rooted immediately under moov instead<p/>
     * <p>Can be no udta/meta atom at all<p/>
     *
     * <pre>
     * |--- ftyp
     * |--- moov
     * |......|
     * |......|----- mvdh
     * |......|----- trak
     * |......|----- udta
     * |..............|
     * |..............|-- meta
     * |....................|
     * |....................|-- hdlr
     * |....................|-- ilst
     * |.........................|
     * |.........................|---- @nam (Optional for each metadatafield)
     * |.........................|.......|-- data
     * |.........................|....... ecetera
     * |.........................|---- ---- (Optional for reverse dns field)
     * |.................................|-- mean
     * |.................................|-- name
     * |.................................|-- data
     * |.................................... ecetere
     * |
     * |--- mdat
     * </pre
     */
}