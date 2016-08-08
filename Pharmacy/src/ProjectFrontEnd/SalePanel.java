package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import ProjectBackEnd.SaleManager;
import net.miginfocom.swing.MigLayout;

public class SalePanel extends JPanel implements ActionListener, ListSelectionListener, KeyListener {
	private JTextField txtSearch = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JScrollPane scrollPaneItemSelection = new JScrollPane();
	private JScrollPane scrollPaneCart = new JScrollPane();
	private JTable tblItemSelection = new JTable();
	private JTable tblCart = new JTable();
	private JLabel lblSearch = new JLabel("Search: ");
	private JLabel lblItemCount = new JLabel("");
	private JLabel lblItemSelectionError = new JLabel("");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblSalesDate = new JLabel("Sales Date:");
	private JLabel lblTotal = new JLabel("Total:");
	private JLabel lblTotalValue = new JLabel("0.00");
	private JLabel lblAddToCartTitle = new JLabel("Item Selection");
	private JLabel lblCartTitle = new JLabel("Cart");
	private JButton btnRemove = new JButton("Remove");
	private JButton btnAddToCart = new JButton("Add to Cart");
	private JButton btnRecord = new JButton("Record");
	private JSpinner spinner = new JSpinner();
    private JSeparator separator_1 = new JSeparator();
    private Box horizontalBox = Box.createHorizontalBox();
    private Box horizontalBox_1 = Box.createHorizontalBox();
    private Component verticalStrut = Box.createVerticalStrut(20);
    
    //OTHER VARIABLES
    private DefaultTableModel tm = new DefaultTableModel();
    private ArrayList<String> prodNameList = new ArrayList<String>();
    private ArrayList<Integer> prodQtyList = new ArrayList<Integer>();
    private ArrayList<Integer> batchIDList = new ArrayList<Integer>();
    private float sum = 0;
    private float sellingprice = 0;
    private int totalQty = 0;
    
	//MANAGERS
    private ProductManager pm = new ProductManager();
    private SaleManager sm = new SaleManager();
    private BatchManager bm = new BatchManager();
    
	public SalePanel() {
		tm.setColumnIdentifiers(new String[] {"Item Name", "Quantity", "Selling Price", "Subtotal"});
            
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[150px:150px:150px][150px:150px:150px][20px:20px][grow][]", "[][][][grow][][][]"));
		
		lblAddToCartTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		lblCartTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		horizontalBox.add(lblSearch);
		horizontalBox.add(txtSearch);

		lblSearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(20);
		txtSearch.addKeyListener(this);

		tblItemSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblItemSelection.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblItemSelection.getSelectionModel().addListSelectionListener(this);
		tblItemSelection.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblItemSelection.addKeyListener(this);
		
		lblSalesDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		btnRemove.setToolTipText("This button deletes any selected item on the cart.");
		btnRemove.setForeground(new Color(0, 0, 0));
		btnRemove.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnRemove.setBackground(Color.WHITE);
		btnRemove.addActionListener(this);

		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		
		scrollPaneItemSelection.setViewportView(tblItemSelection);
		scrollPaneItemSelection.setFocusable(false);
		
		tblCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblCart.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblCart.setModel(tm);
		
		scrollPaneCart.setViewportView(tblCart);
		
		lblQuantity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.LIGHT_GRAY);
		
		lblTotal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		lblItemSelectionError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblItemSelectionError.setForeground(Color.RED);
		
		horizontalBox_1.add(btnAddToCart);
		
		btnAddToCart.setBackground(Color.WHITE);
		btnAddToCart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAddToCart.addActionListener(this);
		
		btnRecord.setForeground(Color.WHITE);
		btnRecord.setBackground(new Color(51, 204, 0));
		btnRecord.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnRecord.addActionListener(this);
		
