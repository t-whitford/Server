package serverMain;

import database.Emails;

public class PostHandler {

	public static Request processPost(Request request){
		System.out.println(request.getBody());
		
		String[] body = request.getBody().split("[&=]");
		
		
		//Create pairs
		PostFormPair[] pairs = new PostFormPair[body.length/2];
		for(int i = 0; i < body.length; i++){
			PostFormPair newPair = new PostFormPair(body[i], body[i+1]);
			pairs[i/2] = newPair;
			i++;
		}
		
		
		//Get email
		for(PostFormPair x: pairs){
			if(x.getId().contains("user_email")){
				
				String email = x.getContent();
				
				//Fix email %40
				
				email = email.replaceAll("%40", "@");
				
				if(email.matches("[a-zA-Z0-9-_@\\.]+")){
					Emails.addEmail(email);
					break;
				}
			}
		}
		
		return request;
		
		
		
	}

}
