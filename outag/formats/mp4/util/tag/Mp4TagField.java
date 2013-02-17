package outag.formats.mp4.util.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import outag.file_presentation.JBBuffer;
import outag.formats.generic.TagField;
import outag.formats.generic.Utils;
import outag.formats.mp4.util.box.Mp4Box;

public abstract class Mp4TagField implements TagField {
    protected Mp4Box head;
    
    public static Mp4TagField parse(Mp4Box head, JBBuffer raw) throws Exception {
        if(head.getId().equals("trkn") || head.getId().equals("tmpo") )
            return new Mp4TagTextNumberField(head, raw);
        else if(head.getId().equals("\u00A9ART")  ||
        		head.getId().equals("\u00A9alb")  ||
        		head.getId().equals("\u00A9nam")  ||
        		head.getId().equals("\u00A9day")  ||
        		head.getId().equals("\u00A9cmt")  ||
        		head.getId().equals("\u00A9gen")  ||
        		head.getId().equals("\u00A9too")  ||
        		head.getId().equals("\u00A9wrt"))
            return new Mp4TagTextField(head, raw);
        else if(head.getId().equals("covr"))
            return new Mp4TagCoverField(head, raw);
        
        return new Mp4TagBinaryField(head, raw);    	
    }
    
    public Mp4TagField(Mp4Box head) { this.head = head; }
    
    public Mp4TagField(Mp4Box head, JBBuffer raw) throws Exception {
        this(head);
        build(head, raw);
    }
    
    public String getId() { return head.getId(); }

    public void isBinary(boolean b) { /* One cannot choose if an arbitrary block can be binary or not */ }

    public boolean isCommon() {
        return getId().equals("ART")   ||
        		getId().equals("alb")  ||
        		getId().equals("nam")  ||
        		getId().equals("trkn") ||
        		getId().equals("day")  ||
        		getId().equals("cmt")  ||
        		getId().equals("gen");
    }
    
    protected byte[] getIdBytes() { return Utils.getDefaultBytes(getId()); }
    
    protected abstract void build(Mp4Box head, JBBuffer raw) throws UnsupportedEncodingException, IOException, Exception ;
}