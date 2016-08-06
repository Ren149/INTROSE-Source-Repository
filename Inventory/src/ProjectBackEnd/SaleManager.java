package ProjectBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnector.DBConnection;

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
    
    public void deductProductBatchQty(ArrayList<String> prodNameList, ArrayList<Integer> prodQtyList){
        ProductManager productManage = new ProductManager();
        BatchManager batchManage = new BatchManager();
        ArrayList<Integer> batchID = new ArrayList<>(); 
        

        for(int i=0; i < prodNameList.size(); i++){
            batchID = batchManage.getBatchIDofProductList(productManage.getProductID(prodNameList.get(i)));
            int sentinel = 0;
            for(int j=0; j < batchID.size(); j++){
                
                if(prodQtyList.get(i) >= batchManage.getEachBatchQuantity(batchID.get(j))){
                    prodQtyList.set(i, prodQtyList.get(i) - batchManage.getEachBatchQuantity(batchID.get(j)));
                    batchManage.changeBatchQtyToZero(batchID.get(j));
                }
                else if(prodQtyList.get(i) < batchManage.getEachBatchQuantity(batchID.get(j))){
                    if(sentinel == 0){
                    
                    batchManage.subtractBatchQty(batchID.get(j), batchManage.getEachBatchQuantity(batchID.get(j)), prodQtyList.get(i));
                    sentinel = 1;
                    }
                    
                }
            
            }
        }
        
        
    }
    
    public void recordLineItem(ArrayList<String> prodNameList)
	{
            SaleManager saleManage = new SaleManager();
            ProductManager prodManage = new ProductManager();
                for(int i=0; i < prodNameList.size(); i++){
		PreparedStatement ps;
		
		String sQuery = "INSERT INTO line_item(salesID, productID)"
						+ "VALUES('"+(saleManage.getLatestSalesID()+1)+"','"+ prodManage.getProductID(prodNameList.get(i))+"')";

		try {
			ps = con.getConnection().prepareStatement(sQuery);
			ps.executeUpdate(sQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
                }
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
