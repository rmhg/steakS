package core.netlink.httpAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPResponse {
	String version = "HTTP/1.1";
	String Status_Code = "200";
	String Status_Str = "OK";
	Map<String, String> headers;
    byte[] dataum = null;
    public HTTPResponse() {
    	headers = new HashMap<String, String>();
    }
    public void addHeader(String key, String value) {
   	 headers.put(key, value);
    }
    public HTTPResponse(int status_code) {
    	headers = new HashMap<String, String>();
    	Status_Code = status_code + "";
    	switch(status_code) {
    	
    	case 200 : Status_Str = "OK";
    	break;
    	case 201 : Status_Str = "Created";
    	break;
    	case 202 : Status_Str = "Accepted";
    	break;
    	case 203 : Status_Str = "Non-Authorative Information";
    	break;
    	case 204 : Status_Str = "No Content";
    	break;
    	case 205 : Status_Str = "Reset Content";
    	break;
    	case 206 : Status_Str = "Partial Content";
    	break;
    	case 400 : Status_Str = "Bad Request";
    	break;
    	case 401 : Status_Str = "Bad Request";
    	break;
    	case 402 : Status_Str = "Bad Request";
    	break;
    	case 403 : Status_Str = "Bad Request";
    	break;
    	case 404 : Status_Str = "Not Found";
    	break;
    	case 405 :
    	break;
    	case 406 : 
    		break;
    	case 407 : 
    		break;
    	case 408 : 
    		break;
    		
    	
    	}
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
    public void Print() {
   	 String data = "";
   	 for(byte tmp : dataum) {
   		 data += (char)tmp;
   	 }
   	 System.out.println(data);
    }
    public void prepare() {
    	String rawdata = version +" " + Status_Code + " " + Status_Str + "\r\n";
        for(Entry<String, String> ent : headers.entrySet()) {
        	rawdata += ent.getKey() + " : " + ent.getValue() + "\r\n";
        }	
        rawdata += "\r\n";
        dataum = rawdata.getBytes();
    }
    public byte[] getRaw() {
    	return dataum;
    }
}
