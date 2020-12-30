package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Baglanti.Helper;

public class Hasta extends User {

	Statement st=null;
	ResultSet rs=null;
	Connection con=conn.connDb();
	PreparedStatement preparedStatement= null;
	
	public Hasta() {
		 
	}

	public Hasta(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		 
	}
	
	public boolean register(String tcno,String password, String name) throws SQLException {
		
		int key=0;
		boolean tekrar_eden=false;
		
		String query="INSERT INTO user (tcno,password,name,type) VALUES (?,?,?,?)";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT*FROM user WHERE tcno='" + tcno + "'");
			while(rs.next()) {
				tekrar_eden = true;
				Helper.Goster("Bu TC No'ya ait bir kayýt bulunmaktadýr!!!\nÞifrenizi öðrenmek için lütfen bilgi iþlem ile irtibata geçiniz!");
				break;
			}
			
			if(!tekrar_eden) {
				

				preparedStatement=con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				
				Helper.Goster("basari");
				
			}
			
			key=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else
		return false;
	}
	
	
	
public boolean addAppoinment(int doctor_id,int hasta_id,  String doctor_name ,String hasta_name, String appDate) throws SQLException {
		
		int key=0;
	 
		
		String query="INSERT INTO appoinment (doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES (?,?,?,?,?)";
		
		try {
			 
			 
				preparedStatement=con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setInt(3, hasta_id);
				preparedStatement.setString(4, hasta_name);
				preparedStatement.setString(5, appDate);
				preparedStatement.executeUpdate();
				
				Helper.Goster("basari");
				
		 	
			key=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else
		return false;
	}
	




public boolean updateWhourStatus(int doctor_id, String wdate ) throws SQLException {
	
	int key=0;
	
	String query="UPDATE whour SET status=?  WHERE doctor_id=? AND wdate=?";
	
	try {
		
		 
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			 
			preparedStatement.executeUpdate();
 
		key=1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(key==1)
		return true;
	else
	return false;
}



public boolean deleteRandevu(int id) throws SQLException {
	String query = "DELETE FROM appoinment WHERE id=?";

	boolean key=false;
	
try {
	st=con.createStatement();
	preparedStatement=con.prepareStatement(query);
	preparedStatement.setInt(1,id);
	 
	preparedStatement.executeUpdate();
	key=true;
} catch (Exception e) {
	e.printStackTrace();
}

if(key)
	return true;
else
	return false;
	

}

public boolean updateWhourStatusA(int doctor_id, String wdate ) throws SQLException {
	
	int key=0;
	
	String query="UPDATE whour SET status=?  WHERE doctor_id=? AND wdate=?";
	
	try {
		
		 
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			 
			preparedStatement.executeUpdate();
 
		key=1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(key==1)
		return true;
	else
	return false;
}




}
