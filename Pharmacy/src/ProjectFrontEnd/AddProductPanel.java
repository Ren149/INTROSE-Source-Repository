//MILESTONE
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class AddProductPanel extends JFrame implements ActionListener {
	//UI ELEMENTS
	private JPanel panel = new JPanel();
	private JLabel lblTitle = new JLabel("Add Product");
	private JLabel lblProductName = new JLabel("Product Name:");
	private JLabel lblBuyingPrice = new JLabel("Buying Price:");
	private JLabel lblSellingPrice = new JLabel("Selling Price:");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblExpiryDate = new JLabel("Expiry Date:");
	private JLabel lblProductNameError = new JLabel("");
	private JLabel lblBuyingPriceError = new JLabel("");
	private JLabel lblSellingPriceError = new JLabel("");
	private JLabel lblQuantityError = new JLabel("");
	private JLabel lblExpiryDateError = new JLabel("");
	private JTextField txtProductName = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTextField txtSellingPrice = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JButton btnAdd = new JButton("Add");
	private Component verticalGlue = Box.createVerticalGlue();
	private Component verticalStrut = Box.createVerticalStrut(20);
	private JComboBox cboExpiryMonth = new JComboBox();
	private JComboBox cboExpiryYear = new JComboBox();
	
	//DIALOG
	private JOptionPane dialogProductAlreadyExists = new JOptionPane();
	private JOptionPane dialogProductAdded = new JOptionPane();
	private JOptionPane dialogRestockSuccessful = new JOptionPane();
	private JLabel lblProductAlreadyExists = new JLabel("<html>Product already exists in the database.<br>"
			+ "This will be treated as a restocking.</html>");
	private JLabel lblProductDiscontinuedRestock = new JLabel("<html>Product was previously discontinued.<br>"
			+ "Clicking OK will bring back the product to the Product List.</html>");
	private JLabel lblProductAdded = new JLabel();
	private JLabel lblRestockSuccessful = new JLabel();
	
	//OTHER VARIABLES
	private LocalDate currentDate = LocalDate.now();
	private ArrayList<String> yearList = new ArrayList<String>();
	
	//MANAGER INITIALIZERS
	private ProductManager pm;
	private BatchManager bm;
	private final JLabel lblLotNumber = new JLabel("Lot Number:");
	private final JTextField txtLotNumber = new JTextField();
	private final JLabel lblLotNumberError = new JLabel("");
	
	public AddProductPanel() {
		setTitle("Add Product");
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		getContentPane().add(panel, "cell 0 0,grow");
		
		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		lblProductName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		txtProductName.setBackground(Color.WHITE);
		txtProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductName.setColumns(20);
		
		lblProductNameError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblBuyingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		txtBuyingPrice.setBackground(Color.WHITE);
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setColumns(10);
		
		lblBuyingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblSellingPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtSellingPrice.setBackground(Color.WHITE);
		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setColumns(10);
		
		lblSellingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblQuantity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtQuantity.setBackground(Color.WHITE);
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
		
		lblQuantityError.setFont(new Font("Segoe UI", Font.ITALIC, 11));

		lblLotNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		txtLotNumber.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtLotNumber.setColumns(10);
		
		lblLotNumberError.setFont(new Font("Segoe UI", Font.ITALIC, 11));

		lblExpiryDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		cboExpiryMonth.setBackground(new Color(255, 255, 255));
		cboExpiryMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));

		yearList.add(String.valueOf(currentDate.getYear() + 2));
		yearList.add(String.valueOf(currentDate.getYear() + 3));
		yearList.add(String.valueOf(currentDate.getYear() + 4));
		yearList.add(String.valueOf(currentDate.getYear() + 5));

		cboExpiryYear.setBackground(new Color(255, 255, 255));
		cboExpiryYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryYear.setModel(new DefaultComboBoxModel(new String[] {"Year", yearList.get(0), yearList.get(1), yearList.get(2), yearList.get(3)}));
		
		lblExpiryDateError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(51, 204, 0));
		btnAdd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAdd.addActionListener(this);
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][]"));
		panel.add(lblTitle, "cell 0 0 2 1");
		panel.add(verticalGlue, "cell 0 1");
		panel.add(lblProductName, "cell 0 2,alignx right");
		panel.add(txtProductName, "cell 1 2,aligny center");
		panel.add(lblProductNameError, "cell 1 3,aligny top");
		panel.add(lblBuyingPrice, "cell 0 4,alignx right");
		panel.add(txtBuyingPrice, "cell 1 4,aligny center");
		panel.add(lblBuyingPriceError, "cell 1 5,alignx left,aligny top");
		panel.add(lblSellingPrice, "cell 0 6,alignx right");
		panel.add(txtSellingPrice, "cell 1 6,aligny center");
		panel.add(lblSellingPriceError, "cell 1 7,aligny top");
		panel.add(lblQuantity, "cell 0 8,alignx right");
		panel.add(txtQuantity, "cell 1 8,aligny center");
		panel.add(lblQuantityError, "cell 1 9,aligny top");
		panel.add(lblLotNumber, "cell 0 10,alignx trailing");
		panel.add(txtLotNumber, "cell 1 10,alignx left");
		panel.add(lblLotNumberError, "cell 1 11");
		panel.add(lblExpiryDate, "cell 0 12,alignx right");
		panel.add(cboExpiryMonth, "flowx,cell 1 12,alignx left,aligny center");
		panel.add(lblExpiryDateError, "cell 1 13,aligny top");
		panel.add(verticalStrut, "cell 0 14");
		panel.add(btnAdd, "cell 1 15,alignx right,aligny bottom");
		panel.add(cboExpiryYear, "cell 1 12,aligny center");
		
		getRootPane().setDefaultButton(btnAdd);

		//DIALOG
		lblProductAlreadyExists.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblProductDiscontinuedRestock.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblProductAdded.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblRestockSuccessful.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public boolean allValidInputs() {
		boolean valid = true;
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtProductName.getText())) {
			lblProductNameError.setText("Product must have a name.");
			txtProductName.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			lblProductNameError.setText("");
			txtProductName.setBackground(Color.WHITE);
		}
		
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
					txtBuyingPrice.setText("");
					valid = false;
				}
				else {
					lblBuyingPriceError.setText("");
					txtBuyingPrice.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblBuyingPriceError.setText("Buying price must be numeric.");
				txtBuyingPrice.setBackground(Color.YELLOW);
				txtBuyingPrice.setText("");
				valid = false;
			}
		}
		
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
					txtSellingPrice.setText("");
					valid = false;
				}
				else {
					lblSellingPriceError.setText("");
					txtSellingPrice.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblSellingPriceError.setText("Selling price must be numeric.");
				txtSellingPrice.setBackground(Color.YELLOW);
				txtSellingPrice.setText("");
				valid = false;
			}
		}
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(txtBuyingPrice.getText()) && !StringUtils.isEmptyOrWhitespaceOnly(txtSellingPrice.getText())){
			try {
				if(getBuyingPrice() >= getSellingPrice()) {
					lblSellingPriceError.setText("Selling price must be greater than buying price.");
					txtSellingPrice.setBackground(Color.YELLOW);
					txtSellingPrice.setText("");
					valid = false;
				}
				else {
					lblSellingPriceError.setText("");
					txtSellingPrice.setBackground(Color.WHITE);
				}				
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtQuantity.getText())) {
			lblQuantityError.setText("Product must have a quantity.");
			txtQuantity.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			try {
				if(Integer.parseInt(txtQuantity.getText()) <= 0){
					lblQuantityError.setText("Quantity must be greater than 0.");
					txtQuantity.setBackground(Color.YELLOW);
					txtQuantity.setText("");
					valid = false;
				}
				else {
					lblQuantityError.setText("");
					txtQuantity.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblQuantityError.setText("Quantity must be a positive whole number.");
				txtQuantity.setBackground(Color.YELLOW);
				txtQuantity.setText("");
				valid = false;
			}
		}
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtLotNumber.getText())) {
			lblLotNumberError.setText("Product must have a lot number.");
			txtLotNumber.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			lblLotNumberError.setText("");
			txtLotNumber.setBackground(Color.WHITE);
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
		
		if(cboExpiryMonth.getSelectedIndex() == 0 || cboExpiryYear.getSelectedIndex() == 0) {
			lblExpiryDateError.setText("Product must both have a month and year of expiry.");
		}
		else {
			lblExpiryDateError.setText("");
		}
		
		pack();
		return valid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(allValidInputs()) {
			pm = new ProductManager();
			bm = new BatchManager();
			
			if(pm.getProductID(getProductName()) == -1) { //if the product is not in record
				pm.addProduct(getProductName(), getSellingPrice());
				bm.addBatch(pm.getLatestProductID(), getQuantity(), getBuyingPrice(), getExpiryMonth(), getExpiryYear(), getLotNumber());
				
				dispose();
				
				lblProductAdded.setText(getProductName() + " added to the database.");
				JOptionPane.showMessageDialog(this,
						lblProductAdded,
						"Product Added",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else { //if the product is in the record
				int productID = pm.getProductID(getProductName());
				int n;
				
				if(pm.isDiscontinued(productID)) {
					n = JOptionPane.showConfirmDialog(this,
							lblProductDiscontinuedRestock,
							"Product Previously Discontinued",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE);
					
					pm.setDiscontinued(productID, false);
				}
				else {
					n = JOptionPane.showConfirmDialog(this,
						lblProductAlreadyExists,
						"Product Already Exists",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				}
				
				if(n == 0) {
					int quantity = Integer.parseInt(txtQuantity.getText());
					float buyingPrice = Float.parseFloat(txtBuyingPrice.getText());
					float sellingPrice = Float.parseFloat(txtSellingPrice.getText());
					int expiryMonth = cboExpiryMonth.getSelectedIndex();
					int expiryYear = Integer.parseInt(cboExpiryYear.getSelectedItem().toString());
					String lotNumber = txtLotNumber.getText();
					
					pm.setSellingPrice(productID, sellingPrice);
					bm.restockBatch(productID, quantity, buyingPrice, expiryMonth, expiryYear, lotNumber);
				
					dispose();
					
					lblRestockSuccessful.setText(getQuantity() + " units of " + getProductName() + " added.");
					JOptionPane.showMessageDialog(this, lblRestockSuccessful, "Restock Successful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private String getProductName() {
		return txtProductName.getText().toUpperCase();
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
	
	private String getLotNumber() {
		return txtLotNumber.getText();
	}
	
	private int getExpiryMonth() {
		return cboExpiryMonth.getSelectedIndex();
	}
	
	private int getExpiryYear() {
		return Integer.parseInt(cboExpiryYear.getSelectedItem().toString());
	}
}