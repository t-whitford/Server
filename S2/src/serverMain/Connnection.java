package serverMain;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.FileUtils;

import pageGeneration.pageGenerator;


public class Connnection {

	boolean validPageNeedsGeneration;
	
	private Socket socket;
	private Request request;
	//private Reply reply;
	
	public Connnection(Socket socket) {
		this.socket = socket;
		//reply = new Reply();
		request = new Request(socket, new Reply());
		
	}

	
	public void run() throws Exception{
		
		//Get request
		request.getRequest();
		
		
		//If HEAD need to only send head
		if(request.getRequestType() == 2)
			request.getReply().setOnlySendHead(true);
		//If POST need to process body
		if(request.getRequestType() == 3)
			PostHandler.processPost(request);
		
		
		//Build reply
		request.getReply().buildReply();
		
		sendReply();
		
		
	}
	
	
	public void sendReply() throws IOException
	{
		
		//byte[] reply = Files.getPageContent(request.getFileName());
		byte[] replyContent = request.getReply().getReply().getBytes();
		
		OutputStream os = socket.getOutputStream();
		
		os.write(replyContent);
		os.flush();
		os.close();		

		socket.close();
		
	}
	
//	private void sendGeneratedPage() throws IOException{
//		//Can either make file, send using sendFile() + clean up...
//		//Or rewrite all the socket stuff from sendFile()
//		//Shall do the first here for now.
//		
//		//Create file
//		File tempFile = generatePageFile("test", "<h1>An Automatically Generated Page</h1>"
//				+ "\n<p>Attempted to save email.</p>");
//		
//		
//		//Set new file in Request + send
//		request.setFile(tempFile.getName());
//		
//		sendReply();
//		
//		
//		//cleanup
//		FileUtils.deleteQuietly(tempFile);
//		
//	}
//	
//	
//	/**
//	 * Creates a temp file to send. Returns a File.
//	 * @throws IOException 
//	 */
//	private File generatePageFile(String title, String body) throws IOException{
//		
//		File f = new File(Server.getRootDirectory(), "temp.html");
//		
//		FileUtils.write(f, pageGenerator.generatePage(title, body));
//		
//		
//		return f;
//	}
//	
//	private void sendReply() throws IOException{
//		
//
//		String head = replyHead();
//		
//		byte[] body = Files.getPageContent(request.getFileName());
//		
//		OutputStream os = socket.getOutputStream();
//		
//		os.write(head.getBytes());
//		os.flush();
//		
//		os.write(body);
//		os.flush();
//		os.close();		
//	}
//	
//	private String replyHead(){
//		
//		String head = "";
//		
//		head = head + "\n" + request.getVer() + " " + "200" + " " + "OK" + "\n\n";
//		
//		return head;
//	}
	
}
