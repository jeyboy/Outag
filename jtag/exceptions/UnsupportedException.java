package jtag.exceptions;

/** Thrown if some element not supported. */
public class UnsupportedException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnsupportedException(String message) { super(message); }
}