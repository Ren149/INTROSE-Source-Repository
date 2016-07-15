<<<<<<< HEAD
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends JPanel implements ActionListener{
	//TABLE PANEL
	private final JPanel pnlProductList = new JPanel();
	private JTextField txtSearch = new JTextField();
	private JScrollPane scrollPane = new JScrollPane();
	private JTable tblProductList = new JTable();
	private JButton btnSearch = new JButton("Search");		
	private JButton btnExportList = new JButton("Export List");
	
	//ADD ITEM PANEL
	private JPanel pnlAddItem = new JPanel();
	private JTextField txtItemName = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTextField txtSellingPrice = new JTextField();
	private JLabel lblEnterItemName = new JLabel("Item name:");
	private JLabel lblEnterBuyingPrice = new JLabel("Buying price:");
	private JLabel lblEnterSellingPrice = new JLabel("Selling price:");
	private JLabel lblPesos = new JLabel("Pesos");
	private JLabel lblPesos_1 = new JLabel("Pesos");
	private JLabel lblAddFeedback = new JLabel("Add details above to add product.");
	private JButton btnAdd = new JButton("Add");
	private Component verticalStrut = Box.createVerticalStrut(20);
	
	//DISCONTINUE PANEL
	private JPanel pnlDiscontinueItem = new JPanel();
	private JLabel lblDiscontinueHelp = new JLabel("<html>\r\nSelect an item from the left<br> to remove from the product list.\r\n</html>");
	private JLabel lblDiscontinueFeedback = new JLabel("Select an item to discontinue it.");
	private JButton btnDiscontinue = new JButton("Discontinue");
	private Component verticalStrut_1 = Box.createVerticalStrut(20);
	
	//MANAGER INITIALIZERS
	private ProductManager productManage = new ProductManager();
	private BatchManager batchManage = new BatchManager();
	
	public ProductListPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][]", "[][grow]"));
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		//ELEMENTS IN THE TABLE PANEL
		txtSearch.setColumns(10);
		
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.addActionListener(this);
		
		btnExportList.setBackground(new Color(0, 153, 153));
		btnExportList.setForeground(new Color(255, 255, 255));
		btnExportList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.setModel(productManage.viewProductsWithPrice());
		tblProductList.getColumnModel().getColumn(0).setMinWidth(100);
		tblProductList.getColumnModel().getColumn(1).setMinWidth(90);
		tblProductList.getColumnModel().getColumn(1).setMaxWidth(90);
		tblProductList.getColumnModel().getColumn(2).setMinWidth(90);
		tblProductList.getColumnModel().getColumn(2).setMaxWidth(90);
		tblProductList.getColumnModel().getColumn(3).setMinWidth(80);
		tblProductList.getColumnModel().getColumn(3).setMaxWidth(80);
		
		scrollPane.setViewportView(tblProductList);
		
		pnlProductList.setBackground(new Color(255, 255, 255));
		pnlProductList.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Product List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlProductList.setLayout(new MigLayout("", "[grow][][grow][]", "[][grow]"));
		pnlProductList.add(txtSearch, "cell 0 0,grow");
		pnlProductList.add(btnSearch, "cell 1 0,alignx left,growy");
		pnlProductList.add(btnExportList, "cell 3 0,alignx left,growy");
		pnlProductList.add(scrollPane, "cell 0 1 4 1,grow");
		
		//ELEMENTS IN THE ADD ITEM PANEL
		lblEnterItemName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtItemName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtItemName.setColumns(10);
		
		lblEnterBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setEditable(true);
		txtBuyingPrice.setColumns(10);

		lblPesos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblEnterSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setEditable(true);
		txtSellingPrice.setColumns(10);
		
		lblPesos_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		btnAdd.setMinimumSize(new Dimension(100, 35));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAdd.addActionListener(this);
		
		pnlAddItem.setBackground(new Color(255, 255, 255));
		pnlAddItem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add New Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAddItem.setLayout(new MigLayout("", "[grow][70px:n,grow]", "[][][][][][][]"));	
		pnlAddItem.add(lblEnterItemName, "cell 0 0");
		pnlAddItem.add(txtItemName, "cell 1 0,grow");
		pnlAddItem.add(lblEnterBuyingPrice, "cell 0 1");
		pnlAddItem.add(txtBuyingPrice, "flowx,cell 1 1,growy");
		pnlAddItem.add(lblPesos, "cell 1 1");
		pnlAddItem.add(lblEnterSellingPrice, "cell 0 2");
		pnlAddItem.add(txtSellingPrice, "flowx,cell 1 2,growy");
		pnlAddItem.add(lblPesos_1, "cell 1 2");
		
		pnlAddItem.add(verticalStrut, "cell 1 3");
		lblAddFeedback.setForeground(new Color(0, 128, 128));
		lblAddFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pnlAddItem.add(lblAddFeedback, "cell 0 5 2 1");
		pnlAddItem.add(btnAdd, "cell 0 6 2 1,alignx right,growy");
		
		//ELEMENTS IN THE DISCONTINUE PANEL
		lblDiscontinueHelp.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		pnlDiscontinueItem.setBackground(new Color(255, 255, 255));
		pnlDiscontinueItem.setBorder(new TitledBorder(null, "Discontinue Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDiscontinueItem.setLayout(new MigLayout("", "[][70:n,grow,right]", "[35px:n][][][]"));
		pnlDiscontinueItem.add(lblDiscontinueHelp, "cell 0 0");
		
		//ELEMENTS IN PRODUCT LIST PANEL
		add(pnlProductList, "cell 0 0 1 2,grow");
		add(pnlAddItem, "cell 1 0,growx");
		add(pnlDiscontinueItem, "cell 1 1,growx,aligny top");
		
		pnlDiscontinueItem.add(verticalStrut_1, "cell 1 1");
		lblDiscontinueFeedback.setForeground(new Color(0, 128, 128));
		lblDiscontinueFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pnlDiscontinueItem.add(lblDiscontinueFeedback, "cell 0 2");
		
		btnDiscontinue.setMinimumSize(new Dimension(100, 35));
		btnDiscontinue.setBackground(new Color(255, 0, 0));
		btnDiscontinue.setForeground(Color.WHITE);
		btnDiscontinue.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDiscontinue.addActionListener(this);
		pnlDiscontinueItem.add(btnDiscontinue, "cell 0 3 2 1,alignx right");
	}


	public int getProductID()
	{
		return productManage.getProductID(tblProductList.getSelectedRow());
	}
	
	public void addItem()
	{
		if(Double.parseDouble(txtSellingPrice.getText()) < 0 ||Double.parseDouble(txtBuyingPrice.getText()) < 0)
		{
			lblAddFeedback.setText("Input cannot be added!");
		}
		else
		{
		productManage.addProduct(txtItemName.getText(), Double.parseDouble(txtSellingPrice.getText()));
		batchManage.addNewBatch(Double.parseDouble(txtBuyingPrice.getText()));
		lblAddFeedback.setText(txtItemName.getText() +" successfully added to the inventory!");
		tblProductList.setModel(productManage.viewProductsWithPrice());
		}
	}
	
	public void discontinueProduct()
	{
		productManage.discontinueProduct(getProductID());
		lblDiscontinueFeedback.setText("Item discontinued!");
		tblProductList.setModel(productManage.viewProductsWithPrice());
	}
	
	public void updateTable()
	{
		if(txtSearch.getText().toString().equals(""))
			tblProductList.setModel(productManage.viewProductsWithPrice());
			else
		tblProductList.setModel(productManage.searchProductWithPrice(txtSearch.getText()));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch))
		{
			updateTable();
		}
		if(e.getSource().equals(btnDiscontinue))
		{
			discontinueProduct();
		}
		if(e.getSource().equals(btnAdd))
		{
			addItem();
		}
		}
