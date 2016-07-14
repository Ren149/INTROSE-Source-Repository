package DBConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private String driverName, url, database, username, password;
	
	public DBConnection() {
		driverName = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/";
		database = "pharmacy";
		username = "root";
		password = "duodecim012+";
	}	
	
	public Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(getUrl() + getDatabase(), getUsername(), getPassword());
			return con;	
		} catch(SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl() {
		this.url = url;
	}
	
	public String getDatabase() {
		return database;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
} 

