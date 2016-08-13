//MILESTONE
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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

public class ExpiryPanel extends JPanel implements ItemListener {
	private JTable tblExpiryList = new JTable();;
	private JLabel lblTitle = new JLabel("Expiry List");
	private JScrollPane scrNearExpiredProducts = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JRadioButton rdbtnThisMonth = new JRadioButton("This month");
	private final JRadioButton rdbtnWithinTheNext = new JRadioButton("Within the next");
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();
	private final JLabel lblItemCount = new JLabel("");
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox cboMonth = new JComboBox();
	private final JLabel lblMonths = new JLabel("month");
	
	public ExpiryPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow]"));
		
		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		tblExpiryList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblExpiryList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrNearExpiredProducts.setViewportView(tblExpiryList);

		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));

		rdbtnThisMonth.setBackground(Color.WHITE);
		rdbtnThisMonth.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		rdbtnWithinTheNext.setBackground(Color.WHITE);
		rdbtnWithinTheNext.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		buttonGroup.add(rdbtnThisMonth);
		buttonGroup.add(rdbtnWithinTheNext);
		
		cboMonth.setBackground(Color.WHITE);
		cboMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		cboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboMonth.addItemListener(this);
		
		lblMonths.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		add(lblTitle, "cell 0 0 2 1");
		add(rdbtnThisMonth, "flowx,cell 0 1,growy");
		add(scrNearExpiredProducts, "cell 0 2 2 1,grow");
		add(lblItemCount, "cell 1 1,alignx right,aligny bottom");
		add(rdbtnWithinTheNext, "cell 0 1,growy");
		add(cboMonth, "cell 0 1,growy");
		add(lblMonths, "cell 0 1,growy");
		
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
			
			switch(rowCount) {
				case 0 : lblItemCount.setText("No items displayed."); break;
				case 1 : lblItemCount.setText("Displaying " + rowCount + " product"); break;
				default: lblItemCount.setText("Displaying " + rowCount + " products"); break;
			}
		}
	
	public void update() {
		loadExpiryList();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(cboMonth)) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(cboMonth.getSelectedIndex() == 0) {
					lblMonths.setText("month");
				}
				else {
					lblMonths.setText("months");
				}
			}
		}
	}
}