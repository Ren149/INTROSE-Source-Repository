package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.time.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends JPanel implements ActionListener {
	private int rowCount = 0;

	private JLabel lblProductListTitle = new JLabel("Product List");
	private JLabel lblItemCount = new JLabel("Displaying " + rowCount + " items");
	private JTextField txtProductSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	private JTable tblProductListTable;
	private JScrollPane scrProduct = new JScrollPane();
	private ProductManager productManage = new ProductManager();
	private final JScrollPane scrBatch = new JScrollPane();
	private final JTable table = new JTable();
	private final JButton btnRestock = new JButton("Restock");
	private final JButton btnAddProduct = new JButton("Add Product");
	private final JLabel lblBatches = new JLabel("Batches");
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JButton btnDiscontinue = new JButton("Discontinue");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblTimestamp = new JLabel("New label");
	
	public ProductListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][][:350px:350px]", "[grow]"));
		add(panel_2, "cell 0 0,grow");
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new MigLayout("", "[][grow]", "[][][][grow][][]"));
		panel_2.add(lblProductListTitle, "cell 0 0");
		
		lblProductListTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTimestamp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		panel_2.add(lblTimestamp, "cell 0 1 2 1,alignx left");
		panel_2.add(txtProductSearch, "flowx,cell 0 2,growy");
		
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductSearch.setColumns(20);
		panel_2.add(btnSearch, "cell 1 2,alignx left,growy");
		
		btnSearch.setForeground(Color.DARK_GRAY);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addActionListener(this);
		
		tblProductListTable = new JTable();
				panel_2.add(scrProduct, "cell 0 3 2 1,grow");
		
				scrProduct.setViewportView(tblProductListTable);
				
				tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_2.add(lblItemCount, "cell 0 4,aligny center");
				
				lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnDiscontinue.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnDiscontinue.setBackground(Color.WHITE);
				btnDiscontinue.setForeground(Color.DARK_GRAY);
				
				panel_2.add(btnDiscontinue, "flowx,cell 1 4,alignx right,growy");
				panel_2.add(btnRestock, "cell 1 4,alignx right,growy");
				btnRestock.setForeground(Color.DARK_GRAY);
				btnRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnRestock.setBackground(Color.WHITE);
				panel_2.add(btnAddProduct, "cell 1 4,alignx right,growy");
				btnAddProduct.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnAddProduct.setForeground(Color.DARK_GRAY);
				btnAddProduct.setBackground(new Color(0, 128, 0));
				btnAddProduct.addActionListener(this);
				
		separator.setForeground(new Color(211, 211, 211));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(255, 255, 255));
		
		add(separator, "cell 1 0,alignx center,growy");
		add(panel_1, "cell 2 0,growy");
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new MigLayout("", "[]", "[][grow]"));
		panel_1.add(lblBatches, "cell 0 0,alignx left");
		lblBatches.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"BatchID", "Entry Date", "Buying Price", "Expiry Date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(0).setMinWidth(55);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(20);
		table.getColumnModel().getColumn(2).setMinWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(20);
		
		panel_1.add(scrBatch, "cell 0 1,alignx center,growy");
		
		scrBatch.setViewportView(table);
		
		viewTable();
	}

	public void viewTable() {
		tblProductListTable.setModel(productManage.viewProducts());
		rowCount = tblProductListTable.getRowCount();
		lblItemCount.setText("Displaying " + rowCount + " items");
	}
	
	public void searchTableWithPrices() {
		if(txtProductSearch.getText().toString().equals("")) {
			viewTable();
		}
		else
			tblProductListTable.setModel(productManage.searchProductWithPrice(txtProductSearch.getText()));
			rowCount = tblProductListTable.getRowCount();
			lblItemCount.setText("Displaying " + rowCount + " items");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch)) {
			searchTableWithPrices();
		}
		else if(e.getSource().equals(btnAddProduct)) {
			AddProductPanel frmAddProduct = new AddProductPanel();
			frmAddProduct.addWindowListener(new WindowAdapter(){
			    public void windowClosed(WindowEvent e) {
					viewTable();
			    }
			}
					);
		}
	}
	
	
	
	public void update() {
		LocalDate d = LocalDate.now();
		String timestamp = "as of ";
		switch(d.getMonthValue()) {
			case 1: timestamp += "January "; break;
			case 2: timestamp += "February "; break;
			case 3: timestamp += "March "; break;
			case 4: timestamp += "April "; break;
			case 5: timestamp += "May "; break;
			case 6: timestamp += "June "; break;
			case 7: timestamp += "July "; break;
			case 8: timestamp += "August "; break;
			case 9: timestamp += "September "; break;
			case 10: timestamp += "October "; break;
			case 11: timestamp += "November "; break;
			case 12: timestamp += "December "; break;
		}
		timestamp += d.getDayOfMonth() + " " + d.getYear();
		lblTimestamp.setText(timestamp);
		viewTable();
	}
}