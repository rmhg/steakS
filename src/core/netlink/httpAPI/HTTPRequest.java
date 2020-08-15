package core.netlink.httpAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPRequest {
	 String type = "GET";
	 String path = "/";
	 String version = "HTTP/1.1";
	 String line = "";
	 Map<String, String> headers;
	 byte[] dataum = null;
	 public HTTPRequest() {
		 headers = new HashMap<String, String>();
		 line = type + " " + path + " " +  version;
	 }
     public void addHeader(String key, String value) {
    	 headers.put(key, value);
     }
     public void addPayload(byte[] data) {
    	 headers.put("Content-Length", data.length + "");
         prepare();     
         byte[] tmp = new byte[dataum.length + data.length];
         for(int i = 0; i < dataum.length;i++) {
        	 tmp[i] = dataum[i];
         }
         for(int i = dataum.length,in = dataum.length; i < tmp.length;i++) {
        	 tmp[i] = data[i - in];
         }
         dataum = tmp;
     }
     public void prepare() {
    	 String rawdata = "";
    	 rawdata += line + "\r\n";
    	 for(Entry<String, String> ent : headers.entrySet()) {
    		 rawdata += ent.getKey() + " : " + ent.getValue() + "\r\n";
    	 }
    	 rawdata += "\r\n";
    	 dataum = rawdata.getBytes();
     }
     public void Print() {
    	 String data = "";
    	 for(byte tmp : dataum) {
    		 data += (char)tmp;
    	 }
    	 System.out.println(data);
     }
     public byte[] getRaw() {
         return dataum;
     }
}
