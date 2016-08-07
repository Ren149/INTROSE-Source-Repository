package ProjectBackEnd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class ProductManager {
	private DBConnection con;
	private PreparedStatement ps;
	private	ResultSet rs;
	private String sQuery;
	
	public ProductManager() {
		con = new DBConnection();
	}
	
	
	public float getSellingPrice(int productID) {
		sQuery = "SELECT selling_price "
				+ "FROM products "
				+ "WHERE productID = '" + productID + "';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
				return rs.getFloat(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public float getSellingPrice(String productname) {
		String sQuery = "SELECT selling_price FROM products WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			
			if(rs.next()) {
				return rs.getFloat(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public String getProductName(int productID) {
		String sQuery = "SELECT product_name "
				+ "FROM products "
				+ "WHERE productID = '" + productID + "';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public int getLatestProductID() {
		String sQuery = "SELECT MAX(productID) FROM products;";

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
	
	public int getProductID(String productname) {
		String sQuery = "SELECT productID FROM products WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
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

	public int getProductID(int row, String searchText) {
		String sQuery = "SELECT p.productID "
					+ "FROM products p "
					+ "WHERE p.product_name LIKE '%" + searchText + "%' "
					+ "ORDER BY p.product_name LIMIT 1 OFFSET " + row + ";";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public ArrayList<Integer> getProductIDList() {	
		ArrayList<Integer> searchResults = new ArrayList<>();
		
		String sQuery = "SELECT productID FROM products ORDER BY product_name;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			while(rs.next()) {
				searchResults.add(rs.getInt(1));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return searchResults;
	}
	
	public ArrayList<Integer> getProductIDList(String searchText) {	
		ArrayList<Integer> searchResults = new ArrayList<>();
		sQuery = "SELECT productID FROM products WHERE product_name LIKE '%" + searchText + "%' ORDER BY product_name;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			while(rs.next()) {
				searchResults.add(rs.getInt(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return searchResults;
	}
	
	public boolean checkDuplicates(String productname) {
		sQuery = "SELECT product_name "
						+ "FROM products	"
						+ "WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			con.getConnection().close();
			
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public void addProduct(String productname, double sellprice) {
		sQuery = "INSERT INTO products(product_name, selling_price, isDiscontinued) "
						+ "VALUES('"+  productname + "', '"+ sellprice + "', '0');";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void restockProduct(String productname, double sellprice, int productID) {
		String sQuery = "UPDATE products "
					+ "SET product_name = '" + productname + "', selling_price = '" + sellprice + "' "
					+ "WHERE productID = "+ productID +";";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void discontinueProduct(int productID) {
		sQuery = "UPDATE products "
			+ "SET isDiscontinued = '1' "
			+ "WHERE isDiscontinued = '0' AND productID = '"+ productID +"';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			con.getConnection().close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet searchProduct(String productname) {
		sQuery = "SELECT p.product_name, SUM(b.batch_quantity), p.selling_price "
				+ "FROM products p, batch b "
				+ "WHERE p.product_name LIKE ? "
				+ "AND p.productID = b.productID AND isDiscontinued = '0' "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, "%" + productname + "%");
			
			rs = ps.executeQuery();
			con.getConnection().close();
			

			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet searchProductWithPrice(String productname) {
		sQuery = "SELECT p.product_name, SUM(b.batch_quantity), b.buying_price "
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
			con.getConnection().close();

			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet searchProductOutofStock(String productname) {
		sQuery = "SELECT p.product_name, SUM(b.batch_quantity) "
				+ "FROM products p, batch b "
				+ "WHERE p.product_name LIKE ? "
				+ "AND p.productID = b.productID AND isDiscontinued = '0' "
				+ "AND b.entry_date = (SELECT MAX(ba.entry_date) "
					+ " FROM batch ba "
					+ "	WHERE ba.productID = p.productID) "
				+ "GROUP BY p.product_name "
				+ "HAVING SUM(b.batch_quantity) == 0"
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, "%" + productname + "%");
			
			rs = ps.executeQuery();
			con.getConnection().close();
			

			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet viewProducts() {
		sQuery = "SELECT p.product_name, SUM(b.batch_quantity), p.selling_price "
				+ "FROM products p, batch b "
				+ "WHERE p.productID = b.productID AND isDiscontinued = '0' "
				+ "GROUP BY p.product_name "
				+ "ORDER BY p.product_name;";
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);

			rs = ps.executeQuery();
			con.getConnection().close();
			

			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet viewProductsWithPrice() {
		sQuery = "SELECT p.product_name, SUM(b.batch_quantity), b.buying_price "
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
			con.getConnection().close();
			
			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}