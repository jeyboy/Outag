package outag.formats.mp4.util.box;

import outag.file_presentation.Parseable;
import outag.formats.exceptions.CannotReadException;

public class Mp4Box {
    private String id;
    private int length;
    
    public String getId() { return id; }
    
    public int getLength() { return length - 8; }
    
    public void init(Parseable f) throws Exception {
    	length = f.UInt();
        id = f.Str(4);
        if (length == 1) { // means what uses extra size fields because block size bigger than 2^32
        	throw new Exception("Long block size unsupported");
//        	length = f.ULong();
        }
    }
    
    public void find(Parseable f, String box_id) throws Exception {
    	init(f);
    	
    	try {
	    	while(!getId().equals(box_id)) {
	    		f.skip(getLength());
	    		init(f);
	    	}
    	}
    	catch(Exception e) { throw new CannotReadException("Not find box " + box_id); }
    }  
}