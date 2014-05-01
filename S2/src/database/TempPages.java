package database;

import java.io.File;
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
	 * @return returns the address of new page or NULL if fails to be added
	 */
	public static String addTempPage(String content)
	{
		
		content = content.replace("\\", "\\\\");
		content = content.replace("\"", "\\\"");
		
		
		
		Random r = new Random();
		String rand = r.nextInt(10000000) + ".html";
		
			try(DB db = new DB()){
				db.executeUpdate("temppages", 
						"INSERT "
						+ "INTO "
						+ "singleuse "
						+ "(id, content) "
						+ "VALUES "
						//+ "(\"" + content + "\");");
						+ "(\"" + rand + "\", \"" + content + "\");");

			} catch(MySQLIntegrityConstraintViolationException e){
				
				return null;
				
			}catch (DatabaseException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		return "" + rand;
	}
	
	public static boolean doesTempPageExist(File file)
	{
		try(DB db = new DB()){

			ResultSet results = db.executeQuery("temppages", 
					"SELECT "
							+ "COUNT(*) "
							+ "FROM "
							+ "singleuse "
							+ "WHERE "
							+ "id = \"" + file.getName() + "\";");

			results.next();
			if(results.getInt(1) == 1)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static String getPageContent(File file){
		String page = null;
		
			try(DB db = new DB()){
				ResultSet results = db.executeQuery("temppages", 
						"SELECT "
						+ "content "
						+ "FROM "
						+ "singleuse "
						+ "WHERE "
						+ "id =\"" + file.getName() + "\";");
				
				results.absolute(1);
				System.out.println(file.getName());
				System.out.println(results.getString(1));
				page = results.getString(1);
				
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
		
		
		return page;
	}
}
