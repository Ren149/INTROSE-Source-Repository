package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends JPanel implements ActionListener {
	private int rowCount = 0;

	private JLabel lblProductListDate = new JLabel("Product List as of " + new Date());
	private JLabel lblItemCount = new JLabel("Displaying " + rowCount + " items");
	private JTextField txtProductSearch = new JTextField();
	private JButton btnSearch = new JButton("SEARCH");
	private JTable tblProductListTable;
	private JScrollPane scrollPane = new JScrollPane();
	private ProductManager productManage = new ProductManager();
	private final JButton btnClear = new JButton("CLEAR");
	private final JPanel panel = new JPanel();
	
	public ProductListPanel() {
		setBackground(new Color(245, 245, 245));
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(new MigLayout("", "[][grow]", "[][][grow][]"));
		
		lblProductListDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductSearch.setColumns(10);
		
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(new Color(60, 179, 113));
		btnSearch.addActionListener(this);
		
		tblProductListTable = new JTable();

		scrollPane.setViewportView(tblProductListTable);
		
		tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		viewTable();
		
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		btnClear.setBackground(new Color(0, 139, 139));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnClear.addActionListener(this);
		
		panel.add(lblProductListDate, "cell 0 0 2 1");
		panel.add(txtProductSearch, "cell 0 1,growy");
		panel.add(btnSearch, "flowx,cell 1 1");
		panel.add(scrollPane, "cell 0 2 2 1,grow");
		panel.add(lblItemCount, "cell 0 3 2 1");
		panel.add(btnClear, "cell 1 1");
		
		add(panel, "cell 0 0,grow");
	}

	public void viewTable() {
		tblProductListTable.setModel(productManage.viewProductsWithPrice());
		rowCount = tblProductListTable.getRowCount();
		lblItemCount.setText("Displaying " + rowCount + " items");
	}
	
	public void searchTableWithPrices() {
		if(txtProductSearch.getText().toString().equals("")) {
			viewTable();
		}
		else
			tblProductListTable.setModel(productManage.searchProductWithPrice(txtProductSearch.getText()));
			rowCount = tblProductListTable.getRowCount();
			lblItemCount.setText("Displaying " + rowCount + " items");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch)) {
			searchTableWithPrices();
			this.repaint();
		}
		else if(e.getSource().equals(btnClear)) {
			txtProductSearch.setText("");
		}
	}
	
	public void update() {
		lblProductListDate.setText("Product List as of " + new Date());
		viewTable();
	}
}