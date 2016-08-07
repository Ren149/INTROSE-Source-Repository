package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class RestockPanel extends JFrame implements ActionListener{
	//UI ELEMENTS
	private JPanel panel = new JPanel();
	private JLabel lblRestockProductTitle = new JLabel("Restock Product");
	private JLabel lblProductName = new JLabel("Product Name:");
	private JLabel lblBuyingPrice = new JLabel("Buying Price:");
	private JLabel lblBuyingPriceError = new JLabel("");
	private JLabel lblSellingPrice = new JLabel("Selling Price:");
	private JLabel lblSellingPriceError = new JLabel("");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblQuantityError = new JLabel("");
	private JLabel lblExpiryDate = new JLabel("Expiry Date:");
	private JLabel lblExpiryDateError = new JLabel("");
	private JTextField txtProductName = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTextField txtSellingPrice = new JTextField();
	private JComboBox cboExpiryYear = new JComboBox();
	private JComboBox cboExpiryMonth = new JComboBox();
	private JButton btnRestock = new JButton("Restock");
	private Component verticalStrut = Box.createVerticalStrut(20);
	private JCheckBox chckbxUpdateBuyingPrice = new JCheckBox("Update");
	private JCheckBox chckbxUpdateSellingPrice = new JCheckBox("Update");
	private Component verticalGlue = Box.createVerticalGlue();
	
	//MANAGERS
	private ProductManager pm;
	private BatchManager bm;
	
	//OTHER VARIABLES
	private ArrayList<String> yearList = new ArrayList<String>();
	private int productID;
	
	public RestockPanel(int productID){
		 pm = new ProductManager();
		 bm = new BatchManager();
		this.productID = productID;
		
		setTitle("Restock Product");
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		getContentPane().add(panel, "cell 0 0,grow");
		
		lblRestockProductTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		lblProductName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductName.setEditable(false);
		txtProductName.setColumns(20);
		txtProductName.setText(pm.getProductName(productID));
		txtProductName.setFocusable(false);
		
		lblBuyingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtBuyingPrice = new JTextField();
		txtBuyingPrice.setEditable(false);
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setColumns(10);
		txtBuyingPrice.setText(Float.toString(bm.getLatestBuyingPrice(productID)));
		txtBuyingPrice.setFocusable(false);
		
		chckbxUpdateBuyingPrice.setBackground(Color.WHITE);
		chckbxUpdateBuyingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		chckbxUpdateBuyingPrice.addActionListener(this);
		
		lblBuyingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblSellingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		txtSellingPrice.setEditable(false);

		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setColumns(10);
		txtSellingPrice.setText(Float.toString(pm.getSellingPrice(productID)));
		txtSellingPrice.setFocusable(false);
		
		chckbxUpdateSellingPrice.setBackground(Color.WHITE);
		chckbxUpdateSellingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		chckbxUpdateSellingPrice.addActionListener(this);
		
		lblSellingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblQuantity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
		
		lblQuantityError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblExpiryDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		cboExpiryMonth.setBackground(new Color(255, 255, 255));
		
		cboExpiryMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cboExpiryMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		yearList.add(String.valueOf(LocalDate.now().getYear() + 2));
		yearList.add(String.valueOf(LocalDate.now().getYear() + 3));
		yearList.add(String.valueOf(LocalDate.now().getYear() + 4));
		yearList.add(String.valueOf(LocalDate.now().getYear() + 5));
		
		cboExpiryYear.setBackground(new Color(255, 255, 255));
		cboExpiryYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryYear.setModel(new DefaultComboBoxModel(new String[] {"Year", yearList.get(0), yearList.get(1), yearList.get(2), yearList.get(3)}));
		
		lblExpiryDateError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
	
		btnRestock.setForeground(Color.WHITE);
		btnRestock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnRestock.setBackground(new Color(51, 204, 0));
		btnRestock.addActionListener(this);
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		panel.add(lblRestockProductTitle, "cell 0 0 2 1");
		panel.add(verticalGlue, "cell 0 1");
		panel.add(lblProductName, "cell 0 2,alignx right,growy");
		panel.add(txtProductName, "cell 1 2,growx,aligny center");
		panel.add(lblBuyingPrice, "cell 0 4,alignx right,growy");
		panel.add(txtBuyingPrice, "flowx,cell 1 4,aligny center");
		panel.add(chckbxUpdateBuyingPrice, "cell 1 4");
		panel.add(lblBuyingPriceError, "cell 1 5,aligny top");
		panel.add(lblSellingPrice, "cell 0 6,alignx right,growy");
		panel.add(txtSellingPrice, "flowx,cell 1 6,aligny center");
		panel.add(chckbxUpdateSellingPrice, "cell 1 6");
		panel.add(lblSellingPriceError, "cell 1 7,aligny top");
		panel.add(lblQuantity, "cell 0 8,alignx trailing,growy");
		panel.add(txtQuantity, "cell 1 8,alignx left,aligny center");
		panel.add(lblQuantityError, "cell 1 9,aligny top");
		panel.add(lblExpiryDate, "cell 0 10,alignx trailing,growy");
		panel.add(cboExpiryMonth, "flowx,cell 1 10,alignx left,aligny center");
		panel.add(cboExpiryYear, "cell 1 10,aligny center");
		panel.add(lblExpiryDateError, "cell 1 11,aligny top");
		panel.add(verticalStrut, "cell 0 12");
		panel.add(btnRestock, "cell 0 13 2 1,alignx right,growy");
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtQuantity, cboExpiryMonth, cboExpiryYear, btnRestock, chckbxUpdateBuyingPrice, txtBuyingPrice, chckbxUpdateSellingPrice, txtSellingPrice}));

		try {
			pm.getDBConnection().getConnection().close();
			bm.getDBConnection().getConnection().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		getRootPane().setDefaultButton(btnRestock);
		txtQuantity.requestFocusInWindow();
	}
	
	private boolean allValidInputs() {
		boolean valid = true;
		
		if(chckbxUpdateBuyingPrice.isSelected()) {
			if(StringUtils.isEmptyOrWhitespaceOnly(txtBuyingPrice.getText())) {
				lblBuyingPriceError.setText("Product must have a buying price.");
				txtBuyingPrice.setBackground(Color.YELLOW);
				valid = false;
			}
			else {
				try {
					if(Float.parseFloat(txtBuyingPrice.getText()) <= 0){
						lblBuyingPriceError.setText("Buying price must be greater than 0.");
						txtBuyingPrice.setBackground(Color.YELLOW);
						valid = false;
					}
					else {
						lblBuyingPriceError.setText("");
						txtBuyingPrice.setBackground(Color.WHITE);
					}
				} catch(NumberFormatException e) {
					lblBuyingPriceError.setText("Buying price must be numeric.");
					txtBuyingPrice.setBackground(Color.YELLOW);
					valid = false;
				}
			}
		}
		else {
			lblBuyingPriceError.setText("");
			txtBuyingPrice.setBackground(new Color(238, 238, 238));
		}
		
		if(chckbxUpdateSellingPrice.isSelected()) {
			if(StringUtils.isEmptyOrWhitespaceOnly(txtSellingPrice.getText())) {
				lblSellingPriceError.setText("Product must have a selling price.");
				txtSellingPrice.setBackground(Color.YELLOW);
				valid = false;
			}
			else {
				try {
					if(Float.parseFloat(txtSellingPrice.getText()) <= 0){
						lblSellingPriceError.setText("Selling price must be greater than 0.");
						txtSellingPrice.setBackground(Color.YELLOW);
						valid = false;
					}
					else {
						lblSellingPriceError.setText("");
						txtSellingPrice.setBackground(Color.WHITE);
					}
				} catch(NumberFormatException e) {
					lblSellingPriceError.setText("Selling price must be numeric.");
					txtSellingPrice.setBackground(Color.YELLOW);
					valid = false;
				}
			}
		}
		else {
			lblSellingPriceError.setText("");
			txtSellingPrice.setBackground(new Color(238, 238, 238));
		}
		
		if(chckbxUpdateBuyingPrice.isSelected() || chckbxUpdateSellingPrice.isSelected()) {
			try {
				if(getBuyingPrice() >= getSellingPrice()) {
					lblSellingPriceError.setText("Selling price must be greater than buying price.");
					txtSellingPrice.setBackground(Color.YELLOW);
					valid = false;
				}
				else {
					lblSellingPriceError.setText("");
					txtSellingPrice.setBackground(Color.WHITE);
				}				
			} catch(NumberFormatException e) {
				
			}
		}
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtQuantity.getText())) {
			lblQuantityError.setText("Product must have a quantity.");
			txtQuantity.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			try {
				if(getQuantity() <= 0){
					lblQuantityError.setText("Quantity must be greater than 0.");
					txtQuantity.setBackground(Color.YELLOW);
					valid = false;
				}
				else {
					lblQuantityError.setText("");
					txtQuantity.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblQuantityError.setText("Quantity must be a positive whole number.");
				txtQuantity.setBackground(Color.YELLOW);
				valid = false;
			}
		}
		
		if(cboExpiryMonth.getSelectedIndex() == 0) {
			cboExpiryMonth.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			cboExpiryMonth.setBackground(Color.WHITE);
		}
		
		if(cboExpiryYear.getSelectedIndex() == 0) {
			cboExpiryYear.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			cboExpiryYear.setBackground(Color.WHITE);
		}
		
		if(getExpiryMonth() == 0 || cboExpiryYear.getSelectedIndex() == 0) {
			lblExpiryDateError.setText("Product must both have a month and year of expiry.");
		}
		else {
			lblExpiryDateError.setText("");
		}

		return valid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(chckbxUpdateBuyingPrice)) {
			if(chckbxUpdateBuyingPrice.isSelected()) {
				txtBuyingPrice.setEditable(true);
				txtBuyingPrice.setBackground(Color.WHITE);
				txtBuyingPrice.setFocusable(true);
			}
			else {
				txtBuyingPrice.setEditable(false);
				txtBuyingPrice.setText(Float.toString(bm.getLatestBuyingPrice(productID)));
				txtBuyingPrice.setBackground(new Color(238, 238, 238));
				txtBuyingPrice.setFocusable(false);
				lblBuyingPriceError.setText("");
			}
		}
		else if(e.getSource().equals(chckbxUpdateSellingPrice)) {
			if(chckbxUpdateSellingPrice.isSelected()) {
				txtSellingPrice.setEditable(true);
				txtSellingPrice.setBackground(Color.WHITE);
				txtSellingPrice.setFocusable(true);
			}
			else {
				txtSellingPrice.setEditable(false);
				txtSellingPrice.setText(Float.toString(pm.getSellingPrice(productID)));
				txtSellingPrice.setBackground(new Color(238, 238, 238));
				txtSellingPrice.setFocusable(false);
				lblSellingPriceError.setText("");
			}
		}
		else if(e.getSource().equals(btnRestock)) {
			if(allValidInputs()) {

				 pm = new ProductManager();
				 bm = new BatchManager();
				 
				int quantity = getQuantity();
				float buyingPrice = Float.parseFloat(txtBuyingPrice.getText());
				int expiryMonth = getExpiryMonth();
				int expiryYear = getExpiryYear();
				
				bm.restockBatch(productID, quantity, buyingPrice, expiryMonth, expiryYear);
				try {
					pm.getDBConnection().getConnection().close();
					bm.getDBConnection().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		}
		
		pack();
	}
	
	private float getBuyingPrice() {
		return Float.parseFloat(txtBuyingPrice.getText());
	}
	
	private float getSellingPrice() {
		return Float.parseFloat(txtSellingPrice.getText());
	}
	
	private int getQuantity() {
		return Integer.parseInt(txtQuantity.getText());
	}
	
	private int getExpiryMonth() {
		return cboExpiryMonth.getSelectedIndex();
	}
	
	private int getExpiryYear() {
		return Integer.parseInt(cboExpiryYear.getSelectedItem().toString());
	}
}