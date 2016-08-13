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
	private	ResultSet rs;
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
    	sQuery = "SELECT MAX(salesID) FROM sales;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
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

}
