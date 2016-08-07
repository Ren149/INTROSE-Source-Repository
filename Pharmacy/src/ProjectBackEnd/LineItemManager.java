package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;

public class LineItemManager {
	
	private DBConnection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sQuery;

	public LineItemManager()
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
	

    public void recordTransaction(int salesID, int productID, int totalQty)
	{
		sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold)"
						+ "VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, ps);
	}
}
