package jtag.formats;

import java.io.File;

public class Utils {	
	/** Copies the bytes of <code>srd</code> to <code>dst</code> at the specified offset.
	 * @param src - The byte do be copied.
	 * @param dst - The array to copy to
	 * @param dstOffset - The start offset for the bytes to be copied. */
	public static void copy(byte[] src, byte[] dst, int dstOffset) {
		System.arraycopy(src, 0, dst, dstOffset, src.length);
	}
	
	
	/** @return The extension of the given file
	 * @param f - file */
	public static String getExtension(File f) {
		String name = f.getName().toLowerCase();
		int i = name.lastIndexOf( "." );
		return (i == -1) ? "" : name.substring(i + 1);
	}	
}