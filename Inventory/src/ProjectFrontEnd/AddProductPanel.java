package ProjectFrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.time.*;
import java.util.ArrayList;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class AddProductPanel extends JFrame implements ActionListener{
	
	private JLabel lblProductName = new JLabel("Product Name:");
	private JLabel lblBuyingPrice = new JLabel("Buying Price:");
	private JLabel lblSellingPrice = new JLabel("Selling Price:");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblProductNameError = new JLabel("");
	private JLabel lblBuyingPriceError = new JLabel("");
	private JLabel lblSellingPriceError = new JLabel("");
	private JLabel lblQuantityError = new JLabel("");
	private JLabel lblTitle = new JLabel("Add Product");
	private JLabel lblExpiryDate = new JLabel("Expiry Date:");
	private JTextField txtProductName = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTextField txtSellingPrice = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JButton btnAdd = new JButton("Add");
	private Component verticalStrut = Box.createVerticalStrut(20);
	private JComboBox cboExpiryMonth = new JComboBox();
	private JComboBox cboExpiryYear = new JComboBox();
	private JPanel panel = new JPanel();
	
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
		
		txtProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductName.setColumns(20);
		
		lblBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtBuyingPrice.setColumns(10);
		
		lblSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	
		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSellingPrice.setColumns(10);
		
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
		
		lblExpiryDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboExpiryMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));

		yearList.add(String.valueOf(currentDate.getYear()));
		yearList.add(String.valueOf(currentDate.getYear()+1));
		yearList.add(String.valueOf(currentDate.getYear()+2));
		yearList.add(String.valueOf(currentDate.getYear()+3));
		yearList.add(String.valueOf(currentDate.getYear()+4));
		cboExpiryYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryYear.setModel(new DefaultComboBoxModel(new String[] {"Year", yearList.get(0), yearList.get(1), yearList.get(2), yearList.get(3), yearList.get(4)}));
		
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(51, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAdd.addActionListener(this);
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][]"));
		panel.add(lblTitle, "cell 0 0 2 1");
		panel.add(lblProductName, "cell 0 1,alignx right");
		panel.add(txtProductName, "cell 1 1");
		panel.add(lblProductNameError, "cell 1 2");
		panel.add(lblBuyingPrice, "cell 0 3,alignx right");
		panel.add(txtBuyingPrice, "cell 1 3");
		panel.add(lblBuyingPriceError, "cell 1 4,alignx left");
		panel.add(lblSellingPrice, "cell 0 5,alignx right");
		panel.add(txtSellingPrice, "cell 1 5");
		panel.add(lblSellingPriceError, "cell 1 6");
		panel.add(lblQuantity, "cell 0 7,alignx right");
		panel.add(txtQuantity, "cell 1 7");
		panel.add(lblQuantityError, "cell 1 8");
		panel.add(lblExpiryDate, "cell 0 9,alignx right");
		panel.add(cboExpiryMonth, "flowx,cell 1 9,alignx left");
		panel.add(verticalStrut, "cell 1 10");
		panel.add(btnAdd, "cell 1 11,alignx right,aligny bottom");
		panel.add(cboExpiryYear, "cell 1 9");
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void addItem() {
			productManage.addProduct(getProductName(),
					getSellingPrice());
			batchManage.addBatch(productManage.getLatestProductID(),
					getQuantity(),
					getBuyingPrice(),
					getExpiryMonth(),
					getExpiryYear());
	}
	
	//please let Bernard work on this.
	public boolean allInvalidInputs() {
		
		boolean isWrong = false;
		
		if(getProductName().equals("")){
		}
		else if(productManage.checkDuplicates(getProductName())){
		}
		else if(getBuyingPrice() <= 0){
		}
		else if(getSellingPrice() <= 0){
		}
		else if(getSellingPrice() > getBuyingPrice()){
		}
		else if(getQuantity() <= 0){
		}
		//insert another JLabel next to the expiry fields too
		else if(getExpiryMonth() == 0){
		}
		else if(cboExpiryYear.getSelectedIndex() == 0){
		}
		else if(getExpiryYear() == Integer.parseInt(yearList.get(0))){
			if(getExpiryMonth() < currentDate.getMonthValue())
			{
			}
		}
		return isWrong;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(allInvalidInputs() == false) {
			addItem();
			dispose();
		}
		else {
			//insert code to set error message text
			//shouldn't there be another JLabel to indicate that there is an error in the input.
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