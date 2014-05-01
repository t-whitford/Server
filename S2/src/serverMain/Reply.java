package serverMain;

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
		return onlySendHead();
	}
	
	public void setOnlySendHead(boolean bool)
	{
		sendHeadOnly = bool;
	}

}
