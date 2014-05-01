package database;

import java.io.Closeable;
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

import serverMain.Server;

public class DB implements AutoCloseable{

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

	public void executeUpdate(String table, String sql) throws SQLException{
		
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + table, user, pw);
		
		Statement s = conn.createStatement();

		System.out.println(s.executeUpdate(sql));
		
	}
	
	
	public ResultSet executeQuery(String sql) throws SQLException
	{
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emails", user, pw);

			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			return rs;
//			ResultSetMetaData rsmd = rs.getMetaData();
//			
//			String email;
//			while(rs.next())
//			{
//				email = rs.getString(1);
//				System.out.println(email);
//			}

	}

	@Override
	public void close() throws IOException {

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

