package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ExpiryPanel extends JPanel {
	private JTable tblExpiryList = new JTable();;
	private JLabel lblTitle = new JLabel("Expiry List");
	private JScrollPane scrNearExpiredProducts = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JRadioButton rdbtnThisMonth = new JRadioButton("This month");
	private final JRadioButton rdbtnPastMonth = new JRadioButton("Past month");
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();
	private final JLabel lblItemCount = new JLabel("");
	
	public ExpiryPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[][grow]", "[][][grow]"));
		
		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		add(lblTitle, "cell 0 0");
		
		tblExpiryList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblExpiryList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblItemCount.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		add(lblItemCount, "cell 1 0");
		
		scrNearExpiredProducts.setViewportView(tblExpiryList);
		add(scrNearExpiredProducts, "cell 1 1 1 2,grow");
		
		buttonGroup.add(rdbtnThisMonth);
		rdbtnThisMonth.setBackground(Color.WHITE);
		rdbtnThisMonth.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		add(rdbtnThisMonth, "cell 0 1");
		buttonGroup.add(rdbtnPastMonth);
		
		rdbtnPastMonth.setBackground(Color.WHITE);
		rdbtnPastMonth.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		add(rdbtnPastMonth, "cell 0 2,aligny top");
		
		loadExpiryList();
	}
	
	public void loadExpiryList() {
		ArrayList<Integer> id;
		DefaultTableModel expiryListTableModel = new DefaultTableModel();
		expiryListTableModel.setColumnIdentifiers(new String[] {"Product Name", "Lot Number", "Expiry Date", "Quantity"});
		
			id = pm.getProductIDList(1);
			
			for(int i : id) {
				String productName = pm.getProductName(i);
				String lotnumber = bm.getLotNumber(i);
				String quantity = bm.getTotalQuantity(i) + "";
				
				String expiryDate = "";
				
				switch(bm.getExpiryMonth(i)) {
					case 0: expiryDate += "Jan "; break;
					case 1: expiryDate += "Feb "; break;
					case 2: expiryDate += "Mar "; break;
					case 3: expiryDate += "Apr "; break;
					case 4: expiryDate += "May "; break;
					case 5: expiryDate += "Jun "; break;
					case 6: expiryDate += "Jul "; break;
					case 7: expiryDate += "Aug "; break;
					case 8: expiryDate += "Sep "; break;
					case 9: expiryDate += "Oct "; break;
					case 10: expiryDate += "Nov "; break;
					case 11: expiryDate += "Dec "; break;
				}
				
				expiryDate += bm.getExpiryYear(i);
				
				expiryListTableModel.addRow(new Object[] {productName, lotnumber, expiryDate, quantity});
			}
			

			tblExpiryList.setModel(expiryListTableModel);
			//tblExpiryList.getColumnModel().getColumn(0).setMinWidth(55);
			//tblExpiryList.getColumnModel().getColumn(0).setMaxWidth(55);
			//tblExpiryList.getColumnModel().getColumn(2).setMinWidth(55);
			//tblExpiryList.getColumnModel().getColumn(2).setMaxWidth(55);
			
			DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
			rendererRight.setHorizontalAlignment(SwingConstants.RIGHT);
			
			tblExpiryList.getColumnModel().getColumn(2).setCellRenderer(rendererRight);
			tblExpiryList.getColumnModel().getColumn(3).setCellRenderer(rendererRight);
			
			int rowCount = tblExpiryList.getRowCount();
			
			/*switch(rowCount) {
				case 0 : lblItemCount.setText("No items displayed."); break;
				case 1 : lblItemCount.setText("Displaying " + rowCount + " product"); break;
				default: lblItemCount.setText("Displaying " + rowCount + " products"); break;
			}*/
		}
	
	public void update() {
		
	}
	
}