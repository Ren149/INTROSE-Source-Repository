//MILESTONE
package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;
import java.util.ArrayList;

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
		sQuery = "INSERT INTO line_item(salesID, productID, quantity_sold, unit_price, total_price_sold) "
                        +"VALUES('"+ salesID +"','"+ productID + "', '"+totalQty+"', '"+unit_price+"', '"+total_price_sold+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
                        
		} catch(SQLException e) {
			e.printStackTrace();
		}
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

			con.getConnection().close();
			rs.close(); 
			return prodIDList;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
    
    public int getTotalProdQty(int prodID, String entrydate){
        sQuery = "SELECT SUM(quantity_sold) FROM line_item WHERE productID = '"+prodID+"'"
                +"AND salesID IN (SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
					return rs.getInt(1);
			}
			rs.close(); 	
		} catch(SQLException e) {
			e.printStackTrace();
		}
                return -1;
    }
    
    public float getUnitPrice(int prodID, String entrydate){
        sQuery = "SELECT DISTINCT unit_price FROM line_item WHERE productID = '"+prodID+"'"
                +"AND salesID IN (SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%') ";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
					return rs.getFloat(1);
			}
			rs.close(); 	
		} catch(SQLException e) {
			e.printStackTrace();
		}
                return -1;
    }
}
