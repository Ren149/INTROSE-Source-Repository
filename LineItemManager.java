//MILESTONE
package ProjectBackEnd;
import java.sql.*;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class LineItemManager {
	
	//this
	
	public LineItemManager()
	{
	}
	
    public void recordTransaction(int salesID, int productID, int totalQty, float unit_price, float total_price_sold)
	{
    	
    	DBConnection con = new DBConnection();
    	PreparedStatement ps;
    	ResultSet rs;
    	String sQuery;

		sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold, unit_price, total_price_sold)"
						+ "VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"', '"+unit_price+"', '"+total_price_sold+")";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
    


	public ArrayList<Integer> getProductIDs() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT DISTINCT(productID) "
				+ "FROM line_item;";
		
		ArrayList<Integer> id = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				id.add(rs.getInt(1));
			}
			
			con.disconnect();
			
			rs.close();
			
			return id;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int getLineQuantity(int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(quantity_sold) "
				+ "FROM line_item "
				+ "WHERE productID = '" + productID + "';";
		int quantity = -1;

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				quantity = rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return quantity;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	public int getAverageQuantity() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT AVG(quantity_sold) "
				+ "FROM line_item;";
		int quantity = -1;

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				quantity = rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return quantity;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

}
