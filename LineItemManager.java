package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;

public class LineItemManager {
	
	private DBConnection con;

	public LineItemManager()
	{
		con = new DBConnection();
	}

    public void recordTransaction(int salesID, int productID, int totalQty)
	{
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold)"
						+ "VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
