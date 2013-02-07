package outag.formats.mp4.util.box;

import outag.file_presentation.Parseable;

public class Mp4Box {
    private String id;
    private int length;
    
    public String getId() { return id; }
    
    public int getLength() { return length - 8; }
//    [0, 0, 0, 51, 101, 115, 100, 115]
    public void init(Parseable f) throws Exception {
    	length = f.UInt();
        id = f.Str(4);
        if (length == 1) { // means what uses extra size fields because block size bigger than 2^32
        	throw new Exception("Long block size unsupported");
//        	length = f.ULong();
        }
    }
    
    public boolean find(Parseable f, boolean throw_error, String ... box_ids) throws Exception {
    	init(f);
    	
    	if (box_ids.length == 0) return f.available() > 0;
    	
    	try {
	    	while(f.available() > 0) {
	    		for(String box_id : box_ids)
	    			if (getId().equals(box_id))
	    				return true;
	    		
	    		f.skip(getLength());
	    		init(f);
	    	}
    	}
    	catch(Exception e) {  }
    	if (throw_error) throw new Error("Not find compatible headers");
    	return false;
    }  
}