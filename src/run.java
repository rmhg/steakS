import java.util.HashMap;
import java.util.Map;

import core.managers.dbmsAPI.DBConn;
import core.managers.responseAPI.ResponseManager;



public class run {

	public static Map<String, String> opt(String[] args){
		Map<String, String> opt = new HashMap<String, String>();
		 for(int i = 0; i < args.length;i++) {
			 if(args[i].contains("--")) {
				 String m = args[i].split("--")[1];
				 switch(m) {
				 case "port" : opt.put("PORT", args[i + 1]);
					 break;
				 case "main" : opt.put("main", args[i + 1]);
					 break;
				 case "path" : opt.put("PATH", args[i + 1]);
					 break;
				 case "host" : opt.put("HOST", args[i + 1]);
					 break;
				 case "help" : System.out.println("--port <port> --path <path> --host <host> --main <main>");
					 break;
				 default :  System.out.println("Try Again With Correct Options");
				 }
			 }
		 }
		 return opt;
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length != 0) {
			Map<String, String> opts = opt(args);
			 if(opts.containsKey("main")) {
				 new ResponseManager(opts.get("main"));			 
			 }
			 else {
				 new ResponseManager(opts);
			 }	
		} else {
			new ResponseManager("main.cfg");
		}
		 
	}

}
