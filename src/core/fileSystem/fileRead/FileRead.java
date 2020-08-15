package core.fileSystem.fileRead;

import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {
	public static byte[] Read(String filePath)  {
		byte[] data = null;
		try {
			FileInputStream fr = new FileInputStream(filePath);
			data = fr.readAllBytes();
			fr.close();			
		}catch(IOException e) {
			//System.out.println("File Not Found ! At " + filePath);
		}
		return data;
	}
}
