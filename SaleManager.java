package ProjectBackEnd;

import java.sql.*;
import java.util.Date;
import DBConnector.DBConnection;
import java.util.ArrayList;

public class SaleManager {
    
    private DBConnection con;
	
	public SaleManager()
	{
		con = new DBConnection();
	}
    
    public float getSubtotal(int qty, float sellprice)
	{
            return sellprice * qty;
        }
    
    public int getLatestSalesID()
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT MAX(salesID) FROM sales;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
					return rs.getInt(1);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
    
    public void recordTransaction(int totalQty, float totalSold, String date)
	{
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO sales(quantity_sold, total_price_sold, date_sold)"
						+ "VALUES('"+ totalQty +"','"+ totalSold + "', '"+date+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
    
    public void recordLineItem()
	{
    	
	}
    
    public int getDuplicate(ArrayList<String> prodNameList, String prodName){
        int x = 999;
        for(int i = 0; i < prodNameList.size(); i++){
            if(prodNameList.get(i).compareTo(prodName) == 0)
                x = i;
        }
        return x;
    }
}
