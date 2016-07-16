package ProjectBackEnd;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

import DBConnector.DBConnection;

public class BatchManager {
	
	private DBConnection con;
	
	public BatchManager()
	{
		con = new DBConnection();
	}
	
	public int getBuyingPrice (int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		
		String sQuery = "SELECT buying_price "
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
	
	public int getExpiryMonth (int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		
		String sQuery = "SELECT expiry_month "
				+ "FROM batch "
				+ "WHERE productID = '" + productID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
			
	}
	
	public int getExpiryYear (int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		
		String sQuery = "SELECT expiry_year "
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
	
	public void addBatch(int batchquantity, double buyingprice, int expiremonth, int expiryyear)
	{
		ProductManager getIDmanage = new ProductManager();
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price)"
						+ "VALUES('"+ getIDmanage.getLatestProductID() +"','"+ batchquantity +"','"+ (expiremonth+1) +"','"+ expiryyear +"', CURDATE(), '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void restockBatch(int batchquantity, double buyingprice, int expiremonth, int expiryyear, int productID)
	{
		PreparedStatement ps;
		String sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price) "
					+ "VALUES('" + productID + "', '" + (getBatchQuantity(productID) - batchquantity) + "','"+ expiremonth +"','"+ expiryyear +"', CURDATE(), '"+ buyingprice + "') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
