package outag.formats.real;

import org.jaudiotagger.audio.generic.GenericTag;

public class RealTag extends GenericTag {
    public String toString() {
        String output = "REAL " + super.toString();
        return output;
    }
}