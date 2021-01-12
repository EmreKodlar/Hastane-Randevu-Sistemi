package Model;

import java.sql.SQLException;

public interface InterfaceModel {
	
	 

	boolean deleteRandevu(int id) throws SQLException;
	
	public boolean updateWhourStatus(int doctor_id, String wdate ) throws SQLException;
	
	public boolean addAppoinment(int doctor_id,int hasta_id,  String doctor_name ,String hasta_name, String appDate) throws SQLException;

}
