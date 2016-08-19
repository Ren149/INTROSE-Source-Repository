//MILESTONE
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
	
    public void recordTransaction(int salesID, int productID, int totalQty, float unit_price, float total_price_sold)
	{
		sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold, unit_price, total_price_sold)"
						+ "VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"', '"+unit_price+"', '"+total_price_sold+")";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
