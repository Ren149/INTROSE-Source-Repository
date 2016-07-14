package ProjectBackEnd;
import java.sql.*;

import DBConnector.DBConnection;

public class BatchManager {
	
	private DBConnection con;

	public BatchManager()
	{
		con = new DBConnection();
	}
	public void addFirstBatch(int batchquantity, double buyingprice)
	{
		ProductManager getIDmanage = new ProductManager();
		PreparedStatement ps;
		String sQuery = "INSERT INTO batch(productID, batch_quantity, buying_price)"
						+ "VALUES('"+ getIDmanage.getLatestProductID() +"','"+ batchquantity +"', '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addBatch(String productname, double buyingprice)
	{
		ProductManager getIDmanage = new ProductManager();
		PreparedStatement ps;
		String sQuery = "INSERT INTO batch(productID, batch_quantity, buying_price)"
						+ "VALUES('"+ getIDmanage.getProductID(productname) +"','50', '"+ buyingprice + "')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
