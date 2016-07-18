package ProjectBackEnd;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DBConnector.DBConnection;

public class BatchManager {
	
	private DBConnection con;
	
	public BatchManager() {
		con = new DBConnection();
	}
	
	public int getBuyingPrice(int productID) {
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
	
	public float getLatestBuyingPrice(int productID) {
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT buying_price "
				+ "FROM batch "
				+ "WHERE productID = '" + productID + "' AND entry_date = (SELECT MAX(entry_date) FROM batch WHERE batch.productID = productID;);";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getFloat(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getBatchQuantity (int productID) {
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(batch_quantity) "
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
	
	public int getExpiryMonth (int productID) {
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
	
	public int getExpiryYear (int productID) {
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
	
	public void addBatch(int batchquantity, double buyingprice, int expiremonth, int expiryyear) {
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
	
	public void restockBatch(int batchquantity, double buyingprice, int expiremonth, int expiryyear, int productID) {
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price) "
					+ "VALUES('" + productID + "', '" + batchquantity + "','"+ (expiremonth+1) +"','"+ expiryyear +"', CURDATE(), '"+ buyingprice + "') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

    public void changeBatchQtyToZero(int batchID) {
        PreparedStatement ps;
        String sQuery = "UPDATE batch "
				+ "SET batch_quantity = '0'"
				+ "WHERE batchID = "+ batchID +";";
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public ArrayList<Integer> getBatchIDofProductList(int productID){
        ArrayList<Integer> BatchIDofProductList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String sQuery = "SELECT batchID "
			+ "FROM batch "
			+ "WHERE productID = '" + productID + "';";
	
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BatchIDofProductList.add(rs.getInt(1));
			}
	                    return BatchIDofProductList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
    }
    
    public int getEachBatchQuantity (int batchID) {
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT batch_quantity "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
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
    
    public void subtractBatchQty(int batchID, int batchQty, int QtySold){
        PreparedStatement ps;
        int difference = batchQty - QtySold;
        String sQuery = "UPDATE batch "
				+ "SET batch_quantity = '"+ difference +"'"
				+ "WHERE batchID = "+ batchID +";";
       
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}