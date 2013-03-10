package jtag.tags.id3v2.io;

public class Encodings {
	public static char nil = '\0';
	
	static EncodeInfo [] encodings = { 
		new EncodeInfo("ISO-8859-1", "" + nil),
		new EncodeInfo("UTF-16", nil + "" + nil),
		new EncodeInfo("UTF-16BE", nil + "" + nil),
		new EncodeInfo("UTF-8", "" + nil)};
	
	public static EncodeInfo getEncoding(byte i) { return encodings[i]; }
	
	public static class EncodeInfo {
		public String name;
		public String null_terminate;
		
		public EncodeInfo(String enc_name, String null_str) {
			name = enc_name;
			null_terminate = null_str;
		}
	}
}