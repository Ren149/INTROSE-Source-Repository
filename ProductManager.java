package ProjectBackEnd;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

import DBConnector.DBConnection;

public class ProductManager {
	
	private DBConnection con;
	
	public ProductManager()
	{
		con = new DBConnection();
	}
	
	public void addProduct(String productname, double sellprice)
	{
		PreparedStatement ps;
		String sQuery = "INSERT INTO products(product_name, selling_price, isDiscontinued)"
						+ "VALUES('"+  productname + "', '"+ sellprice + "', 'FALSE');";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getLatestProductID()
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT MAX(productID) FROM products;";

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
	
	public int getProductID(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT productID FROM products WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(2, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
					return rs.getInt(1);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void discontinueProduct(int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "UPDATE products"
			+ "SET isDiscontinued = 'TRUE'"
			+ "WHERE isDiscontinued = 'FALSE' AND productID = "+ productID +";";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			} catch(SQLException e) {
					e.printStackTrace();
				}
		
	}

	public DefaultTableModel searchItem(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel();
		
		tm.setColumnIdentifiers(new String[] {"Product Name", "Buying Price", "Selling Price", "Batch Quantity"});
		
		String sQuery = "SELECT p.product_name, b.batch_quantity, b.buying_price, p.selling_price"
				+ "FROM products p, batch.b"
				+ "WHERE product_name = ?"
				+ "INNER JOIN batch ON product.productID = batch.productID"
				+ "ORDER BY product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(2, productname);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				tm.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	public DefaultTableModel viewProducts()
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel();
		
		tm.setColumnIdentifiers(new String[] {"Product Name", "Buying Price", "Selling Price", "Batch Quantity"});
		
		String sQuery = "SELECT p.product_name, b.batch_quantity, b.buying_price, p.selling_price"
				+ "FROM products p, batch.b"
				+ "INNER JOIN batch ON product.productID = batch.productID"
				+ "ORDER BY product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				tm.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
}
