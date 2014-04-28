package ServerMain;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import PageGeneration.pageGenerator;


public class RequestSocket {

	boolean validPageNeedsGeneration;
	
	private Socket socket;
	private Request request;
	
	public RequestSocket(Socket socket) {
		this.socket = socket;
		validPageNeedsGeneration = false;
	}

	
	public void run() throws Exception{
		
		//Get request
		this.request = new Request(socket);
		
		
		//Is the requested file valid?
		
		checkFile();
		
		
		if(validPageNeedsGeneration)
			sendGeneratedPage();
		else
			sendFile();
	}
	
	/**
	 * Checks if the file either exists or if it needs to be generated automatically
	 */
	private void checkFile(){
		
		System.out.println(request.getFileName());
		
		if(request.getFileName().contains("submitEmail.html"))
			validPageNeedsGeneration = true;
		
	}
	
	private void sendGeneratedPage() throws IOException{
		//Can either make file, send using sendFile() + clean up...
		//Or rewrite all the socket stuff from sendFile()
		//Shall do the first here for now.
		
		//Create file
		File tempFile = generatePageFile("test", "<h1>An Automatically Generated Page</h1>"
				+ "\n<p>This page was created just for you!</p>"
				+ "\n<p>It will get deleted as soon as it has been sent, and then remade for the next guy</p>");
		
		
		//Set new file in Request + send
		request.setFile(tempFile.getName());
		
		sendFile();
		
		
		//cleanup
		FileUtils.deleteQuietly(tempFile);
		
	}
	
	
	/**
	 * Creates a temp file to send. Returns a File.
	 * @throws IOException 
	 */
	private File generatePageFile(String title, String body) throws IOException{
		
		File f = new File(Server.getRootDirectory(), "temp.html");
		
		FileUtils.write(f, pageGenerator.generatePage(title, body));
		
		
		return f;
	}
	
	
	private void sendFile() throws IOException{
		
		String head = replyHead();
		
		File file = new File(Server.getRootDirectory(), request.getFileName());
		Logger.log(file.getPath());

		byte[] mybytearray = new byte[(int) file.length()];
		
		BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(file));
		
		fileIn.read(mybytearray, 0, mybytearray.length);
		
		OutputStream os = socket.getOutputStream();
		
		os.write(head.getBytes());
		os.flush();
		
		os.write(mybytearray, 0, mybytearray.length);
		os.flush();
		os.close();
		fileIn.close();
		
	}
	
	private String replyHead(){
		
		String head = "";
		
		head = head + "\n" + request.getVer() + " " + "200" + " " + "OK" + "\n\n";
		
		return head;
	}
	
	public static void runTesting(){
		try {
			System.out.println(pageGenerator.generatePage("Test", "<h1>Test!</h1>"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