		lblTotalValue.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		spinner.setModel(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		spinner.setSize(new Dimension(30, 15));
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(5);
		txtQuantity.setEditable(false);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtSearch, txtQuantity}));
        
		add(lblAddToCartTitle, "cell 0 0 2 1");
		add(lblCartTitle, "cell 3 0");
		add(horizontalBox, "flowx,cell 0 1 2 1,grow");
		add(lblSalesDate, "flowx,cell 3 1");
		add(btnRemove, "cell 4 0 1 2,aligny bottom");
		add(lblItemCount, "cell 0 2 2 1");
		add(scrollPaneItemSelection, "cell 0 3 2 1,grow");
		add(lblQuantity, "flowx,cell 1 4,alignx right");
		add(separator_1, "cell 2 0 1 7,alignx center,growy");
		add(lblTotal, "cell 3 4,alignx right");
		add(lblItemSelectionError, "cell 0 5 2 1,alignx right,aligny top");
		add(verticalStrut, "cell 3 5");
		add(horizontalBox_1, "flowx,cell 0 6 2 1,alignx right");
		add(btnRecord, "flowx,cell 3 6 2 1,alignx right");
		add(lblTotalValue, "cell 4 4,alignx right");
		add(spinner, "cell 3 1,growy");
		add(txtQuantity, "cell 1 4,alignx right");
		add(scrollPaneCart, "cell 3 2 2 2,grow");
		
		txtSearch.requestFocusInWindow();
	
		loadItemSelection();
	}

	public void loadCart() {
		DefaultTableModel cartTableModel = new DefaultTableModel();
		cartTableModel.setColumnIdentifiers(new String[] {"Product Name", "Quantity", "Selling Price", "Subtotal"});

		for(int i = 0; i < prodNameList.size(); i++){
			int productID = pm.getProductID(prodNameList.get(i));
			String productName = prodNameList.get(i);
			int quantity = prodQtyList.get(i);
			float sellingprice = pm.getSellingPrice(productID);
			float subtotal = prodQtyList.get(i) * sellingprice;
	
			cartTableModel.addRow(new Object[] {productName, quantity, sellingprice, subtotal});
		}
		
	}
	
	public void loadItemSelection() {
		ArrayList<Integer> id;
		DefaultTableModel itemSelectionTableModel = new DefaultTableModel();
		itemSelectionTableModel.setColumnIdentifiers(new String[] {"Product Name", "Selling Price", "Quantity"});
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtSearch.getText())) {
			id = pm.getProductIDList();
		}
		else {
			id = pm.getProductIDList(txtSearch.getText().trim());
		}

		for(int i : id) {
			String productName = pm.getProductName(i);
			String sellingPrice = Float.toString(pm.getSellingPrice(i));
			String quantity = bm.getTotalQuantity(i) + "";
			
			itemSelectionTableModel.addRow(new Object[] {productName, sellingPrice, quantity});
		}
		
		tblItemSelection.setModel(itemSelectionTableModel);
		tblItemSelection.getColumnModel().getColumn(1).setMinWidth(90);
		tblItemSelection.getColumnModel().getColumn(1).setMaxWidth(90);
		tblItemSelection.getColumnModel().getColumn(2).setMaxWidth(60);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tblItemSelection.getColumnModel().getColumn(1).setCellRenderer(renderer);
		tblItemSelection.getColumnModel().getColumn(2).setCellRenderer(renderer);
		
		int rowCount = tblItemSelection.getRowCount();
		
		switch(rowCount) {
			case 0 : lblItemCount.setText("No items displayed."); break;
			case 1 : lblItemCount.setText("Displaying " + rowCount + " product"); break;
			default: lblItemCount.setText("Displaying " + rowCount + " products"); break;
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		txtQuantity.setEditable(true);
	}
    
	private boolean allValidInputs() {
		boolean valid = true;
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtQuantity.getText())) {
			lblItemSelectionError.setText("Product must have a quantity.");
			txtQuantity.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			String prodName = String.valueOf(tblItemSelection.getValueAt(tblItemSelection.getSelectedRow(), 0));
			int prodQtyLeft = Integer.parseInt(String.valueOf(tblItemSelection.getValueAt(tblItemSelection.getSelectedRow(), 2)));
			
			
			try {
				if(Integer.parseInt(txtQuantity.getText()) <= 0){
					lblItemSelectionError.setText("Quantity must be greater than 0.");
					txtQuantity.setBackground(Color.YELLOW);
					valid = false;
				}
				else if(Integer.parseInt(txtQuantity.getText()) > Integer.parseInt(String.valueOf(tblItemSelection.getValueAt(tblItemSelection.getSelectedRow(), 2)))) {
					lblItemSelectionError.setText("Insufficient stock.");
					txtQuantity.setBackground(Color.YELLOW);
					valid = false;
				}
				else if(prodNameList.indexOf(prodName) != -1){
					if(prodQtyList.get(prodNameList.indexOf(prodName)) + Integer.parseInt(txtQuantity.getText()) > prodQtyLeft){
						lblItemSelectionError.setText("Insufficient stock1.");
						txtQuantity.setBackground(Color.YELLOW);
						valid = false;
					}
				}
				else {
					lblItemSelectionError.setText("");
					txtQuantity.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblItemSelectionError.setText("Quantity must be a positive whole number.");
				txtQuantity.setBackground(Color.YELLOW);
				valid = false;
			}
		}
		
		return valid;
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnAddToCart)){
        	
            
            
            if(allValidInputs() ) {
            	
            
                    if(prodNameList.contains(String.valueOf(tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0)))){
                    	int index;
                        index = prodNameList.indexOf(String.valueOf(tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0)));
                        System.out.println(prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText()));
                        
                        sellingprice = pm.getSellingPrice(String.valueOf(tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0)));
                        sum += sm.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice);
                        totalQty += Integer.parseInt(txtQuantity.getText());
                        int newQty = prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText());
                        tm.setValueAt(newQty,index ,1);
                        tm.setValueAt(sm.getSubtotal(newQty, sellingprice), index, 3);
                        prodQtyList.set(index, (prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText())));
                        System.out.println("Valid Qty");
                    }
                    else{

                    	sellingprice = pm.getSellingPrice(String.valueOf(tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0)));
                        totalQty += Integer.parseInt(txtQuantity.getText());
              
                        sum += sm.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice);
                        tm.addRow(new Object[]{tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0), 
                                       txtQuantity.getText(), 
                                       sellingprice, 
                                       sm.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice)
                                      });
                
                        prodNameList.add(String.valueOf(tblItemSelection.getModel().getValueAt(tblItemSelection.getSelectedRow(), 0)));
                        prodQtyList.add(Integer.parseInt(txtQuantity.getText()));
                        
                        
                    }
            }    
            
	        tblItemSelection.clearSelection();
	        txtQuantity.setText("");
	        txtQuantity.setEditable(false);
	        btnAddToCart.setEnabled(true);
        }
		
        if(e.getSource().equals(btnRemove)){
            if(tblCart.getSelectedRow() != -1){
                sum -= Integer.parseInt(String.valueOf(tblCart.getModel().getValueAt(tblCart.getSelectedRow(), 3)));
                totalQty -= Integer.parseInt(String.valueOf(tblCart.getModel().getValueAt(tblCart.getSelectedRow(), 1)));
                prodNameList.remove(tblCart.getSelectedRow());
                prodQtyList.remove(tblCart.getSelectedRow());
                tm.removeRow(tblCart.getSelectedRow());
            }
            
            tblCart.clearSelection();
        }
        if(e.getSource().equals(btnRecord)){
            if(tblCart.getRowCount() != 0){
            	int prodID;
            	for(int i = 0; i < prodNameList.size(); i++){
            		prodID = pm.getProductID(prodNameList.get(i));
            		batchIDList = bm.getBatchIDofProductList(prodID);
            		
            		for(int j = 0; j < batchIDList.size(); j++){
            			int batchQty = bm.getEachBatchQuantity(batchIDList.get(j));
            			if(prodQtyList.get(i) > batchQty){
            				bm.changeBatchQtyToZero(batchIDList.get(j));
            				prodQtyList.set(i, batchQty);
            			}
            			else{
            				int difference = batchQty - prodQtyList.get(i);
            				bm.subtractBatchQty(batchIDList.get(j), difference);
            			}
            		}
            	}
                
                
                
                
                /*
                preferredDate.append(cboSalesDateYear.getSelectedItem().toString());
                preferredDate.append("-");
                preferredDate.append(String.valueOf(cboSalesDateMonth.getSelectedIndex()+1));
                preferredDate.append("-");
                preferredDate.append(String.valueOf(cboSalesDateDay.getSelectedIndex()+1));
                */
                String preferreddate = "12-12-2012";
                sm.recordTransaction(sum, preferreddate);
               
                
                //sm.recordLineItem(prodNameList);
                prodNameList.clear();
                prodQtyList.clear();
                totalQty = 0;
                sum = 0;
                tm.setRowCount(0);
                //tblSaleSearch.setModel(pm.viewProducts());
            }
           
        }
        
        lblTotalValue.setText(String.valueOf(sum));
	}
	
	public void update() {
		loadItemSelection();
		txtSearch.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource().equals(tblItemSelection)) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				txtQuantity.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(txtSearch)) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				loadItemSelection();
				
				if(tblItemSelection.getRowCount() > 0) {
					tblItemSelection.requestFocusInWindow();
					tblItemSelection.setRowSelectionInterval(0, 0);
				}
			}
			else {
				loadItemSelection();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
