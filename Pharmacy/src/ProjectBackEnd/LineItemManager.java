package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;

public class LineItemManager {
	
	//this
	
	
	private DBConnection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sQuery;

	public LineItemManager()
	{
		con = new DBConnection();
	}
	
	public DBConnection getDBConnection()
	{
		return con;
	}
	
    public void recordTransaction(int salesID, int productID, int totalQty)
	{
		sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold)"
						+ "VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
