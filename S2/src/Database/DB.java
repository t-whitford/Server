package Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ServerMain.Server;

public class DB {

	Connection conn = null;
	
	private static final String folder = "Private";
	private static final String fileName = "DBDetails.txt";
	
	private static String user;
	private static String pw;

	public DB() throws DatabaseException, IOException
	{
		//Get PW and USER from file

		File f = new File(Server.getRootDirectory(), "\\" + folder + "\\" + fileName);

		List<String> details = FileUtils.readLines(f);

		if(details.size() != 2)
			throw new DatabaseException("File error - DBDetails.txt");

		user = details.get(0).trim();
		pw = details.get(1).trim();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Error");
		}
	}

	public void executeQuery()
	{


		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emails", user, pw);

			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery("SELECT * FROM emails");
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String email;
			while(rs.next())
			{
				email = rs.getString(1);
				System.out.println(email);
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}

}

