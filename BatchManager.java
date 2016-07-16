package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;

public class BatchManager {
	
	private DBConnection con;

	public BatchManager()
	{
		con = new DBConnection();
	}
	
	public int getBatchQuantity (int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		
		String sQuery = "SELECT batch_quantity "
				+ "FROM batch "
				+ "WHERE productID = '" + productID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
			
	}
	
	public void addFirstBatch(int batchquantity, double buyingprice, int expiremonth, int expiryyear)
	{
		ProductManager getIDmanage = new ProductManager();
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price)"
						+ "VALUES('"+ getIDmanage.getLatestProductID() +"','"+ batchquantity +"','"+ expiremonth +"','"+ expiryyear +"','CURDATE()', '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addNewBatch(double buyingprice)
	{
		ProductManager getIDmanage = new ProductManager();
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO batch(productID, batch_quantity, entry_date, buying_price)"
						+ "VALUES('"+ getIDmanage.getLatestProductID() +"','1', 'CURDATE()', '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addBatch(int productID, double buyingprice)
	{
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO batch(productID, entry_date, buying_price)"
						+ "VALUES('"+ productID +"', 'CURDATE()', '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public void restockBatch(int batchquantity, double buyingprice, int productID)
	{
		PreparedStatement ps;
		String sQuery = "UPDATE batch "
					+ "SET batch_quantity = '" + (getBatchQuantity(productID) + batchquantity) + "', buying_price = '" + buyingprice + "' "
					+ "WHERE productID = "+ productID +";";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
