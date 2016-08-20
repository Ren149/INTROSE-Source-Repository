//MILESTONE
package ProjectBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class SaleManager {
	
	//this
	
    
    private DBConnection con;
	private PreparedStatement ps;
	private String sQuery;
	
	public SaleManager()
	{
		con = new DBConnection();
	}
	
	public DBConnection getDBConnection()
	{
		return con;
	}
	

	
    
    public float getSubtotal(int qty, float sellprice)
	{
            return sellprice * qty;
        }
    
    public int getLatestSalesID()
	{
    	ResultSet rs;
    	sQuery = "SELECT MAX(salesID) FROM sales;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.disconnect();
			
			if(rs.next()) {
					rs.close();
					return rs.getInt(1);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
    
    public void recordTransaction(float totalSold, String date)
	{
    	sQuery = "INSERT INTO sales(total_price_sold, date_sold)"
						+ "VALUES('"+ totalSold + "', '"+date+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
    
    public int getTotalSales(int salesID){
    	ResultSet rs;
        sQuery = "SELECT total_price_sold FROM sales WHERE salesID = '"+salesID+"';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			rs = ps.executeQuery();
			con.disconnect();
			int temp;
			if(rs.next()) {
					temp = rs.getInt(1);
					return temp;
			}	
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
                return -1;
    }
    
    public ArrayList<Integer> getSalesIDList(String entrydate) {	
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT salesID FROM sales WHERE date_sold LIKE '%"+entrydate+"%';";
		ArrayList<Integer> salesIDList = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				salesIDList.add(rs.getInt(1));
			}

			con.disconnect();
			rs.close(); 
			return salesIDList;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
