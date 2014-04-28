package ServerMain;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;


public class Server {

	private final int port = 80;
	public final static File rootDirectory = new File("C:\\Users\\Tom\\Documents\\Programming\\Goitstock");
	
	public int run(){
		
		//Create Server Socket
		ServerSocket servsocket = null;
		try{
			
			servsocket = new ServerSocket(port);
			
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			return 22;
		}
		
		
		boolean run = true;
		
		//RequestSocket.runTesting();
		
		while(run)
		{
			try {
				
				RequestSocket request = new RequestSocket(servsocket.accept());
				request.run();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static File getRootDirectory()
	{
		return rootDirectory;
	}
	
	
	public static void main(String[] args) {

		Logger.log("Starting server...");
		Server server = new Server();
		server.run();
		
		Logger.log("Closing server");

	}
}