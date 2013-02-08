package outag.formats.mp4.util.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import outag.file_presentation.JBBuffer;
import outag.formats.generic.TagField;
import outag.formats.generic.TagTextField;
import outag.formats.generic.Utils;

public class Mp4TagTextField extends Mp4TagField implements TagTextField {
	protected String content;

	public Mp4TagTextField(String id, JBBuffer raw) throws IOException {
		super(id, raw);
	}

	public Mp4TagTextField(String id, String content) {
		super(id);
		this.content = content;
	}

	protected void build(JBBuffer raw) throws IOException {
		int dataSize = raw.UInt();
		raw.skip(12); // to 16 byte
		this.content = raw.Str(dataSize - 16, getEncoding());
	}

	public void copyContent(TagField field) {
		if (field instanceof Mp4TagTextField)
			this.content = ((Mp4TagTextField) field).getContent();
	}

	public String getContent() { return content; }

	// This is overridden in the number data box
	protected byte[] getDataBytes() throws UnsupportedEncodingException {
		return content.getBytes(getEncoding());
	}

	public String getEncoding() { return "ISO-8859-1"; }

	public byte[] getRawContent() throws UnsupportedEncodingException {
		byte[] data = getDataBytes();
		byte[] b = new byte[4 + 4 + 4 + 4 + 4 + 4 + data.length];

		int offset = 0;
		Utils.copy(Utils.getSizeBigEndian(b.length), b, offset);
		offset += 4;

		Utils.copy(Utils.getDefaultBytes(getId()), b, offset);
		offset += 4;

		Utils.copy(Utils.getSizeBigEndian(4 + 4 + 4 + 4 + data.length), b,
				offset);
		offset += 4;

		Utils.copy(Utils.getDefaultBytes("data"), b, offset);
		offset += 4;

		Utils.copy(new byte[] { 0, 0, 0, (byte) (isBinary() ? 0 : 1) }, b,
				offset);
		offset += 4;

		Utils.copy(new byte[] { 0, 0, 0, 0 }, b, offset);
		offset += 4;

		Utils.copy(data, b, offset);
		offset += data.length;

		return b;
	}

	public boolean isBinary() { return false; }

	public boolean isEmpty() { return this.content.trim().equals(""); }

	public void setContent(String s) { this.content = s; }

	public void setEncoding(String s) {	/* Not allowed */ }

	public String toString() { return content; }
}