package ProjectFrontEnd;

//MILESTONE
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MainWindow extends JFrame implements ActionListener {
	private ProductListPanel productListPanel = new ProductListPanel();
	private SalePanel salePanel = new SalePanel();
	private ReorderListPanel reorderListPanel = new ReorderListPanel();
	private ExpiryPanel expiryListPanel = new ExpiryPanel();
	private JButton btnSale = new JButton("Sale");
	private JButton btnProductList = new JButton("Product List");
	private JButton btnReorderList = new JButton("Reorder List");
	private JButton btnExpiryList = new JButton("Expiry List");
	private JMenuBar menuBar = new JMenuBar();

	private Color selectedButtonColor = new Color(230, 230, 250);

	public MainWindow() {
		setTitle("Farmacia Regine Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(245, 245, 245));
		setJMenuBar(menuBar);

		btnSale.setBackground(selectedButtonColor);
		btnSale.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSale.setSize(new Dimension(100, 35));
		btnSale.addActionListener(this);

		btnProductList.setBackground(Color.WHITE);
		btnProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnProductList.addActionListener(this);

		btnReorderList.setBackground(Color.WHITE);
		btnReorderList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnReorderList.addActionListener(this);

		btnExpiryList.setBackground(Color.WHITE);
		btnExpiryList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnExpiryList.addActionListener(this);

		menuBar.setBackground(Color.WHITE);
		menuBar.setBorderPainted(false);
		menuBar.add(btnSale);
		menuBar.add(btnProductList);
		menuBar.add(btnExpiryList);
		menuBar.add(btnReorderList);

		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new CardLayout(10, 10));
		getContentPane().add("SALE", salePanel);
		getContentPane().add("PRODUCT_LIST", productListPanel);
		getContentPane().add("EXPIRY_LIST", expiryListPanel);
		getContentPane().add("REORDER_LIST", reorderListPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSale)) {
			salePanel.update();
			((CardLayout) getContentPane().getLayout()).show(getContentPane(), "SALE");
			btnSale.setBackground(selectedButtonColor);
			btnProductList.setBackground(Color.WHITE);
			btnExpiryList.setBackground(Color.WHITE);
			btnReorderList.setBackground(Color.WHITE);
		} else if (e.getSource().equals(btnProductList)) {
			productListPanel.update();
			((CardLayout) getContentPane().getLayout()).show(getContentPane(), "PRODUCT_LIST");
			btnSale.setBackground(Color.WHITE);
			btnProductList.setBackground(selectedButtonColor);
			btnExpiryList.setBackground(Color.WHITE);
			btnReorderList.setBackground(Color.WHITE);

		} else if (e.getSource().equals(btnExpiryList)) {
			expiryListPanel.update();
			((CardLayout) getContentPane().getLayout()).show(getContentPane(), "EXPIRY_LIST");
			btnSale.setBackground(Color.WHITE);
			btnProductList.setBackground(Color.WHITE);
			btnExpiryList.setBackground(selectedButtonColor);
			btnReorderList.setBackground(Color.WHITE);

		} else if (e.getSource().equals(btnReorderList)) {
			reorderListPanel.update();
			((CardLayout) getContentPane().getLayout()).show(getContentPane(), "REORDER_LIST");
			btnSale.setBackground(Color.WHITE);
			btnProductList.setBackground(Color.WHITE);
			btnExpiryList.setBackground(Color.WHITE);
			btnReorderList.setBackground(selectedButtonColor);
		}

		getContentPane().requestFocusInWindow();
	}
}