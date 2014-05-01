package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import serverMain.Reply;
import serverMain.Request;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class TempPages {
	
	/**
	 * Takes the parameters for pageGenerator and adds them to DB for later use
	 * @param head
	 * @param body
	 * @param request updates the request with a 303 redirect to the new page.
	 * @return returns the address of new page or NULL if fails to be added
	 */
	public static String addTempPage(String head, String body, Request request)
	{
		
		Random r = new Random();
		int rand = r.nextInt();
		
			try(DB db = new DB()){
				db.executeUpdate("temppages", 
						"INSERT "
						+ "INTO "
						+ "singleUsePages "
						+ "(id, body, head) "
						+ "VALUES "
						+ "(\"" + body + "\", \"" + head + "\");");

			} catch(MySQLIntegrityConstraintViolationException e){
				
				return null;
				
			}catch (DatabaseException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		return "" + rand;
	}

}
