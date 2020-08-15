package utils;

public class Utils {
   public static String byte2string(byte[] data) {
	   String raw = "";
	   if(data == null)
		   return raw;
	   for(byte tmp : data) 
		   raw += (char)tmp;
	   return raw;
   } 
   public static void Println(String data) {
	   System.out.println(data);
   }
   public static void Println(byte[] data) {
	   System.out.println(byte2string(data));
   }
}
