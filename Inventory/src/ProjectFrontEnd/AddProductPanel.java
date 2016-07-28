package GUI;

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

public class AddProductPanel extends JPanel implements ActionListener{
	//Product Name
	private JLabel lblProductName = new JLabel("1. Enter product name:");
	private JTextField txtProductName = new JTextField();
	
	//Buy Price
	private JLabel lblBuyingPrice = new JLabel("2. Enter buying price:");
	private JTextField txtBuyingPrice = new JTextField();
	
	//Sell Price
	private JLabel lblSellingPrice = new JLabel("3. Enter selling price:");
	private JTextField txtSellingPrice;
	
	//Product Quantity
	private JLabel lblProductQuantity = new JLabel("4. Enter quantity of product:");
	private JTextField txtProductQuantity = new JTextField();
	
	//Add Product
	private JLabel lblProductStatus = new JLabel("<Item Status>");
	private JButton btnAdd = new JButton("Add");;
	

	
	public AddProductPanel() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 366, 289);
		setLayout(new MigLayout("", "[][][]", "[][][][][][][][][][]"));
		
		//Product Name
		lblProductName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(lblProductName, "cell 0 0");
		setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(txtProductName, "cell 2 0,growx");
		txtProductName.setColumns(10);
		
		//Buy Price
		lblBuyingPrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblBuyingPrice, "cell 0 2");
		txtBuyingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(txtBuyingPrice, "cell 2 2,growx");
		txtBuyingPrice.setColumns(10);
		
		//Sell Price
		lblSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(lblSellingPrice, "cell 0 4");
		txtSellingPrice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(txtSellingPrice, "cell 2 4,growx");
		txtSellingPrice.setColumns(10);
		
		//Product Quantity
		lblProductQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(lblProductQuantity, "cell 0 6");
		txtProductQuantity = new JTextField();
		txtProductQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(txtProductQuantity, "cell 2 6,growx");
		txtProductQuantity.setColumns(10);
		
		//Add Product
		lblProductStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(lblProductStatus, "flowx,cell 2 9,aligny bottom");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(51, 204, 0));
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(btnAdd, "cell 2 9,alignx right,aligny bottom");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
