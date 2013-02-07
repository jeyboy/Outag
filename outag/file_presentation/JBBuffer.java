package outag.file_presentation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class JBBuffer extends Parseable {
	ByteBuffer base;
	
	public JBBuffer(byte[] buf) { base = ByteBuffer.wrap(buf); }
	
	public JBBuffer(ByteBuffer buf) { base = buf; }

	public JBBuffer(int length) { base = ByteBuffer.allocate(length); }

	public byte get() { return base.get();	}
	
	public int read() { return base.get();	}

	public int read(byte[] array) throws IOException { base.get(array); return array.length; }

	public int skip(int n) { base.position(base.position() + n); return n; }
	
	public void pos(int new_pos) { base.position(new_pos); }
	
	public long pos() { return base.position(); }
	
	public long available() { return base.capacity() - base.position(); }
	
	public JBBuffer slice() { return new JBBuffer(base.slice()); }
	
	public void skipToEnd()	{ skip((int)available()); }
}