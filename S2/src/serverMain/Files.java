package serverMain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Files {
	
	public static byte[] getPageContent(File file) throws IOException{
		
		return getFile(file);

	}
	
	
	private static byte[] getFile(File file) throws IOException
	{

		
		Logger.log(file.getPath());

		byte[] mybytearray = new byte[(int) file.length()];
		
		BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(file));
		
		fileIn.read(mybytearray, 0, mybytearray.length);
		
		fileIn.close();
		
		return mybytearray;
	}
	
	private static boolean fileExists(File file){
		
		
		
		return true;
	}

}
