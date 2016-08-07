package ProjectBackEnd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

public class ProductManager {
	
	//this
	public ProductManager() {
		
	}
	
	public float getSellingPrice(int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT selling_price "
				+ "FROM products "
				+ "WHERE productID = '" + productID + "';";
		float sellingPrice = -1;

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				sellingPrice = rs.getFloat(1);
			}

			con.disconnect();
			
			rs.close();
			
			return sellingPrice;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public float getSellingPrice(String productname) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT selling_price FROM products WHERE product_name = ?;";
		float sellingPrice = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				sellingPrice = rs.getFloat(1);
			}

			con.disconnect();
			
			rs.close();
			
			return sellingPrice;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public String getProductName(int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT product_name "
				+ "FROM products "
				+ "WHERE productID = '" + productID + "';";
		String productName = null;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				productName = rs.getString(1);
			}

			con.disconnect();
			
			rs.close();
			
			return productName;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public int getLatestProductID() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT MAX(productID) FROM products;";
		int latestProductID = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				latestProductID = rs.getInt(1);
			}
			
			con.disconnect();

			rs.close();
			
			return latestProductID;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int getProductID(String productname) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT productID FROM products WHERE product_name = ?;";
		int productID = 0;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				productID = rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return productID;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getProductID(int row, String searchText) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT p.productID "
					+ "FROM products p "
					+ "WHERE p.product_name LIKE '%" + searchText + "%' "
					+ "ORDER BY p.product_name LIMIT 1 OFFSET " + row + ";";
		int productID = -1;
		
		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeQuery(sQuery);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				productID = rs.getInt(1);
			}

			con.disconnect();
			
			rs.close();
			
			return productID;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public ArrayList<Integer> getProductIDList() {	
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT productID FROM products ORDER BY product_name;";
		ArrayList<Integer> productIDList = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productIDList.add(rs.getInt(1));
			}

			con.disconnect();
			
			rs.close();
			
			return productIDList;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Integer> getProductIDList(String searchText) {	
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT productID FROM products WHERE product_name LIKE '%" + searchText + "%' ORDER BY product_name;";
		ArrayList<Integer> productIDList = new ArrayList<>();

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productIDList.add(rs.getInt(1));
			}
			
			con.disconnect();

			rs.close();
			
			return productIDList;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean checkDuplicates(String productname) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT product_name "
						+ "FROM products	"
						+ "WHERE product_name = ?;";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.setString(1, productname);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				con.disconnect();
				
				rs.close();
				
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void addProduct(String productname, double sellprice) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "INSERT INTO products(product_name, selling_price, isDiscontinued) "
						+ "VALUES('"+  productname + "', '"+ sellprice + "', '0');";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
			
			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void restockProduct(String productname, double sellprice, int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "UPDATE products "
					+ "SET product_name = '" + productname + "', selling_price = '" + sellprice + "' "
					+ "WHERE productID = "+ productID +";";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);

			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void discontinueProduct(int productID) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "UPDATE products "
			+ "SET isDiscontinued = '1' "
			+ "WHERE isDiscontinued = '0' AND productID = '"+ productID +"';";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);

			con.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}