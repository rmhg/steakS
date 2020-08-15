package core.managers.responseAPI;


import java.util.Map;

import core.netlink.httpAPI.HTTPServer;
import core.netlink.socketAPI.Server;
import utils.Utils;
import core.configs.Config;
import core.fileSystem.fileRead.FileRead;


public class ResourceManager {
     Map<String, Map<String, String> > resource;
     String path;
     String currDir = "/";
     String resDir = "";
     public String info = "";
     public ResourceManager(String filepath,String cfgname) {
    	 path = filepath;
    	 resource = Config.getMap(filepath + cfgname);
     }
     public byte[] getData(String rou) {
    	 String ofilePath = "";
    	 String filename = "";
    	 if(rou.contains("cmd")) {
    		 String cmd = rou.split("cmd/")[1];
    		 byte[] cmdd = null;
    		 switch(cmd) {
    		 case "ip" : {
    			 info = "text/plain";
    			 cmdd = HTTPServer.connectedIp.getBytes();    			 
    		 }
    		 break;
    		 case "mwho":{
    			 info = "text/plain";
    			 cmdd =  "Hey This Is Some Kind Of Who Statement!".getBytes();
    		 }
    		 }
    		 return cmdd;
    	 }
    	 if(!rou.contains(".")) {
    		currDir = rou;
    		filename = resource.get("ROUTE").get(currDir);
    		resDir = resource.get("DIR").get(currDir);
    	 }
    	 ofilePath = rou + filename;
    	 byte[] fileData = null;
    	 fileData = FileRead.Read(path + resDir + ofilePath);
    	 if(fileData == null)
         fileData = FileRead.Read(path + ofilePath);
    	 info = getInfo(ofilePath);
    	 return fileData;

     }
     public static String getInfo(String filename) {
    	 String info = "";
    	 String ex = "";
    	 for(int i = 0,dot = 1000; i < filename.length();i++) {
    		 if(filename.charAt(i) == '.')
    			 dot = i;
    		if(i > dot) {
    			 ex += filename.charAt(i);    			 
    		 }
    	 }
    	 switch(ex){
    	 case "svg": info = "image/svg+xml";
    	 break;
    	 case "html" : info ="text/html";
    	 break;
    	 case "css" : info = "text/css";
    		 break;
    	 case "png" : info ="image/png";
    		 break;
    	 case "js" : info = "text/javascript";
    	 break;
    	 case "json": info = "application/json";
    		 break;
    		 
    	 }
    	 return info;
     }
}
