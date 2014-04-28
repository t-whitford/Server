package ServerMain;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import Database.DB;
import Database.DatabaseException;


public class Server {

	private final int port = 80;
	private final static File rootDirectory = new File("C:\\Users\\Tom\\Documents\\Programming\\Goitstock");
	
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
		
		
		try {
			DB db = new DB();
			db.executeQuery();
		} catch (DatabaseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		boolean run = false;
		
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