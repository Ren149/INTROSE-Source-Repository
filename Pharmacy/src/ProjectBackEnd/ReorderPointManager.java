package ProjectBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DBConnector.DBConnection;

public class ReorderPointManager {
	
	//this
	
	public ReorderPointManager()
	{
	}
	
	
	public int getHighReorderPoint() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT quantity "
				+ "FROM reorder_point "
				+ "WHERE item_type = 'HIGH' ;";
		int setDemand = 0;
			
			try {
				ps = con.getConnection().prepareStatement(sQuery);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					setDemand = rs.getInt(1);
				}	

				con.disconnect();
				
				rs.close();
				
				return setDemand;
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return setDemand;
		}
	
	public int getLowReorderPoint() {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sQuery = "SELECT quantity "
				+ "FROM reorder_point "
				+ "WHERE item_type = 'LOW' ;";
		int setDemand = 0;
			
			try {
				ps = con.getConnection().prepareStatement(sQuery);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					setDemand = rs.getInt(1);
				}	

				con.disconnect();
				
				rs.close();
				
				return setDemand;
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return setDemand;
		}
	


	public void setHighReorderPoint(int newPoint) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		
        String sQuery = "UPDATE reorder_point "
				+ "SET quantity= '"+ newPoint +"'"
				+ "WHERE item_type = 'HIGH' ;";
       
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
		
        	con.disconnect();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
	}
	
	public void setLowReorderPoint(int newPoint) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
        String sQuery = "UPDATE reorder_point "
				+ "SET quantity= '"+ newPoint +"'"
				+ "WHERE item_type = 'LOW' ;";
       
        try {
        	ps = con.getConnection().prepareStatement(sQuery);
        	ps.executeUpdate(sQuery);
		
        	con.disconnect();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
	}
	

	public void setReorderPoint(String demand, int totalQuantity) {
		DBConnection con = new DBConnection();
		PreparedStatement ps;
		String sQuery = "INSERT INTO reorder_point(item_type, quantity) "
					+ "VALUES('"+ demand + "', '"+ totalQuantity + "');";
			
			try {
				ps = con.getConnection().prepareStatement(sQuery);
				ps.executeUpdate(sQuery);
				
				con.disconnect();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}


