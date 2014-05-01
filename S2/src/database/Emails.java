package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Emails {

	/**
	 * 
	 * @param email - must be formatted + checked BEFORE THIS POINT.
	 * @return whether the email is in database at the end of the process.
	 * @throws IOException 
	 * @throws DatabaseException 
	 */
	public static boolean addEmail(String email)
	{
			try(DB db = new DB()){
				db.executeUpdate("emails", "INSERT INTO emails (email) VALUES ('" + email + "');");

			} catch(MySQLIntegrityConstraintViolationException e){
				
				return true;
				
			}catch (DatabaseException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		return true;
	}
	
	public static void searchEmail()
	{
		
	}
	
	
	public static void printAllEmails(){
		
		try(DB db = new DB()){
			ResultSet rs = db.executeQuery("SELECT * FROM emails");

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
