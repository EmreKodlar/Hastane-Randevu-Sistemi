package Baglanti;

import java.sql.*;



 

public class Database {
	
	

	Connection c=null;
	
	public Database(){}
	
	public Connection connDb() {
		
		try {
			

			this.c= DriverManager.getConnection("jdbc:mysql://localhost/javadb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8","root","");
			 
			return c;
		} 
		
		
		catch (SQLException e)
		
		{
			 
			e.printStackTrace();
		}
		
		return c;
	}
	
	
	

}