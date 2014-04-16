import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class RequestSocket {

	private Socket socket;
	private Request request;
	
	public RequestSocket(Socket socket) {
		this.socket = socket;
	}

	
	public void run() throws Exception{
		
		//Get request
		this.request = new Request(socket);
		
		//checkFile();
		
		sendReply();
	}
	
	
	private void checkFile(){
		
	}
	
	private void sendReply() throws IOException{
		
		String head = replyHead();
		
		File file = new File(Server.rootDirectory, request.getFileName());
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
	
	
}
