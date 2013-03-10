package jtag.formats;

import java.util.Hashtable;

/** Represents a structure for storing and retrieving information
 * about the codec respectively the encoding parameters. */
public class EncodingInfo {
	/** Contains the parameters. */
	private Hashtable<String, Comparable<?>> content;
	private Hashtable<String, Comparable<?>> extra_content;

	/** Creates an instance with empty values. */
	public EncodingInfo() {
		content = new Hashtable<String, Comparable<?>>(7);
		content.put("bitrate", -1);
		content.put("channel", -1);
		content.put("type", "");
		content.put("compression", "Unknow");
		content.put("sample_rate", -1);
		content.put("length", -1f);
		content.put("vbr", false);
		
		extra_content = new Hashtable<String, Comparable<?>>(1);
	}

	public int getBitrate() { return (Integer) content.get("bitrate"); }
	public void setBitrate(int bitrate) { content.put("bitrate", bitrate);	}
	
	public int getChannelNumber() {	return (Integer) content.get("channel"); }
	public void setChannelNumber(int chanNb) { content.put("channel", chanNb);	}	
	
	public String getEncodingType() { return (String) content.get("type"); }
	public void setEncodingType(String encodingType) { content.put("type", encodingType); }
	
	public String getCompressionType() { return (String) content.get("compression"); }
	public void setCompressionType(String compression) { content.put("compression", compression); }	
	
	public Hashtable<String, Comparable<?>> getExtraEncodingInfos() { return extra_content; }
	public void setExtraEncodingInfos(String key, Comparable<?> val) { extra_content.put(key, val); }	

	/** @return duration in seconds. */
	public int getLength() { return (int) getPreciseLength(); }
	public void setLength(int length) {	content.put("length", length); }	

	/** @return duration in seconds. */
	public float getPreciseLength() { return (Float) content.get("length");	}
	public void setPreciseLength(float seconds) { content.put("length", seconds); }	

	/** @return sample rate in &quot;Hz&quot;. */
	public int getSamplingRate() { return ((Integer) content.get("sample_rate")).intValue();	}
	public void setSamplingRate(int samplingRate) {	content.put("sample_rate", samplingRate); }	

	/** @return <code>true</code> if audio is encoded with VBR. */
	public boolean isVbr() { return (Boolean) (content.get("vbr"));	}
	public void setVbr(boolean b) {	content.put("vbr", b);	}

	/** Pretty prints this encoding info */
	public String toString() {
		StringBuffer out = new StringBuffer(50);
		out.append("Encoding infos content:\n");
		for(String key : content.keySet()) {
			out.append("\t");
			out.append(key);
			out.append(" : ");
			out.append(content.get(key));
			out.append("\n");			
		}
		return out.toString().substring(0, out.length() - 1);
	}
}