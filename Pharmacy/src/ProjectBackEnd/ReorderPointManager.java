package ProjectBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnector.DBConnection;

public class ReorderPointManager {
	
	//this
	

	private DBConnection con;
	private PreparedStatement ps;
	private	ResultSet rs;
	private String sQuery;

	public ReorderPointManager()
	{
		con = new DBConnection();
	}
	
	public DBConnection getDBConnection()
	{
		return con;
	}

}
