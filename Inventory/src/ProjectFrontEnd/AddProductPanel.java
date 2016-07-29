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
		
		cboExpiryYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboExpiryYear.setModel(new DefaultComboBoxModel(new String[] {"Year"}));
		
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

	public boolean allValidInputs() {
		//insert code for error correction
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(allValidInputs()) {
			//insert code to add data to database			
			dispose();
		}
		else {
			//insert code to set error message text
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