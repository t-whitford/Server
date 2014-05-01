package serverMain;

import java.io.File;
import java.io.IOException;

import database.TempPages;

public class Reply {
	
	/**
	 * 
	 */
	private boolean bodySet;
	private boolean headSet;
	private boolean sendHeadOnly;
	private String head;
	private String body;
	private Request request;
	
	private boolean fileInSystem;
	private boolean fileInDB;
	
	public Reply()
	{
	}
	
	public void attachRequest(Request request)
	{
		this.request = request;
		
		bodySet = false;
		headSet = false;
		sendHeadOnly = false;
	}
	
	public Reply(String head, String body) {
		this.head = head;
		this.body = body;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
		headSet = true;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		bodySet = true;
	}
	
	public boolean isBodySet(){
		return bodySet;
	}
	
	public boolean isHeadSet(){
		return headSet;
	}
	
	public boolean onlySendHead(){
		return sendHeadOnly;
	}
	
	public void setOnlySendHead(boolean bool)
	{
		sendHeadOnly = bool;
	}
	
	public static Reply generate303Reply(String location)
	{
		Reply r = new Reply();
		r.setOnlySendHead(true);
		
		String head = "HTTP/1.1 303 Redirect"
				+ "\nLocation: /" + location;
		r.setHead(head);
		return r;
	}

	public void setHead200() {
		head = "\n"
				+ "HTTP/1.1 200 OK"
				+ "\n";
	}
	
	public String getReply()
	{
		
		String result = "";
		result += head + "\n";
		
		if(!onlySendHead())
		{
			result += body;
		}
		
		return result;
	}

	public void buildReply() throws IOException {
		if(!isHeadSet() && !isBodySet())
		{
			if(checkFileExists()){
				if(fileInSystem)
				{
					body = new String(Files.getPageContent(request.getFileName()));
					setHead200();
				}else if(fileInDB){
					body = TempPages.getPageContent(request.getFileName());
					setHead200();
				}
			}
		}	
	}
	
	public boolean checkFileExists()
	{
		File file = request.getFileName();
		
		//Check if file exists or if it is in Database
		if(file.exists()){
			fileInSystem = true;
			return true;
		}
		else if(TempPages.doesTempPageExist(request.getFileName())){
			fileInDB = true;
			return true;
		}
		else
			return false;
	}

}
