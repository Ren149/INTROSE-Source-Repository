package ProjectFrontEnd;

import ProjectBackEnd.ProductManager;
import ProjectBackEnd.SaleManager;
import java.awt.*;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SalePanel extends JPanel implements ActionListener {
	
	private JPanel pnlAddToCart = new JPanel();
	private JTextField txtSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	private JTable tblSaleSearch = new JTable();
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JTextField txtQuantity = new JTextField();
	private JScrollPane scrollPaneSelectItem = new JScrollPane();
	private JTable tblCart = new JTable();
	private JButton btnAddToCart = new JButton("Add to Cart");
	private JLabel lblAddToCartFeedback = new JLabel(" ");
	private JPanel pnlCart = new JPanel();
	private JLabel lblHelpCart = new JLabel("Select and item and click remove to delete it from the cart");
	private JButton btnRemove = new JButton("Remove");
	private JScrollPane scrollPaneCart = new JScrollPane();
	private JLabel lblSalesDate = new JLabel("Sales Date:");
	private JLabel lblTotal = new JLabel("Total: ");
	private JLabel lblTotalValue = new JLabel("0");
	private JButton btnRecord = new JButton("Record");
	private JLabel lblFeedbackCart = new JLabel(" ");
	private JComboBox cboSalesDateMonth = new JComboBox();
	private JComboBox cboSalesDateDay = new JComboBox();
	private JComboBox cboSalesDateYear = new JComboBox();
	
        private ProductManager productManage = new ProductManager();
        private SaleManager saleManage = new SaleManager();
        private DefaultTableModel tm = new DefaultTableModel();
        private ArrayList<String> prodNameList = new ArrayList<String>();
        private ArrayList<Integer> prodQtyList = new ArrayList<Integer>();
        private int sum = 0;
        private int totalQty = 0;
        private int sellingprice;
            
        
	public SalePanel() {
                tm.setColumnIdentifiers(new String[] {"Item Name", "Quantity", "Selling Price", "Subtotal"});
            
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[300][grow]", "[grow]"));
		
		pnlAddToCart.setBackground(Color.WHITE);
		pnlAddToCart.setBorder(new TitledBorder(null, "Add to Cart", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAddToCart.setLayout(new MigLayout("", "[grow][]", "[][grow][][][]"));
		
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(10);
		
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.addActionListener(this);
		
		pnlAddToCart.add(txtSearch, "cell 0 0,grow");
		pnlAddToCart.add(btnSearch, "cell 1 0,alignx left");
		
				btnSearch.setForeground(new Color(255, 255, 255));
				btnSearch.setBackground(new Color(0, 204, 0));
				btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		tblSaleSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblSaleSearch.setModel(productManage.viewProducts());
		tblSaleSearch.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblSaleSearch.getColumnModel().getColumn(1).setMinWidth(65);
		tblSaleSearch.getColumnModel().getColumn(1).setMaxWidth(65);
		scrollPaneSelectItem.setViewportView(tblSaleSearch);
		
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnAddToCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAddToCart.setMinimumSize(new Dimension(100, 35));
		btnAddToCart.setBackground(new Color(51, 255, 153));
                btnAddToCart.addActionListener(this);
                
		
		lblAddToCartFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblAddToCartFeedback.setForeground(Color.RED);
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
                
		pnlAddToCart.add(scrollPaneSelectItem, "cell 0 1 2 1,grow");
		pnlAddToCart.add(lblQuantity, "cell 0 2,alignx right,growy");
		pnlAddToCart.add(btnAddToCart, "cell 0 3 2 1,alignx right");
		pnlAddToCart.add(lblAddToCartFeedback, "cell 0 4 2 1,alignx right");
		pnlAddToCart.add(txtQuantity, "cell 1 2,grow");
		
		pnlCart.setBackground(Color.WHITE);
		pnlCart.setBorder(new TitledBorder(null, "Cart", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCart.setLayout(new MigLayout("", "[][grow]", "[][grow][][][]"));
		
		lblHelpCart.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRemove.setBackground(Color.RED);
                btnRemove.addActionListener(this);
		
		tblCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblCart.setModel(tm);
		scrollPaneCart.setViewportView(tblCart);
		
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblTotalValue.setFont(new Font("Segoe UI", Font.BOLD, 11));
		
		lblSalesDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cboSalesDateMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cboSalesDateDay.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateYear.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020"}));
		cboSalesDateYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblFeedbackCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFeedbackCart.setForeground(new Color(0, 128, 0));
		
		btnRecord.setForeground(Color.WHITE);
		btnRecord.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRecord.setMinimumSize(new Dimension(100, 35));
		btnRecord.setBackground(new Color(0, 204, 0));
                btnRecord.addActionListener(this);
		
		pnlCart.add(lblHelpCart, "cell 0 0");
		pnlCart.add(btnRemove, "cell 1 0,alignx right");
		pnlCart.add(scrollPaneCart, "cell 0 1 2 1,grow");
		pnlCart.add(lblSalesDate, "flowx,cell 0 2");
		pnlCart.add(lblTotal, "flowx,cell 1 2,alignx right");
		pnlCart.add(lblTotalValue, "cell 1 2,alignx right");
		pnlCart.add(btnRecord, "flowy,cell 1 3,alignx right");
		pnlCart.add(lblFeedbackCart, "cell 0 4 2 1,alignx right");
		pnlCart.add(cboSalesDateMonth, "cell 0 2");
		pnlCart.add(cboSalesDateDay, "cell 0 2");
		pnlCart.add(cboSalesDateYear, "cell 0 2");
		
		add(pnlAddToCart, "cell 0 0,alignx left,growy");
		add(pnlCart, "cell 1 0,grow");
                
                txtQuantity.setEditable(false);
                btnAddToCart.setEnabled(true);
                selectTableField();
	}
        
        public void selectTableField()
	{
		tblSaleSearch.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				txtQuantity.setEditable(true);			}
                                
			
		});
                
	}
        
        
        public void searchTable()
	{
		if(txtSearch.getText().toString().equals(""))
			tblSaleSearch.setModel(productManage.viewProducts());
                else{
                tblSaleSearch.clearSelection();
		tblSaleSearch.setModel(productManage.searchProduct(txtSearch.getText()));
                }
	}
                
	public void actionPerformed(ActionEvent e) {
            sellingprice=0;
                if(e.getSource().equals(btnSearch)){
                   searchTable();
                }
		if(e.getSource().equals(btnAddToCart)){
                    lblAddToCartFeedback.setText(" ");
                   
                    if(txtQuantity.getText().length() >= 1){
                       
                        if(Integer.parseInt(txtQuantity.getText()) <= Integer.parseInt(String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 1)))){
                            
                            if(saleManage.getDuplicate(prodNameList, String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0))) == 999){
                                sellingprice = productManage.getSellingPrice(String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0)));
                                totalQty += Integer.parseInt(txtQuantity.getText());
                      
                                sum += saleManage.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice);
                                tm.addRow(new Object[]{tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0), 
                                               txtQuantity.getText(), 
                                               sellingprice, 
                                               saleManage.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice)
                                              });                        
                        
                                prodNameList.add(String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0)));
                                prodQtyList.add(Integer.parseInt(txtQuantity.getText()));
                            }
                            else{
                                int index;
                                index = saleManage.getDuplicate(prodNameList, String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0)));
                                System.out.println(prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText()));
                                if((prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText())) <= Integer.parseInt(String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 1)))){
                                sellingprice = productManage.getSellingPrice(String.valueOf(tblSaleSearch.getModel().getValueAt(tblSaleSearch.getSelectedRow(), 0)));
                                sum += saleManage.getSubtotal(Integer.parseInt(txtQuantity.getText()), sellingprice);
                                totalQty += Integer.parseInt(txtQuantity.getText());
                                int newQty = prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText());
                                tm.setValueAt(newQty,index ,1);
                                tm.setValueAt(saleManage.getSubtotal(newQty, sellingprice), index, 3);
                                prodQtyList.set(index, (prodQtyList.get(index)+Integer.parseInt(txtQuantity.getText())));
                                System.out.println("Valid Qty");
                                }
                                else lblAddToCartFeedback.setText("It exceeded the quantity of the product!");
                            }
                        }
                        else lblAddToCartFeedback.setText("Invalid Quantity!");
                    }
                    else lblAddToCartFeedback.setText("Please fill in all fields!");
                    
                tblSaleSearch.clearSelection();
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
                        StringBuilder preferredDate = new StringBuilder(); 
                        preferredDate.append(cboSalesDateYear.getSelectedItem().toString());
                        preferredDate.append("-");
                        preferredDate.append(String.valueOf(cboSalesDateMonth.getSelectedIndex()+1));
                        preferredDate.append("-");
                        preferredDate.append(String.valueOf(cboSalesDateDay.getSelectedIndex()+1));
                        saleManage.recordTransaction(totalQty, sum, preferredDate.toString());
                        saleManage.deductProductBatchQty(prodNameList, prodQtyList);
                        saleManage.recordLineItem(prodNameList);
                        prodNameList.clear();
                        prodQtyList.clear();
                        totalQty = 0;
                        sum = 0;
                        tm.setRowCount(0);
                        tblSaleSearch.setModel(productManage.viewProducts());
                        lblFeedbackCart.setText("Recorded Successfully!");
                    }
                }
                
            lblTotalValue.setText(String.valueOf(sum));
	}
}
