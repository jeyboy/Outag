package outag.formats.mp4.util.tag;

import java.io.UnsupportedEncodingException;

import outag.formats.generic.TagField;
import outag.formats.generic.Utils;

public abstract class Mp4TagField implements TagField {
    protected String id;
    
    public Mp4TagField(String id) { this.id = id; }
    
    public Mp4TagField(String id, byte[] raw) throws UnsupportedEncodingException {
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
    
    protected abstract void build(byte[] raw) throws UnsupportedEncodingException ;
}