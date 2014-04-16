import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.io.*;


public class Logger {
	
	public static void log(String l){
		//Date + time
		String dateTime = (Calendar.DATE + "/" + (Calendar.MONTH + 1) + "/" + Calendar.YEAR + "\t" 
				+ Calendar.SECOND + ":" +Calendar.MINUTE + ":" + Calendar.HOUR);
		
		String toPrint =  dateTime + "\t" + l + System.lineSeparator();
		
		System.out.print(toPrint);
		
		File f = new File("log.txt");
		
		try{
			FileUtils.writeStringToFile(f, toPrint + l, true);
			
		} catch(IOException e){
		
			System.out.println("Logger error");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
