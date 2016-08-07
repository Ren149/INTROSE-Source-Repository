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

	
	public void closeConnection(DBConnection con, ResultSet rs, PreparedStatement ps)
	{
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { con.getConnection().close(); } catch (Exception e) { /* ignored */ }
	}

	public void closeConnection(DBConnection con, PreparedStatement ps)
	{
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { con.getConnection().close(); } catch (Exception e) { /* ignored */ }
	}
	
}
