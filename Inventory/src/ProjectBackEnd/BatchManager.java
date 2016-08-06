package ProjectBackEnd;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class BatchManager {
	
	private DBConnection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sQuery;
	
	public BatchManager() {
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
	
	public float getBuyingPrice(int batchID) {
		sQuery = "SELECT buying_price "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getFloat(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public float getLatestBuyingPrice(int productID) {
		sQuery = "SELECT buying_price "
				+ "FROM batch "
				+ "WHERE productID = " + productID + " "
				+ "AND batchID = (SELECT MAX(b.batchID)"
				+ " FROM batch b"
				+ " WHERE b.productID = " + productID + ");";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getFloat(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, rs, ps);
		return 0;
	}
	
	public int getTotalQuantity (int productID) {
		sQuery = "SELECT SUM(batch_quantity) "
				+ "FROM batch "
				+ "WHERE productID = '" + productID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con, rs, ps);
		return 0;
	}
	
	public Date getEntryDate (int batchID) {
		sQuery = "SELECT entry_date "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getDate(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con, rs, ps);
		return null;
	}
	
	public int getExpiryMonth (int batchID) {
		sQuery = "SELECT expiry_month "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con, rs, ps);
		return 0;
	}
	
	public int getExpiryYear (int batchID) {
		sQuery = "SELECT expiry_year "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con, rs, ps);
		return 0;
	}
	
	public ArrayList<Integer> getBatchIDList (int productID) {
		ArrayList<Integer> searchResults = new ArrayList<>();
		
		sQuery = "SELECT batchID FROM batch WHERE productID = ? ORDER BY entry_date DESC, batchID DESC;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setInt(1, productID);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				searchResults.add(rs.getInt(1));
			}
			closeConnection(con, rs, ps);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, rs, ps);
		return searchResults;
	}
	
	public void addBatch(int productID, int batchquantity, double buyingprice, int expiremonth, int expiryyear) {
		sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price)"
						+ "VALUES('"+ productID +"','"+ batchquantity +"','"+ expiremonth +"','"+ expiryyear +"', CURDATE(), '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, ps);
	}
	
	public void restockBatch(int productID, int quantity, double buyingPrice, int expiryMonth, int expiryYear) {
		sQuery = "INSERT INTO batch(productID, batch_quantity, expiry_month, expiry_year, entry_date, buying_price) "
					+ "VALUES('" + productID + "', '" + quantity + "','"+ expiryMonth +"','"+ expiryYear +"', CURDATE(), '"+ buyingPrice + "') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, ps);
	}

    public void changeBatchQtyToZero(int batchID) {
        sQuery = "UPDATE batch "
				+ "SET batch_quantity = '0'"
				+ "WHERE batchID = "+ batchID +";";
        
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
        	
			
        } catch(SQLException e) {
        	e.printStackTrace();
        }
		closeConnection(con, ps);
    }
    
    public ArrayList<Integer> getBatchIDofProductList(int productID){
        ArrayList<Integer> BatchIDofProductList = new ArrayList<>();
        
        sQuery = "SELECT batchID "
			+ "FROM batch "
			+ "WHERE productID = '" + productID + "';";
	
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BatchIDofProductList.add(rs.getInt(1));
			}
			closeConnection(con, rs, ps);
			return BatchIDofProductList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		closeConnection(con, rs, ps);
		return null;
    }
    
    public int getEachBatchQuantity (int batchID) {
		sQuery = "SELECT batch_quantity "
				+ "FROM batch "
				+ "WHERE batchID = '" + batchID + "';";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				closeConnection(con, rs, ps);
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con, rs, ps);
		return 0;
	}
    
    public void subtractBatchQty(int batchID, int batchQty, int QtySold) {
        int difference = batchQty - QtySold;
        
        sQuery = "UPDATE batch "
				+ "SET batch_quantity = '"+ difference +"'"
				+ "WHERE batchID = "+ batchID +";";
       
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
        	
        } catch(SQLException e) {
        	e.printStackTrace();
        }
		closeConnection(con, rs, ps);
    }
}