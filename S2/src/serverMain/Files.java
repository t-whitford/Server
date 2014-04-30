package serverMain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Files {
	
	public static byte[] getPageContent(String fileName) throws IOException{
		
		
		File file = new File(Server.getRootDirectory(), fileName);
		Logger.log(file.getPath());

		byte[] mybytearray = new byte[(int) file.length()];
		
		BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(file));
		
		fileIn.read(mybytearray, 0, mybytearray.length);
		
		return mybytearray;
	}

}
