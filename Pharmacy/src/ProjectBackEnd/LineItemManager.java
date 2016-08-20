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
		String sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold, unit_price, total_price_sold) "
                        +"VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"', '"+unit_price+"', '"+total_price_sold+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
                        
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
    

	public String determineDemand(int productID){
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(quantity_sold) "
				+ "FROM line_item "
				+ "WHERE productID = '" + productID + "' ;";
		String demand = "";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			float averageSaleCount = getAverageSaleCount();
			
			if(rs.next()) {
				if(rs.getInt(1) >= averageSaleCount)
					demand = "High";
				else
					demand = "Low";
			}	

			con.disconnect();
			
			rs.close();
			
			return demand;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return demand;
	}
	
    
    public ArrayList<Integer> getProdIDList(String entrydate) {	
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT DISTINCT productID FROM line_item WHERE salesID "
                        + "IN (SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%') "
                        + "ORDER BY productID ASC";
                
		ArrayList<Integer> prodIDList = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				prodIDList.add(rs.getInt(1));
			}

			con.disconnect();
			rs.close(); 
			return prodIDList;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
    
    public int getTotalProdQty(int prodID, String entrydate){
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(quantity_sold) FROM line_item WHERE productID = '"+prodID+"'"
                +"AND salesID IN (SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			rs = ps.executeQuery();
			int temp = 0;
			
			if(rs.next()) {
                                        temp = rs.getInt(1);
                                        con.disconnect();
					rs.close();
					return temp;
					
			}
			 	
		} catch(SQLException e) {
			e.printStackTrace();
		}
                return -1;
    }
    
    public float getUnitPrice(int prodID, String entrydate){
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT DISTINCT unit_price FROM line_item WHERE productID = '"+prodID+"'"
                +"AND salesID IN (SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			rs = ps.executeQuery();
			float temp = 0;
			if(rs.next()) {
                                        temp = rs.getFloat(1);
                                        con.disconnect();
                                        rs.close();
					return temp;
			}
			 	
		} catch(SQLException e) {
			e.printStackTrace();
		}
                return -1;
    }

	public ArrayList<Integer> getProductIDs() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT DISTINCT(l.productID) "
				+ "FROM line_item l, products p "
				+ "WHERE l.productID = p.productID "
				+ "ORDER BY p.product_name;";
		
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

	private ArrayList<Integer> getTotalSaleCountList() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT SUM(quantity_sold) "
				+ "FROM line_item "
				+ "GROUP BY productID;";
		ArrayList<Integer> saleCountList = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				saleCountList.add(rs.getInt(1));
			}

			con.disconnect();
			
			rs.close();
			
			return saleCountList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private float getAverageSaleCount() {
		ArrayList<Integer> saleCountList = getTotalSaleCountList();
		float averageSaleCount = 0;
		int i = 0;
		
		for(i = 0; i < saleCountList.size(); i++){
			averageSaleCount += saleCountList.get(i);
		}

		averageSaleCount /= i;
		return averageSaleCount;
	}
}
