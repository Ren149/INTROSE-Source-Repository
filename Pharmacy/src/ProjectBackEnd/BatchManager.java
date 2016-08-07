package ProjectBackEnd;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class BatchManager {
	
	//this
	public BatchManager() {

	}
	
	public float getBuyingPrice(int batchID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT buying_price "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		float buyingPrice = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				buyingPrice = rs.getFloat(1);
			}

			con.disconnect();
			
			rs.close();
			
			return buyingPrice;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public float getLatestBuyingPrice(int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT buying_price "
				+ "FROM batch "
				+ "WHERE productID = " + productID + " "
				+ "AND batchID = (SELECT MAX(b.batchID)"
				+ " FROM batch b"
				+ " WHERE b.productID = " + productID + ");";
		float latestBuyingPrice = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				latestBuyingPrice = rs.getFloat(1);
			}

			con.disconnect();
			
			rs.close();
			
			return latestBuyingPrice;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getTotalQuantity (int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(total_batch_quantity) "
				+ "FROM batch "
				+ "WHERE productID = '" + productID + "';";
		int totalQuantity = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return totalQuantity;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public Date getEntryDate (int batchID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT entry_date "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		Date entryDate = null;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				entryDate = rs.getDate(1);
			}

			con.disconnect();
			
			rs.close();
			
			return entryDate;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public int getExpiryMonth (int batchID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT expiry_month "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		int expiryMonth = 0;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				expiryMonth = rs.getInt(1);
			}
			
			con.disconnect();

			rs.close();
			
			return expiryMonth;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int getExpiryYear (int batchID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT expiry_year "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		int expiryYear = 0;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				expiryYear = rs.getInt(1);
			}
		
			con.disconnect();

			rs.close();
			
			return expiryYear;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public ArrayList<Integer> getBatchIDList (int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT batchID FROM batch WHERE productID = ? ORDER BY entry_date DESC, batchID DESC;";
		ArrayList<Integer> searchResults = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setInt(1, productID);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				searchResults.add(rs.getInt(1));
			}

			con.disconnect();

			rs.close();
			
			return searchResults;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}
	
	public void addBatch(int productID, int batchquantity, double buyingprice, int expiremonth, int expiryyear) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "INSERT INTO batch(productID, total_batch_quantity, batch_quantity_left, expiry_month, expiry_year, entry_date, buying_price)"
						+ "VALUES('"+ productID +"','"+ batchquantity +"','"+ batchquantity +"','"+ expiremonth +"','"+ expiryyear +"', CURDATE(), '"+ buyingprice + "')";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			
			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void restockBatch(int productID, int quantity, double buyingPrice, int expiryMonth, int expiryYear) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "INSERT INTO batch(productID, total_batch_quantity, batch_quantity_left, expiry_month, expiry_year, entry_date, buying_price) "
					+ "VALUES('" + productID + "', '" + quantity + "', '" + quantity + "','"+ expiryMonth +"','"+ expiryYear +"', CURDATE(), '"+ buyingPrice + "') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			
			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

    public void changeBatchQtyToZero(int batchID) {
    	DBConnection con = new DBConnection();
		PreparedStatement ps;
    	String sQuery = "UPDATE batch "
				+ "SET total_batch_quantity = '0'"
				+ "AND batch_quantity_left = '0'"
				+ "WHERE batchID = "+ batchID +";";
        
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
			
        	con.disconnect();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public ArrayList<Integer> getBatchIDofProductList(int productID){
    	DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
        String sQuery = "SELECT batchID "
			+ "FROM batch "
			+ "WHERE productID = '" + productID + "';";
        ArrayList<Integer> BatchIDofProductList = new ArrayList<>();
	
		try {
			ps = con.getConnection().prepareStatement(sQuery);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				BatchIDofProductList.add(rs.getInt(1));
			}

			con.disconnect();

			rs.close();
			
			return BatchIDofProductList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
    }
    
    public int getEachBatchQuantity (int batchID) {
    	DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
    	String sQuery = "SELECT total_batch_quantity "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		int batchQuantity = -1;
    	
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				batchQuantity = rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return batchQuantity;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
    
    public void subtractBatchQty(int batchID, int batchQty, int QtySold) {
    	DBConnection con = new DBConnection();
		PreparedStatement ps;
		int difference = batchQty - QtySold;
        String sQuery = "UPDATE batch "
				+ "SET batch_quantity_left = '"+ difference +"'"
				+ "WHERE batchID = "+ batchID +";";
       
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
		
        	con.disconnect();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}