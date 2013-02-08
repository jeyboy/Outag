package outag.formats.mp4.util.tag;

import java.io.IOException;
import outag.file_presentation.JBBuffer;
import outag.formats.generic.Utils;

public class Mp4TagTextNumberField extends Mp4TagTextField {
    
    public Mp4TagTextNumberField(String id, String n) {
        super(id, n);
    }
    
    public Mp4TagTextNumberField(String id, JBBuffer raw) throws IOException {
        super(id, raw);
    }
    
    protected byte[] getDataBytes() {
        return Utils.getSizeBigEndian(Integer.parseInt(content));
    }
    
    protected void build(JBBuffer raw) throws IOException {
    	raw.skip(16);
    	this.content = raw.UInt() + "";
    }
}