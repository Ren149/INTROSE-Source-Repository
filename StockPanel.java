package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class StockPanel extends JPanel implements ActionListener{
	//PRODUCTLIST PANEL
	private JPanel pnlProductList = new JPanel();
	private JTextField txtSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");	
	private JLabel lblSort = new JLabel("Sort:");
	private JComboBox cboSort = new JComboBox();
	private JTable tblProductList = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	
	//FORM PANEL
	private JPanel pnlForm = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
	//ADDNEWITEMFORM PANEL
	private JPanel pnlAddNewItemForm = new JPanel();
	private JTextField txtItemNameAdd = new JTextField();
	private JTextField txtBuyingPriceAdd = new JTextField();
	private JTextField txtSellingPriceAdd = new JTextField();
	private JTextField txtQuantityAdd = new JTextField();
	private JLabel lblItemNameAdd = new JLabel("Item name:");
	private JLabel lblBuyingPriceAdd = new JLabel("Buying price:");
	private JLabel lblSellingPriceAdd = new JLabel("Selling price:");
	private JLabel lblPesosAdd = new JLabel("Pesos");
	private JLabel lblPesosAdd_1 = new JLabel("Pesos");
	private JLabel lblQuantityAdd = new JLabel("Quantity:");
	private JLabel lblPiecesAdd = new JLabel("Pieces");
	private JLabel lblFeedbackAdd = new JLabel("Add details above to add product.");
	private JButton btnAdd = new JButton("Add");
	private Component vstAdd = Box.createVerticalStrut(20);
	
	//RESTOCKITEMFORM PANEL
	private JPanel pnlRestockItemForm = new JPanel();
	private JTextField txtItemNameRestock = new JTextField();
	private JTextField txtBuyingPriceRestock = new JTextField();
	private JTextField txtSellingPriceRestock = new JTextField();
	private JTextField txtQuantityRestock = new JTextField();
	private JLabel lblItemNameRestock = new JLabel("Item name:");
	private JLabel lblBuyingPriceRestock = new JLabel("Buying Price:");
	private JLabel lblSellingPriceRestock = new JLabel("Selling Price:");
	private JLabel lblQuantityRestock = new JLabel("Quantity:");
	private JLabel lblPesosRestock = new JLabel("Pesos");
	private JLabel lblPesosRestock_1 = new JLabel("Pesos");
	private JLabel lblPiecesRestock = new JLabel("Pieces");
	private JLabel lblFeedbackRestock = new JLabel("??? successfully added to the inventory.");
	private JLabel lblHelpRestock = new JLabel("Select an item from the left.");
	private JLabel lblHelpRestock_1 = new JLabel("<html>Toggle the update button to keep<br>the old selling price for this item<br>or to set a new one</html>");
	private Component hstRestock = Box.createHorizontalStrut(20);
	private Component vstRestock = Box.createVerticalStrut(20);
	private JToggleButton tglbtnUpdateRestock = new JToggleButton("Update");
	private JButton btnRestock = new JButton("Restock");
	
	//MANAGER INITIALIZERS
	private ProductManager productManage = new ProductManager();
	private BatchManager batchManage = new BatchManager();
	
	public StockPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][]", "[]"));
		
		//ELEMENTS IN THE PRODUCTLIST PANEL
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(10);
		
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Item Name", "Quantity"
			}
		)
		);
		tblProductList.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblProductList.getColumnModel().getColumn(0).setMinWidth(100);
		tblProductList.getColumnModel().getColumn(1).setMinWidth(80);
		tblProductList.getColumnModel().getColumn(1).setMaxWidth(80);
		
		scrollPane.setViewportView(tblProductList);
		
		pnlProductList.setBackground(new Color(255, 255, 255));
		pnlProductList.setLayout(new MigLayout("", "[grow][][grow][]", "[][grow]"));
		pnlProductList.add(txtSearch, "cell 0 0,grow");
		pnlProductList.add(btnSearch, "cell 1 0,alignx left,growy");
		pnlProductList.add(scrollPane, "cell 0 1 4 1,grow");
		
		pnlForm.setBackground(new Color(255, 255, 255));
		pnlForm.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//ELEMENTS IN THE ADD NEW ITEM PANEL
		lblItemNameAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtItemNameAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtItemNameAdd.setColumns(10);
		
		lblBuyingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtBuyingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPriceAdd.setEditable(true);
		txtBuyingPriceAdd.setColumns(10);
		
		lblPesosAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblSellingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtSellingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPriceAdd.setEditable(true);
		txtSellingPriceAdd.setColumns(10);
		
		lblPesosAdd_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblQuantityAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtQuantityAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantityAdd.setColumns(10);
		
		lblPiecesAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblFeedbackAdd.setForeground(new Color(0, 128, 128));
		lblFeedbackAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnAdd.setMinimumSize(new Dimension(100, 35));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAdd.addActionListener(this);
																			
		pnlAddNewItemForm.setBackground(Color.WHITE);
		pnlAddNewItemForm.setLayout(new MigLayout("", "[80:80px][grow]", "[][][][][][][]"));
		pnlAddNewItemForm.add(lblItemNameAdd, "cell 0 0,alignx right");
		pnlAddNewItemForm.add(txtItemNameAdd, "cell 1 0,growx");
		pnlAddNewItemForm.add(lblBuyingPriceAdd, "cell 0 1,alignx right");
		pnlAddNewItemForm.add(txtBuyingPriceAdd, "flowx,cell 1 1");
		pnlAddNewItemForm.add(lblPesosAdd, "cell 1 1");
		pnlAddNewItemForm.add(lblSellingPriceAdd, "cell 0 2,alignx right");
		pnlAddNewItemForm.add(txtSellingPriceAdd, "flowx,cell 1 2");
		pnlAddNewItemForm.add(lblPesosAdd_1, "cell 1 2");
		pnlAddNewItemForm.add(lblQuantityAdd, "cell 0 3,alignx right");
		pnlAddNewItemForm.add(txtQuantityAdd, "flowx,cell 1 3");
		pnlAddNewItemForm.add(lblPiecesAdd, "cell 1 3");
		pnlAddNewItemForm.add(vstAdd, "cell 1 4");
		pnlAddNewItemForm.add(lblFeedbackAdd, "cell 0 5 2 1,alignx right");
		pnlAddNewItemForm.add(btnAdd, "cell 1 6,alignx right");
		
		
		//ELEMENTS IN THE RESTOCK ITEM PANEL
		lblItemNameRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtItemNameRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtItemNameRestock.setEditable(false);
		txtItemNameRestock.setColumns(10);
				
		lblHelpRestock.setFont(new Font("Segoe UI", Font.ITALIC, 11));
						
		lblBuyingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
							
		txtBuyingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPriceRestock.setColumns(10);
										
		lblPiecesRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
										
		lblSellingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
										
		txtSellingPriceRestock.setEditable(false);
		txtSellingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPriceRestock.setColumns(10);
		
		lblQuantityRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtQuantityRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantityRestock.setColumns(10);
		
		lblHelpRestock_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
												
		lblPesosRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
														
		lblPesosRestock_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		lblFeedbackRestock.setForeground(new Color(0, 128, 128));
		lblFeedbackRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnRestock.setMinimumSize(new Dimension(100, 35));
		btnRestock.setForeground(Color.WHITE);
		btnRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRestock.setBackground(new Color(0, 204, 0));
		
		tglbtnUpdateRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tglbtnUpdateRestock.setBackground(new Color(51, 255, 153));
		tglbtnUpdateRestock.addActionListener(this);
		
		pnlRestockItemForm.setBackground(Color.WHITE);
		pnlRestockItemForm.setLayout(new MigLayout("", "[80:80px][grow]", "[][][][][][][][][]"));
		pnlRestockItemForm.add(lblItemNameRestock, "cell 0 0,alignx right");
		pnlRestockItemForm.add(txtItemNameRestock, "cell 1 0,growx");
		pnlRestockItemForm.add(lblHelpRestock, "cell 1 1,alignx left,aligny top");
		pnlRestockItemForm.add(lblBuyingPriceRestock, "cell 0 2,alignx trailing");
		pnlRestockItemForm.add(txtBuyingPriceRestock, "flowx,cell 1 2,alignx left");
		pnlRestockItemForm.add(lblSellingPriceRestock, "cell 0 3,alignx trailing");
		pnlRestockItemForm.add(txtSellingPriceRestock, "flowx,cell 1 3,alignx left");
		pnlRestockItemForm.add(lblHelpRestock_1, "cell 1 4,alignx left,aligny top");
		pnlRestockItemForm.add(lblQuantityRestock, "cell 0 5,alignx trailing");
		pnlRestockItemForm.add(txtQuantityRestock, "flowx,cell 1 5,alignx left");
		pnlRestockItemForm.add(lblPesosRestock, "cell 1 2");
		pnlRestockItemForm.add(lblPesosRestock_1, "cell 1 3");
		pnlRestockItemForm.add(lblPiecesRestock, "cell 1 5");
		pnlRestockItemForm.add(hstRestock, "cell 1 3");
		pnlRestockItemForm.add(vstRestock, "cell 1 6");
		pnlRestockItemForm.add(lblFeedbackRestock, "cell 0 7 2 1,alignx right");
		pnlRestockItemForm.add(btnRestock, "cell 1 8,alignx right");
		pnlRestockItemForm.add(tglbtnUpdateRestock, "cell 1 3");
		
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Add New Item", null, pnlAddNewItemForm, null);
		tabbedPane.addTab("Restock Item", null, pnlRestockItemForm, null);
		
		pnlForm.add(tabbedPane, "cell 0 0,grow");
		
		add(pnlProductList, "cell 0 0,grow");
		add(pnlForm, "cell 1 0,grow");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tglbtnUpdateRestock)) {
			if(tglbtnUpdateRestock.isSelected()) {
				txtSellingPriceRestock.setEditable(true);
			}
			else {
				txtSellingPriceRestock.setEditable(false);
			}
			
			if(e.getSource().equals(btnAdd)) {
				productManage.addProduct(txtItemNameAdd.getText(), Double.parseDouble(txtSellingPriceAdd.getText()));
				batchManage.addFirstBatch(Integer.parseInt(txtQuantityAdd.getText()), Double.parseDouble(txtBuyingPriceAdd.getText()));
				lblFeedbackRestock.setText(txtItemNameAdd.getText() +"successfully added to the inventory!");
			}
		}
	}
}
