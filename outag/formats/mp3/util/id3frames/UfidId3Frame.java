package outag.formats.mp3.util.id3frames;

import java.io.UnsupportedEncodingException;

import outag.formats.generic.TagField;

public class UfidId3Frame extends Id3Frame {
	private String ownerId;
	private byte[] identifier;
	
	/*
	 * 0,1| frame flags
	 * 2,..,0X00| Owner ID
	 * xx,...| identifier (binary)
	 */
	
	public UfidId3Frame(byte[] raw, byte version) throws UnsupportedEncodingException {
		super(raw, version);
	}
	
	protected void populate(byte[] raw) {
		int i = indexOfFirstNull(raw, flags.length);
		
		if(i != -1)
			this.ownerId = new String(raw, flags.length, i-flags.length);
		else {
			this.ownerId = new String(raw, flags.length, raw.length-flags.length);
			this.identifier = new byte[0];
		}
		
		this.identifier = new byte[raw.length - i - 1];
		for(int j = 0; j<identifier.length; j++)
			this.identifier[j] = raw[i+1+j];
		
	}
	
	protected byte[] build() {
		byte[] own = this.ownerId.getBytes();
		
		//the return byte[]
		byte[] b = new byte[4 + 4 + flags.length + own.length + 1 + identifier.length];
		
		int offset = 0;
		copy(getIdBytes(), b, offset);        offset += 4;
		copy(getSize(b.length-10), b, offset); offset += 4;
		copy(flags, b, offset);               offset += flags.length;
		
		copy(own, b, offset);		offset += own.length;
		
		b[offset] = 0; 				offset += 1;
		
		copy(identifier, b, offset);
		
		return b;
	}
	
	public boolean isBinary() { return true; }
	
	public String getOwnerId() { return this.ownerId; }
	
	public byte[] getIdentifier() {	return this.identifier;	}
	
	public String getId() { return "UFID"; }
	
	public boolean isCommon() {	return false; }
	
	public void copyContent(TagField field) {
	    if(field instanceof UfidId3Frame) {
	        this.ownerId = ((UfidId3Frame)field).getOwnerId();
	        this.identifier = ((UfidId3Frame)field).getIdentifier();
	    }
	}
	
	public boolean isEmpty() {
	    return this.ownerId.equals("") || this.identifier.length == 0;
	}
	
	public String toString() { return "UFID : "+getOwnerId(); }
}