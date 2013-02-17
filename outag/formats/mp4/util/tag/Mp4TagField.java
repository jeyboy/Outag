package outag.formats.mp4.util.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import outag.file_presentation.JBBuffer;
import outag.formats.generic.TagField;
import outag.formats.generic.Utils;

public abstract class Mp4TagField implements TagField {
    protected String id;
    
    public static Mp4TagField parse(String id, JBBuffer raw) throws Exception {
        if(id.equals("trkn") || id.equals("tmpo") )
            return new Mp4TagTextNumberField(id, raw);
        else if(id.equals("\u00A9ART")  ||
		        id.equals("\u00A9alb")  ||
		        id.equals("\u00A9nam")  ||
		        id.equals("\u00A9day")  ||
		        id.equals("\u00A9cmt")  ||
		        id.equals("\u00A9gen")  ||
		        id.equals("\u00A9too")  ||
		        id.equals("\u00A9wrt"))
            return new Mp4TagTextField(id, raw);
        
        else if(id.equals("covr"))
            return new Mp4TagCoverField(raw.get(new byte[(int) raw.available()]));
        
        return new Mp4TagBinaryField(id, raw);    	
    }
    
    public Mp4TagField(String id) { this.id = id; }
    
    public Mp4TagField(String id, JBBuffer raw) throws Exception {
        this(id);
        build(raw);
    }
    
    public String getId() { return id; }

    public void isBinary(boolean b) { /* One cannot choose if an arbitrary block can be binary or not */ }

    public boolean isCommon() {
        return id.equals("ART")   ||
		        id.equals("alb")  ||
		        id.equals("nam")  ||
		        id.equals("trkn") ||
		        id.equals("day")  ||
		        id.equals("cmt")  ||
		        id.equals("gen");
    }
    
    protected byte[] getIdBytes() { return Utils.getDefaultBytes(getId()); }
    
    protected abstract void build(JBBuffer raw) throws UnsupportedEncodingException, IOException, Exception ;
}