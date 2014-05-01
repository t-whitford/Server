package serverMain;

import java.io.IOException;

import database.Emails;

public class PostHandler {

	public static void processPost(Request request) throws IOException{
		
		
		//Parse input

		String[] body = request.getBody().split("[&=]");


		//Create pairs
		PostFormPair[] pairs = new PostFormPair[body.length/2];
		for(int i = 0; i < body.length; i++){
			PostFormPair newPair = new PostFormPair(body[i], body[i+1]);
			pairs[i/2] = newPair;
			i++;
		}
		
		//Add data to DB
			//Is success?
			//Add success page to DB
			//Set head of reply to 303 with page
				//Set reply send headonly
		
		
		
		//Get email
		for(PostFormPair x: pairs){
			if(x.getId().contains("user_email")){
				
				String email = x.getContent();
				
				//Fix email %40
				email = email.replaceAll("%40", "@");
				
				if(email.matches("[a-zA-Z0-9-_@\\.]+")){
					//Add to db
					//Get location of temp file + change reply to 303
					String redirect = Emails.addEmail(email);
					request.setReply(Reply.generate303Reply(redirect));
				}
			}
		}
	}

}
