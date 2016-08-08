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
	
	public float getSellingPrice(int productID)
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT selling_price "
				+ "FROM products "
				+ "WHERE productID = '" + productID + "';";

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
	
	public float getSellingPrice(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT selling_price FROM products WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
					return rs.getInt(1);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
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
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
					return rs.getInt(1);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getProductID(int row)
	{
	PreparedStatement ps;
	ResultSet rs;
	
	String sQuery = "SELECT p.productID "
			+ "FROM products p "
			+ "ORDER BY p.product_name DESC LIMIT 1 OFFSET " + row + ";";
	
	try {
		ps = con.getConnection().prepareStatement(sQuery);
		ps.executeQuery(sQuery);
		
		rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1);
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	
	return -1;
		
	}

	public int getProductID(int row, String searchText)
	{
	PreparedStatement ps;
	ResultSet rs;
	String sQuery;
	
	if(searchText.equals(""))
	{
	sQuery = "SELECT p.productID "
			+ "FROM products p "
			+ "ORDER BY p.product_name DESC LIMIT 1 OFFSET " + row + ";";
	}
	else
	{
	sQuery = "SELECT p.productID "
			+ "FROM products p "
			+ "WHERE p.product_name LIKE ? "
			+ "ORDER BY p.product_name DESC LIMIT 1 OFFSET " + row + ";";
	}
	
	try {
		ps = con.getConnection().prepareStatement(sQuery);
		ps.setString(1, "%" + searchText + "%");
		ps.executeQuery(sQuery);
		
		rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1);
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	
	return -1;
		
	}
	
	public boolean checkDuplicates(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT product_name "
						+ "FROM products	"
						+ "WHERE product_name = ?;";

		try {ps = con.getConnection().prepareStatement(sQuery);
		ps.setString(1, productname);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			return true;
		}
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addProduct(String productname, double sellprice)
	{
		PreparedStatement ps;
		String sQuery = "INSERT INTO products(product_name, selling_price, isDiscontinued) "
						+ "VALUES('"+  productname + "', '"+ sellprice + "', '0');";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void restockProduct(String productname, double sellprice, int productID)
	{
		PreparedStatement ps;
		String sQuery = "UPDATE products "
					+ "SET product_name = '" + productname + "', selling_price = '" + sellprice + "' "
					+ "WHERE productID = "+ productID +";";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void discontinueProduct(int productID)
	{
		PreparedStatement ps;
		String sQuery = "UPDATE products "
			+ "SET isDiscontinued = '1' "
			+ "WHERE isDiscontinued = '0' AND productID = '"+ productID +"';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			} catch(SQLException e) {
					e.printStackTrace();
				}
		
	}

	public DefaultTableModel searchProduct(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};

		tm.setColumnIdentifiers(new String[] {"Product Name", "Quantity", "Selling Price"});

		String sQuery = "SELECT p.product_name, SUM(b.batch_quantity), p.selling_price "
				+ "FROM products p, batch b "
				+ "WHERE p.product_name LIKE ? "
				+ "AND p.productID = b.productID AND isDiscontinued = '0' "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, "%" + productname + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(2) > 0)
				tm.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return tm;
	}

	public DefaultTableModel searchProductWithPrice(String productname)
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tm.setColumnIdentifiers(new String[] {"Product Name", "Quantity", "Buying Price"});
		
		String sQuery = "SELECT p.product_name, SUM(b.batch_quantity), b.buying_price "
				+ "FROM products p, batch b "
				+ "WHERE p.product_name LIKE ? "
				+ "AND p.productID = b.productID AND isDiscontinued = '0' "
				+ "AND b.entry_date = (SELECT MAX(ba.entry_date) "
					+ " FROM batch ba "
					+ "	WHERE ba.productID = p.productID) "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, "%" + productname + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(2) > 0)
				tm.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return tm;
	}

	public DefaultTableModel viewProducts()
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tm.setColumnIdentifiers(new String[] {"Product Name", "Quantity", "Selling Price"});
		
		String sQuery = "SELECT p.product_name, SUM(b.batch_quantity), p.selling_price "
				+ "FROM products p, batch b "
				+ "WHERE p.productID = b.productID AND isDiscontinued = '0' "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(2) > 0)
				tm.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return tm;
	}

	public DefaultTableModel viewProductsWithPrice()
	{
		PreparedStatement ps;
		ResultSet rs;
		DefaultTableModel tm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tm.setColumnIdentifiers(new String[] {"Product Name", "Quantity", "Buying Price"});
		
		String sQuery = "SELECT p.product_name, SUM(b.batch_quantity), b.buying_price "
				+ "FROM products p, batch b "
				+ "WHERE p.productID = b.productID AND isDiscontinued = '0' "
				+ " AND b.entry_date = (SELECT MAX(ba.entry_date) "
				+ " FROM batch ba "
				+ "	WHERE ba.productID = p.productID) "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(2) > 0)
				tm.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3)});
			}
			return tm;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return tm;
	}
	
}
