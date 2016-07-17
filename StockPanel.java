package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import ProjectBackEnd.SalesManager;
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
	private JLabel lblExpiryAdd = new JLabel("Expiry:");
	private JLabel lblQuantityAdd = new JLabel("Quantity:");
	private JLabel lblPiecesAdd = new JLabel("Pieces");
	private JLabel lblFeedbackAdd = new JLabel("Add details above to add product.");
	private JButton btnAdd = new JButton("Add");
	private Component vstAdd = Box.createVerticalStrut(20);
	private JComboBox cboExpiryMonthAdd = new JComboBox();
	private JComboBox cboExpiryYearAdd = new JComboBox();
	
	//RESTOCKITEMFORM PANEL
	private JPanel pnlRestockItemForm = new JPanel();
	private JTextField txtItemNameRestock = new JTextField();
	private JTextField txtBuyingPriceRestock = new JTextField();
	private JTextField txtSellingPriceRestock = new JTextField();
	private JTextField txtQuantityRestock = new JTextField();
	private JLabel lblItemNameRestock = new JLabel("Item name:");
	private JLabel lblBuyingPriceRestock = new JLabel("Buying price:");
	private JLabel lblSellingPriceRestock = new JLabel("Selling price:");
	private JLabel lblQuantityRestock = new JLabel("Quantity:");
	private JLabel lblPesosRestock = new JLabel("Pesos");
	private JLabel lblPesosRestock_1 = new JLabel("Pesos");
	private JLabel lblPiecesRestock = new JLabel("Pieces");
	private JLabel lblExpiryRestock = new JLabel("Expiry:");
	private JLabel lblFeedbackRestock = new JLabel("Add details above to restock product.");
	private JComboBox cboExpiryMonthRestock = new JComboBox();
	private JComboBox cboExpiryYearRestock = new JComboBox();
	private Component hstRestock = Box.createHorizontalStrut(20);
	private Component vstRestock = Box.createVerticalStrut(20);
	private JToggleButton tglbtnUpdateRestock = new JToggleButton("Update");
	private JButton btnRestock = new JButton("Restock");

	//MANAGER INITIALIZERS
	private ProductManager productManage = new ProductManager();
	private BatchManager batchManage = new BatchManager();
	private ProductListPanel productPanel = new ProductListPanel();
	
	public StockPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][]", "[]"));
		
		//ELEMENTS IN THE PRODUCTLIST PANEL
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(10);
		
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.addActionListener(this);
		
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.setModel(productManage.viewProducts());
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
		txtBuyingPriceAdd.setText("0");
		
		lblPesosAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblSellingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtSellingPriceAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPriceAdd.setEditable(true);
		txtSellingPriceAdd.setColumns(10);
		txtSellingPriceAdd.setText("0");
		
		lblPesosAdd_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblQuantityAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtQuantityAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantityAdd.setColumns(10);
		txtQuantityAdd.setText("0");
		
		lblPiecesAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblExpiryAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboExpiryMonthAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryMonthAdd.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		
		cboExpiryYearAdd.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020"}));
		cboExpiryYearAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblFeedbackAdd.setForeground(new Color(0, 128, 128));
		lblFeedbackAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnAdd.setMinimumSize(new Dimension(100, 35));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAdd.addActionListener(this);

		pnlAddNewItemForm.setBackground(Color.WHITE);
		pnlAddNewItemForm.setLayout(new MigLayout("", "[80:80px][grow]", "[][][][][][][][]"));
		pnlAddNewItemForm.add(lblItemNameAdd, "cell 0 0,alignx right");
		pnlAddNewItemForm.add(txtItemNameAdd, "cell 1 0,growx");
		pnlAddNewItemForm.add(lblBuyingPriceAdd, "cell 0 1,alignx right");
		pnlAddNewItemForm.add(txtBuyingPriceAdd, "flowx,cell 1 1");
		pnlAddNewItemForm.add(lblPesosAdd, "cell 1 1");
		pnlAddNewItemForm.add(lblSellingPriceAdd, "cell 0 2,alignx right");
		pnlAddNewItemForm.add(txtSellingPriceAdd, "flowx,cell 1 2");
		pnlAddNewItemForm.add(lblPesosAdd_1, "cell 1 2");
		pnlAddNewItemForm.add(cboExpiryMonthAdd, "flowx,cell 1 3,alignx left");
		pnlAddNewItemForm.add(lblQuantityAdd, "cell 0 4,alignx right");
		pnlAddNewItemForm.add(txtQuantityAdd, "flowx,cell 1 4");
		pnlAddNewItemForm.add(lblPiecesAdd, "cell 1 4");
		pnlAddNewItemForm.add(lblExpiryAdd, "cell 0 3,alignx trailing");
		pnlAddNewItemForm.add(vstAdd, "cell 1 5");
		pnlAddNewItemForm.add(lblFeedbackAdd, "cell 0 6 2 1,alignx right");
		pnlAddNewItemForm.add(btnAdd, "cell 1 7,alignx right");
		pnlAddNewItemForm.add(cboExpiryYearAdd, "cell 1 3");
		
		//ELEMENTS IN THE RESTOCK ITEM PANEL
		lblItemNameRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtItemNameRestock.setText("Select an item on the left:");
		
		txtItemNameRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtItemNameRestock.setEditable(false);
		txtItemNameRestock.setColumns(10);
						
		lblBuyingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPriceRestock.setEditable(false);
							
		txtBuyingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPriceRestock.setColumns(10);
		txtBuyingPriceRestock.setText("0");
										
		lblPiecesRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
										
		lblSellingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
										
		txtSellingPriceRestock.setEditable(false);
		txtSellingPriceRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPriceRestock.setColumns(10);
		txtSellingPriceRestock.setText("0");
		
		lblQuantityRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtQuantityRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantityRestock.setColumns(10);
		txtQuantityRestock.setText("0");
												
		lblPesosRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
														
		lblPesosRestock_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		lblFeedbackRestock.setForeground(new Color(0, 128, 128));
		lblFeedbackRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pnlRestockItemForm.setBackground(Color.WHITE);
		pnlRestockItemForm.setLayout(new MigLayout("", "[80:80px][grow][]", "[][][][][][][][]"));
		pnlRestockItemForm.add(lblItemNameRestock, "cell 0 0,alignx right");
		pnlRestockItemForm.add(txtItemNameRestock, "cell 1 0 2 1,growx");
		pnlRestockItemForm.add(lblBuyingPriceRestock, "cell 0 1,alignx trailing");
		pnlRestockItemForm.add(txtBuyingPriceRestock, "flowx,cell 1 1,alignx left");
		pnlRestockItemForm.add(lblSellingPriceRestock, "cell 0 2,alignx trailing");
		pnlRestockItemForm.add(txtSellingPriceRestock, "flowx,cell 1 2,alignx left");
		
		tglbtnUpdateRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tglbtnUpdateRestock.setBackground(new Color(51, 255, 153));
		tglbtnUpdateRestock.addActionListener(this);
		
		pnlRestockItemForm.add(tglbtnUpdateRestock, "cell 2 1 1 2,growy");
		
		lblExpiryRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboExpiryMonthRestock.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cboExpiryMonthRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		cboExpiryYearRestock.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020"}));
		cboExpiryYearRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnRestock.setMinimumSize(new Dimension(100, 35));
		btnRestock.setForeground(Color.WHITE);
		btnRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRestock.setBackground(new Color(0, 204, 0));
		btnRestock.addActionListener(this);
		
		pnlRestockItemForm.add(cboExpiryMonthRestock, "flowx,cell 1 3,alignx left");
		pnlRestockItemForm.add(lblQuantityRestock, "cell 0 4,alignx trailing");
		pnlRestockItemForm.add(txtQuantityRestock, "flowx,cell 1 4 2 1,alignx left");
		pnlRestockItemForm.add(lblPesosRestock, "cell 1 1");
		pnlRestockItemForm.add(lblPesosRestock_1, "cell 1 2");
		pnlRestockItemForm.add(lblPiecesRestock, "cell 1 4");
		pnlRestockItemForm.add(lblExpiryRestock, "cell 0 3,alignx trailing");
		pnlRestockItemForm.add(hstRestock, "cell 1 2");
		pnlRestockItemForm.add(vstRestock, "cell 1 5");
		pnlRestockItemForm.add(lblFeedbackRestock, "cell 0 6 3 1,alignx right");
		pnlRestockItemForm.add(btnRestock, "cell 0 7 3 1,alignx right");
		pnlRestockItemForm.add(cboExpiryYearRestock, "cell 1 3");
		
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Add New Item", null, pnlAddNewItemForm, null);
		tabbedPane.addTab("Restock Item", null, pnlRestockItemForm, null);
		
		pnlForm.add(tabbedPane, "cell 0 0,grow");
		
		add(pnlProductList, "cell 0 0,grow");
		add(pnlForm, "cell 1 0,grow");
	}
	
	public void togglePriceUpdate()
	{			
	if(tglbtnUpdateRestock.isSelected()) 
		{
		txtBuyingPriceRestock.setEditable(true);
		txtSellingPriceRestock.setEditable(true);
	}
	else 
	{
		txtBuyingPriceRestock.setEditable(false);
		txtSellingPriceRestock.setEditable(false);
	}
	}
	
	public void restockItem()
	{
		if((txtSellingPriceRestock.isEditable() == true && Double.parseDouble(txtSellingPriceRestock.getText()) <= 0) ||
			(txtBuyingPriceRestock.isEditable() == true && Double.parseDouble(txtBuyingPriceRestock.getText()) <= 0)||
			Integer.parseInt(txtQuantityRestock.getText()) <= 0)
		{
			lblFeedbackRestock.setText("Input cannot be added!");
			txtItemNameRestock.setText("");
		}
		else
		{
			if(txtBuyingPriceRestock.isEditable() == true)
			{
				if(txtSellingPriceRestock.isEditable() == true)
				{
					productManage.restockProduct(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString(),
							Double.parseDouble(txtSellingPriceRestock.getText()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
					batchManage.restockBatch(Integer.parseInt(txtQuantityRestock.getText()),
							Double.parseDouble(txtBuyingPriceRestock.getText()),
							cboExpiryMonthRestock.getSelectedIndex(),
							Integer.parseInt(cboExpiryYearRestock.getSelectedItem().toString()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
				}
				else
				{
					productManage.restockProduct(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString(),
							productManage.getSellingPrice(productManage.getProductID(tblProductList.getSelectedRow())),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
					batchManage.restockBatch(Integer.parseInt(txtQuantityRestock.getText()),
							Double.parseDouble(txtBuyingPriceRestock.getText()),
							cboExpiryMonthRestock.getSelectedIndex(),
							Integer.parseInt(cboExpiryYearRestock.getSelectedItem().toString()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
				}
			}
			else
			{
				if(txtSellingPriceRestock.isEditable() == true)
				{
					productManage.restockProduct(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString(),
							Double.parseDouble(txtSellingPriceRestock.getText()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
					batchManage.restockBatch(Integer.parseInt(txtQuantityRestock.getText()),
							batchManage.getBuyingPrice(productManage.getProductID(tblProductList.getSelectedRow()))
							, cboExpiryMonthRestock.getSelectedIndex(),
							Integer.parseInt(cboExpiryYearRestock.getSelectedItem().toString()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
				}
				else
				{
					productManage.restockProduct(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString(),
							productManage.getSellingPrice(productManage.getProductID(tblProductList.getSelectedRow())),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
					batchManage.restockBatch(Integer.parseInt(txtQuantityRestock.getText()),
							batchManage.getBuyingPrice(productManage.getProductID(tblProductList.getSelectedRow())),
							cboExpiryMonthRestock.getSelectedIndex(),
							Integer.parseInt(cboExpiryYearRestock.getSelectedItem().toString()),
							productManage.getProductID(tblProductList.getValueAt(tblProductList.getSelectedRow(), 0).toString()));
				}
				}
			lblFeedbackRestock.setText((String) tblProductList.getModel().getValueAt(tblProductList.getSelectedRow(), 0) +" restocked successfully!");
			}
	}
	public void addItem()
	{
		if(Double.parseDouble(txtSellingPriceAdd.getText()) <= 0 ||
		   Double.parseDouble(txtBuyingPriceAdd.getText()) <= 0||
		   Integer.parseInt(txtQuantityAdd.getText()) <= 0||
		   txtItemNameAdd.getText().equals(""))
		{
			lblFeedbackAdd.setText("Input cannot be added!");
		}
		else if(productManage.checkDuplicates(txtItemNameAdd.getText()))
		{
			lblFeedbackAdd.setText("Input has been added!");
		}
		else
		{
		tblProductList.getSelectionModel().clearSelection();
		productManage.addProduct(txtItemNameAdd.getText(),
				Double.parseDouble(txtSellingPriceAdd.getText()));
		batchManage.addBatch(Integer.parseInt(txtQuantityAdd.getText()),
				Double.parseDouble(txtBuyingPriceAdd.getText()),
				cboExpiryMonthAdd.getSelectedIndex(),
				Integer.parseInt(cboExpiryYearAdd.getSelectedItem().toString()));
		tblProductList.clearSelection();
		tblProductList.setModel(productManage.viewProducts());
		lblFeedbackAdd.setText(txtItemNameAdd.getText() +" successfully added to the inventory!");
		}
	}
	
	public void searchTable()
	{
		if(txtSearch.getText().toString().equals(""))
		{
			tblProductList.setModel(productManage.viewProducts());
		}
			else
		tblProductList.setModel(productManage.searchProduct(txtSearch.getText()));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tglbtnUpdateRestock)) {
			togglePriceUpdate();
			this.repaint();
		}
			if(e.getSource().equals(btnAdd)) {
				addItem();
				this.repaint();
			}
		if(e.getSource().equals(btnSearch))
		{
			searchTable();
			this.repaint();
		}
		if(e.getSource().equals(btnRestock))
		{
			restockItem();
			this.repaint();
		}
		}
}
