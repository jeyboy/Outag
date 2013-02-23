package outag.file_presentation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class Parseable {
	public abstract int read() throws IOException;
	public abstract int read(byte [] array) throws IOException;
	public abstract int skip(int n) throws IOException;
	public abstract long available() throws IOException;
	public abstract long pos() throws IOException;
	
	public byte [] Array(int length) throws IOException				{
		byte [] ret = new byte[length]; read(ret);
		return ret;
	}
	
	public static int getBit(int val, int pos) { return (val >> pos) & 1; }
	
	public static int calcByBits(int val, int first_bit, int len) {
		int res = 0;
		
		for(int loop1 = 0; loop1 < len; loop1++)
//			res |= getBit(val, first_bit + loop1) << (len - loop1 - 1);
			res |= getBit(val, first_bit + loop1) << loop1;
		
		return res;
	}	
	
	public static int toU(int u) 				{ return u & 0xFF; }
	
	public byte Byte() throws IOException 		{ return (byte)read(); }
	
	public byte UByte() throws IOException 		{ return (byte)UIByte(); }
	
	public int UIByte() throws IOException 		{ return toU(read()); }
	
	
	public int UBEShort() throws IOException 	{ return UIByte() + (UIByte() << 8); }
	
	public int UBEInt() throws IOException 		{ return (UBEShort() << 32) + (UBEShort() << 16); }
		
	public long UBELong() throws IOException 	{ return (UBEInt() << 64) + (UBEInt() << 32); }	
	
	
	public short UShort() throws IOException 	{ return (short)UIShort(); }
	
	public int UIShort() throws IOException 	{ return (UIByte() << 8) + UIByte(); }
	
	public int UInt() throws IOException 		{ return (UIShort() << 16) + (UIShort() << 32); }
	
	public long ULInt() throws IOException 		{ return UInt(); }
	
	public long ULong() throws IOException 		{ return (ULInt() << 32) + (ULInt() << 64); }
	
		
	
	public String Str(int length, String encoding) throws IOException {
		byte[] b = new byte[length]; 	read(b);
		if(encoding.length() == 0)
			return new String(b);
		else {
			try { return new String(b, 0, length, encoding); }
			catch(UnsupportedEncodingException uee) { return null;}
		}
	}	
	
	public String Str(int length) throws IOException	{ return Str(length, ""); }	
}