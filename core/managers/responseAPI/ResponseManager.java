package core.managers.responseAPI;


import java.util.HashMap;
import java.util.Map;

import core.netlink.httpAPI.HTTPServer;
import core.configs.Config;

public class ResponseManager {
	String Host = "";
	int port = 0;
	int backlog =  10;
	String PATH = "";
	ResourceManager resource;
	HTTPServer server;
    public ResponseManager(String filepath) throws Exception {
    	System.out.println("ResponseManager : ResponseManager Started ! Retring Data From " + filepath + "...");
    	Map<String, String> main = Config.getMap(filepath).get("MAIN"); 
    	Host = main.get("HOST");
    	port = Integer.parseInt(main.get("PORT"));
    	PATH = main.get("PATH");
    	System.out.println("ResponseManager : Starting Server At " + Host + ":" + port + " | Backlogs : " + backlog);
    	resource = new ResourceManager(PATH,"config.cfg");
    	server = new HTTPServer(port, Host);
    	server.start(resource);
    }
    public ResponseManager(Map<String, String> main) throws Exception {
    	System.out.println("ResponseManager : ResponseManager Started !");
    	Host = main.get("HOST");
    	port = Integer.parseInt(main.get("PORT"));
    	PATH = main.get("PATH");
    	System.out.println("ResponseManager : Starting Server At " + Host + ":" + port + " | Backlogs : " + backlog);
    	resource = new ResourceManager(PATH,"config.cfg");
    	if(Host == null)
    		server = new HTTPServer(port);
    	else
    		server = new HTTPServer(port, Host);
    	server.start(resource);
    }
   
}
