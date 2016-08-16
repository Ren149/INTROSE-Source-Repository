//MILESTONE
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends JPanel implements ActionListener, ListSelectionListener, MouseListener, KeyListener, FocusListener {
	//UI ELEMENTS
	private JLabel lblProductListTitle = new JLabel("Product List");
	private JLabel lblItemCount = new JLabel("");
	private JLabel lblBatchesTitle = new JLabel("Batches");
	private JTextField txtSearch = new JTextField();
	private JTable tblProductList = new JTable() {
		public boolean isCellEditable(int row, int column) {                
            return false;               
		}
	};
	private JTable tblBatchList = new JTable();
	private JScrollPane scrProductList = new JScrollPane();
	private JScrollPane scrBatchList = new JScrollPane();
	private JButton btnRestock = new JButton("Restock");
	private JButton btnAddProduct = new JButton("Add Product");
	private JButton btnDiscontinue = new JButton("Discontinue");
	
	//MANAGERS
	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();
	private final JLabel lblSearch = new JLabel("Search: ");
	private final JSeparator separator = new JSeparator();
	private final Box horizontalBox = Box.createHorizontalBox();
	private final Box horizontalBox_1 = Box.createHorizontalBox();
	
	public ProductListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[150px:150px:150px][150px:150px:150px][20px:20px][grow]", "[][][][grow][]"));
		
		lblProductListTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		lblBatchesTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		
		lblSearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		horizontalBox.add(lblSearch);
		horizontalBox.add(txtSearch);
		
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(20);
		txtSearch.addKeyListener(this);
		txtSearch.addFocusListener(this);
		
		tblBatchList.setRowSelectionAllowed(false);
		tblBatchList.setEnabled(false);
		tblBatchList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblBatchList.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN, 11));
		tblBatchList.addMouseListener(this);

		scrBatchList.setEnabled(false);
		scrBatchList.setViewportView(tblBatchList);
		
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN, 11));
		tblProductList.getSelectionModel().addListSelectionListener(this);
		tblProductList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProductList.addKeyListener(this);
		tblProductList.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

		scrProductList.setViewportView(tblProductList);

		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
	
		btnDiscontinue.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnDiscontinue.setBackground(Color.WHITE);
		btnDiscontinue.setForeground(Color.BLACK);
		btnDiscontinue.addActionListener(this);
		btnDiscontinue.setEnabled(false);
		
		horizontalBox_1.add(btnDiscontinue);
		horizontalBox_1.add(btnRestock);
		
		btnRestock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnRestock.setBackground(Color.WHITE);
		btnRestock.setForeground(Color.BLACK);
		btnRestock.addActionListener(this);
		btnRestock.setEnabled(false);
		
		btnAddProduct.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAddProduct.setForeground(Color.WHITE);
		btnAddProduct.setBackground(new Color(51, 204, 0));
		btnAddProduct.addActionListener(this);
		
		add(lblBatchesTitle, "cell 3 0,aligny bottom");
		add(lblProductListTitle, "cell 0 0 2 1");
		add(horizontalBox, "flowx,cell 0 1 2 1,grow");
		add(scrBatchList, "cell 3 1 1 4,grow");
		add(lblItemCount, "cell 0 2");
		add(scrProductList, "cell 0 3 2 1,grow");
		add(separator, "cell 2 0 1 5,alignx center,growy");
		add(horizontalBox_1, "flowx,cell 0 4,alignx left");
		add(btnAddProduct, "cell 1 4,alignx right");

		addMouseListener(this);

		loadProductList();
		loadBatchList();
		
		txtSearch.requestFocusInWindow();
	}

	private void loadBatchList() {
		ArrayList<Integer> id;
		DefaultTableModel batchListTableModel = new DefaultTableModel();
		batchListTableModel.setColumnIdentifiers(new String[] {"Lot No.", "Entry Date", "Quantity", "Buying Price", "Expiry Date"});
		
		if(tblProductList.getSelectedRow() != -1) {
			id = bm.getBatchIDList(pm.getProductID(tblProductList.getSelectedRow(), txtSearch.getText().trim()));
		}
		else {
			id = new ArrayList<Integer>();
		}
		
		for(int i : id) {
			String batchID = i + "";
			Date entryDateTemp = bm.getEntryDate(i);
			String entryDate = "";

			switch(entryDateTemp.getMonth()) {
				case 1: entryDate += "Jan "; break;
				case 2: entryDate += "Feb "; break;
				case 3: entryDate += "Mar "; break;
				case 4: entryDate += "Apr "; break;
				case 5: entryDate += "May "; break;
				case 6: entryDate += "Jun "; break;
				case 7: entryDate += "Jul "; break;
				case 8: entryDate += "Aug "; break;
				case 9: entryDate += "Sep "; break;
				case 10: entryDate += "Oct "; break;
				case 11: entryDate += "Nov "; break;
				case 12: entryDate += "Dec "; break;
			}
			
			entryDate += String.format("%02d", entryDateTemp.getDate()) + ", " + (entryDateTemp.getYear() + 1900);
			
			String lotNumber = bm.getLotNumber(i);
			String quantity = bm.getEachBatchQuantity(i) + "";
			String buyingPrice = "P" + String.format("%.2f", bm.getBuyingPrice(i));
			String expiryDate = "";
			
			switch(bm.getExpiryMonth(i)) {
				case 1: expiryDate += "Jan "; break;
				case 2: expiryDate += "Feb "; break;
				case 3: expiryDate += "Mar "; break;
				case 4: expiryDate += "Apr "; break;
				case 5: expiryDate += "May "; break;
				case 6: expiryDate += "Jun "; break;
				case 7: expiryDate += "Jul "; break;
				case 8: expiryDate += "Aug "; break;
				case 9: expiryDate += "Sep "; break;
				case 10: expiryDate += "Oct "; break;
				case 11: expiryDate += "Nov "; break;
				case 12: expiryDate += "Dec "; break;
			}
			
			expiryDate += bm.getExpiryYear(i);
			
			batchListTableModel.addRow(new Object[] {lotNumber, entryDate, quantity, buyingPrice, expiryDate});
		}
		
		tblBatchList.setModel(batchListTableModel);
		tblBatchList.getColumnModel().getColumn(0).setMinWidth(55);
		tblBatchList.getColumnModel().getColumn(0).setMaxWidth(55);
		tblBatchList.getColumnModel().getColumn(2).setMinWidth(55);
		tblBatchList.getColumnModel().getColumn(2).setMaxWidth(55);
		
		DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
		rendererRight.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tblBatchList.getColumnModel().getColumn(0).setCellRenderer(rendererRight);
		tblBatchList.getColumnModel().getColumn(1).setCellRenderer(rendererRight);
		tblBatchList.getColumnModel().getColumn(2).setCellRenderer(rendererRight);
		tblBatchList.getColumnModel().getColumn(3).setCellRenderer(rendererRight);
		tblBatchList.getColumnModel().getColumn(4).setCellRenderer(rendererRight);
	}
	
	private void loadProductList() {
		ArrayList<Integer> id;
		DefaultTableModel productListTableModel = new DefaultTableModel();
		productListTableModel.setColumnIdentifiers(new String[] {"Product Name", "Selling Price", "Quantity"});
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtSearch.getText())) {
			id = pm.getProductIDList();
		}
		else {
			id = pm.getProductIDList(txtSearch.getText().trim());
		}

		for(int i : id) {
			String productName = pm.getProductName(i);
			String sellingPrice = "P" + String.format("%.2f", pm.getSellingPrice(i));
			String quantity = bm.getTotalQuantity(i) + "";
			
			productListTableModel.addRow(new Object[] {productName, sellingPrice, quantity});
		}
		
		tblProductList.setModel(productListTableModel);
		tblProductList.getColumnModel().getColumn(1).setMinWidth(90);
		tblProductList.getColumnModel().getColumn(1).setMaxWidth(90);
		tblProductList.getColumnModel().getColumn(2).setMaxWidth(60);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tblProductList.getColumnModel().getColumn(1).setCellRenderer(renderer);
		tblProductList.getColumnModel().getColumn(2).setCellRenderer(renderer);
		
		int rowCount = tblProductList.getRowCount();
		
		switch(rowCount) {
			case 0 : lblItemCount.setText("No items displayed."); break;
			case 1 : lblItemCount.setText("Displaying " + rowCount + " product"); break;
			default: lblItemCount.setText("Displaying " + rowCount + " products"); break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAddProduct)) {
			AddProductPanel frmAddProduct = new AddProductPanel();
			frmAddProduct.addWindowListener(new WindowAdapter(){
			    public void windowClosed(WindowEvent e) {
					loadProductList();
			    }
			});
		}
		else if(e.getSource().equals(btnRestock)) {
			int productID = pm.getProductID(tblProductList.getSelectedRow(), txtSearch.getText());
			
			RestockPanel frmRestockProduct = new RestockPanel(productID);
			frmRestockProduct.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent e) {
					loadProductList();
					loadBatchList();
				}
			});
		}
		else if(e.getSource().equals(btnDiscontinue)) {
			int productID = pm.getProductID(tblProductList.getSelectedRow(), txtSearch.getText());
			
			DiscontinueDialog dd = new DiscontinueDialog(productID);
			dd.addWindowListener(new WindowAdapter(){
			    public void windowClosed(WindowEvent e) {
					loadProductList();
			    }
			});
			
			loadProductList();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource().equals(tblProductList.getSelectionModel())) {
			if(tblProductList.getSelectedRow() == -1) {
				btnRestock.setEnabled(false);
				btnDiscontinue.setEnabled(false);
				loadBatchList();
			}
			else {
				btnRestock.setEnabled(true);
				btnDiscontinue.setEnabled(true);
				loadBatchList();
			}
		}
	}
	
	public void update() {
		loadProductList();
		loadBatchList();
		btnRestock.setEnabled(false);
		btnDiscontinue.setEnabled(false);
		txtSearch.grabFocus();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(this)) {
			if(e.getID() == MouseEvent.MOUSE_CLICKED) {
				tblProductList.clearSelection();
				tblProductList.transferFocusUpCycle();
				btnRestock.setEnabled(false);
				btnDiscontinue.setEnabled(false);
				loadBatchList();
			} 
		}
		else if(e.getSource().equals(scrBatchList)) {
			if(e.getID() == MouseEvent.MOUSE_CLICKED) {
				tblProductList.clearSelection();
				tblProductList.transferFocusUpCycle();
				btnRestock.setEnabled(false);
				btnDiscontinue.setEnabled(false);
				loadBatchList();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource().equals(tblProductList)) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				btnRestock.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(txtSearch)) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				if(tblProductList.getRowCount() > 0) {
					tblProductList.requestFocusInWindow();
					tblProductList.setRowSelectionInterval(0, 0);
				}
			}
			else {
				loadProductList();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(txtSearch)) {
			tblProductList.clearSelection();
			btnRestock.setEnabled(false);
			btnDiscontinue.setEnabled(false);
			loadProductList();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {

	}
}