package core.netlink.httpAPI;

import java.io.IOException;

import core.managers.responseAPI.ResourceManager;
import core.netlink.socketAPI.Server;
import utils.Utils;

public class HTTPServer {
  Server server;
  public static String connectedIp = "";
  public HTTPServer(int port, String ip) throws Exception {
	  server = new Server(1, ip, port);
  }
  public HTTPServer(int port) throws Exception {
	  server = new Server(port);
  }
  public void start(ResourceManager resource) throws IOException {
      while(true) {
     	  server.waitForConn();
     	  connectedIp = Server.connectedIp;
     	  System.out.println("Connected To " + Server.connectedIp);
          byte[] body = server.recvData();
          if(body == null || body.length == 0) {
         	 server.CloseConn();
         	 continue;
          }
     	  HTTP tmp = HTTPParser.HTTPParse(body);
          HTTPResponse http;
          Utils.Println(body);
          if(tmp.headers.containsKey("X-Forwarded-For")) {
        	  String[] stmp = tmp.headers.get("X-Forwarded-For").split(",");
        	  connectedIp = stmp[stmp.length - 1].strip();
          }
          String Req = tmp.top_line[1];
          byte[] data = resource.getData(Req);
          if(data == null) {
         	 http = new HTTPResponse(404); 
         	 http.prepare();           	 
          }
          else {
         	http = new HTTPResponse();
         	http.addHeader("Content-Type", resource.info);
         	http.addPayload(data);
          }
          server.sendData(http.getRaw());
          server.CloseConn();	 
      }
  }
}
