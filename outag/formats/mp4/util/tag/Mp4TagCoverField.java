package outag.formats.mp4.util.tag;

import java.io.IOException;
import outag.file_presentation.JBBuffer;

public class Mp4TagCoverField extends Mp4TagBinaryField {
    public Mp4TagCoverField() { super("covr"); }
    
    public Mp4TagCoverField(JBBuffer raw) throws IOException {
        super("covr", raw);
    }
    
    public boolean isBinary() { return true; }
}