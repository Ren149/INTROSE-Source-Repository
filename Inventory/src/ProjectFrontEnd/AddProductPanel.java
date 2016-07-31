package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class AddProductPanel extends JFrame implements ActionListener{
	
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
	private Component verticalStrut = Box.createVerticalStrut(20);
	private JComboBox cboExpiryMonth = new JComboBox();
	private JComboBox cboExpiryYear = new JComboBox();
	
	//DIALOG
	private JOptionPane optionPane = new JOptionPane();
	private JLabel lblMessage = new JLabel("<html>Product already exists in the database.<br>"
	    + "This will be treated as a restocking.</html>");
	
	//MANAGER INITIALIZERS
	private ProductManager productManage = new ProductManager();
	private BatchManager batchManage = new BatchManager();
	
	//OTHER VARIABLES
	private LocalDate currentDate = LocalDate.now();
	private ArrayList<String> yearList = new ArrayList<String>();
	
	public AddProductPanel() {
		setTitle("Add Product");
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setBounds(100, 100, 366, 289);
		getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		getContentPane().add(panel, "cell 0 0,grow");
		
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		lblProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductName.setBackground(Color.WHITE);
		
		txtProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductName.setColumns(20);
		
		lblProductNameError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setBackground(Color.WHITE);
				
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setColumns(10);
		
		lblBuyingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setBackground(Color.WHITE);
	
		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setColumns(10);
		
		lblSellingPriceError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setBackground(Color.WHITE);
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
		
		lblQuantityError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		lblExpiryDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboExpiryMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));

		yearList.add(String.valueOf(currentDate.getYear() + 2));
		yearList.add(String.valueOf(currentDate.getYear() + 3));
		yearList.add(String.valueOf(currentDate.getYear() + 4));
		yearList.add(String.valueOf(currentDate.getYear() + 5));
		
		cboExpiryYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryYear.setModel(new DefaultComboBoxModel(new String[] {"Year", yearList.get(0), yearList.get(1), yearList.get(2), yearList.get(3)}));
		
		lblExpiryDateError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(51, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAdd.addActionListener(this);
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		panel.add(lblTitle, "cell 0 0 2 1");
		panel.add(lblProductName, "cell 0 1,alignx right");
		panel.add(txtProductName, "cell 1 1");
		panel.add(lblProductNameError, "cell 1 2,aligny top");
		panel.add(lblBuyingPrice, "cell 0 3,alignx right");
		panel.add(txtBuyingPrice, "cell 1 3");
		panel.add(lblBuyingPriceError, "cell 1 4,alignx left,aligny top");
		panel.add(lblSellingPrice, "cell 0 5,alignx right");
		panel.add(txtSellingPrice, "cell 1 5");
		panel.add(lblSellingPriceError, "cell 1 6,aligny top");
		panel.add(lblQuantity, "cell 0 7,alignx right");
		panel.add(txtQuantity, "cell 1 7");
		panel.add(lblQuantityError, "cell 1 8,aligny top");
		panel.add(lblExpiryDate, "cell 0 9,alignx right");
		panel.add(cboExpiryMonth, "flowx,cell 1 9,alignx left");
		panel.add(lblExpiryDateError, "cell 1 10,aligny top");
		panel.add(verticalStrut, "cell 1 11");
		panel.add(btnAdd, "cell 1 12,alignx right,aligny bottom");
		panel.add(cboExpiryYear, "cell 1 9");
		
		getRootPane().setDefaultButton(btnAdd);

		//DIALOG
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void addItem() {
		productManage.addProduct(getProductName(), getSellingPrice());
		batchManage.addBatch(productManage.getLatestProductID(), getQuantity(), getBuyingPrice(), getExpiryMonth(), getExpiryYear());
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
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(txtBuyingPrice.getText()) && !StringUtils.isEmptyOrWhitespaceOnly(txtSellingPrice.getText())){
			if(getBuyingPrice() >= getSellingPrice()) {
				lblSellingPriceError.setText("Selling price must be greater than buying price.");
				txtSellingPrice.setBackground(Color.YELLOW);
				valid = false;
			}
			else {
				lblSellingPriceError.setText("");
				txtSellingPrice.setBackground(Color.WHITE);
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
					valid = false;
				}
				else {
					lblQuantityError.setText("");
					txtQuantity.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblQuantityError.setText("Quantity must be numeric.");
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
			if(productManage.getProductID(getProductName()) == 0) {
				addItem();
				dispose();
			}
			else {
				int n = optionPane.showConfirmDialog(this, lblMessage, "Product Already Exists", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(n == 0) {
					//insert code for restocking
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		}
	}
	
	public String getProductName() {
		return txtProductName.getText();
	}
	
	public float getBuyingPrice() {
		return Float.parseFloat(txtBuyingPrice.getText());
	}
	
	public float getSellingPrice() {
		return Float.parseFloat(txtSellingPrice.getText());
	}
	
	public int getQuantity() {
		return Integer.parseInt(txtQuantity.getText());
	}
	
	public int getExpiryMonth() {
		return cboExpiryMonth.getSelectedIndex();
	}
	
	public int getExpiryYear() {
		return Integer.parseInt(cboExpiryYear.getSelectedItem().toString());
	}
}