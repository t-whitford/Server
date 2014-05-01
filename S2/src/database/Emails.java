package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import pageGeneration.pageGenerator;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Emails {

	/**
	 * 
	 * @param email - must be formatted + checked BEFORE THIS POINT.
	 * @return location of confirmation page. Is NULL if failed to add to DB
	 * @throws IOException 
	 * @throws DatabaseException 
	 */
	public static String addEmail(String email) throws IOException
	{
		//Add email to DB
		//Add success page
		//Return location of success page
		
		String location = null;
		
			try(DB db = new DB()){
				db.executeUpdate("emails", "INSERT INTO emails (email) VALUES ('" + email + "');");
				
				location = TempPages.addTempPage(pageGenerator.generatePage("Success", "Added " + email + " to the mailing list"));
				//location = TempPages.addTempPage("Success", "Added " + email + " to the mailing list");

			} catch(MySQLIntegrityConstraintViolationException e){
				
				location = TempPages.addTempPage(pageGenerator.generatePage("Failed", "Was unable to add to database because" + e.getMessage()));
				return location;
				
			}catch (DatabaseException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		return location;
	}
	
	public static void searchEmail()
	{
		
	}
	
	
	public static void printAllEmails(){
		
		try(DB db = new DB()){
			ResultSet rs = db.executeQuery("emails", "SELECT * FROM emails");

			String email;
			while(rs.next())
			{
				email = rs.getString(1);
				System.out.println(email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DatabaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
