package core.configs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * Configuration Files Are cfg Text Files Which Use To Configure The HTServer
 * There Are  Two Types Of Token Declaration 
 * if Two Identifier are separated by -> than its a Simple Key Value Pair
 * If A Identifier Tailed By a : Than Its a Label To Which any Followed Key Value Pair Belonged If Its Not Overriden
 * By A Label
 * Format (Angle Bracket Are Not Part Of Format)
 *  <Label> :    //Label
 *  <key> -> <value> //Key Value
 *  <key>,<key>... -> <value> //Multiple Key Single Value
 *  ----------------------------------------------------------------------------------------------------------
 *  In HTServer There Are  Types Of Config File
 *  1. Main
 *  2. Web
 *  3. Variable
 *  
 *  In Main It Define HOST IP and Path For Host Files
 *  KeyWord For Host Ip Is HOST and for file(web config file) PATH 
 *  Sample Main.cfg :-
 *  HOST -> localhost
 *  PORT -> 80
 *  PATH -> /website/
 * 
 *  
 */
public class Config {
	public static Map<String, Map<String, String> > getMap(String filepath)  {
		Map<String, Map<String, String> > keyval = null;
		try {
			keyval = new HashMap<String,Map<String, String> >();
			String Label = "";
			FileInputStream fr = new FileInputStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fr));
			String line = "";
			while((line = br.readLine()) != null) {
			         if(line.contains("->")) {
			        	 String[] tmps = line.split("->", 2);
			        	 Map<String, String> vals = keyval.get(Label);
			        	 for(String stmp : tmps[0].split(","))
			        	 vals.put(stmp.strip(), tmps[1].strip());
			         }
			         else {
			        	 Label = line.split(":")[0].strip();
			        	 keyval.put(Label, new HashMap<String, String>());
			         }
				}
		   br.close();
		   fr.close();
		}
		catch(IOException e) {
			System.out.println("Cant Open The File : " + filepath);
		}
		
	   return keyval;
	}	
}
