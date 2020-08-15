package core.netlink.httpAPI;

import java.util.HashMap;
import java.util.Map;

import utils.Utils;


class HTTP{
	public Map<String, String> headers = new HashMap<String, String>();
	public String[] top_line = null;
	public byte[] payload = null;	
}

public class HTTPParser {
     public static HTTP HTTPParse(byte[] body) {
    	 HTTP sample = new HTTP();
    	 String rawdata = Utils.byte2string(body);
    	 String[] data = rawdata.split("^\r\n$");
    	 String mdata = data[0];
    	 String[] mdatas = mdata.split("\r\n");
    	 sample.top_line = mdatas[0].split(" ");
    	 for(int i = 1; i < mdatas.length;i++) {
    		 String[] header = mdatas[i].split(":", 2);
    		 sample.headers.put(header[0].strip(), header[1].strip());
    	}
    	 if(data.length == 2) {
    		 sample.payload = data[1].getBytes();
    	 }
        return sample;
     }
}