=======
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class ProductListPanel extends JPanel implements ActionListener {
	//PRODUCT SEARCH PANEL
	private JLabel lblProductListDate = new JLabel("Product List as of ");
	private JTextField txtProductSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	
	//TABLE LIST PANEL
	JPanel pnlProductListTable = new JPanel();
	private JTable tblProductListTable;
	
	//BOTTOM PANEL
	private JLabel lblNoItemsShown = new JLabel("Items Showed");
	private final JPanel pnlProducSearchPanel = new JPanel();
	private final JPanel panel = new JPanel();
	
	public ProductListPanel() {
		setLayout(new MigLayout("", "[grow][grow][][][][][][][][][grow]", "[grow][grow][]"));
		//PRODUCT SEARCH PANEL
		add(pnlProducSearchPanel, "cell 0 0 11 1,alignx left,aligny top");
		pnlProducSearchPanel.setLayout(new MigLayout("", "[100px:n][][][][][][][][][][][][][][]", "[26px]"));
		lblProductListDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlProducSearchPanel.add(lblProductListDate, "cell 0 0 6 1,alignx left,aligny top");
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pnlProducSearchPanel.add(txtProductSearch, "cell 13 0");
		txtProductSearch.setColumns(10);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlProducSearchPanel.add(btnSearch, "cell 14 0");
		
		btnSearch.setBackground(Color.YELLOW);
		add(pnlProductListTable, "cell 0 1 11 1,grow");
		
		
		//TABLE PANEL
		tblProductListTable = new JTable();
		tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlProductListTable.add(tblProductListTable);
		
		
		//BOTTOM PANEL
		add(panel, "flowx,cell 0 2 11 1");
		lblNoItemsShown.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panel.add(lblNoItemsShown);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

>>>>>>> 7ffb40cc0607a6d13e58ce970d714e96f8a89bbf
}
