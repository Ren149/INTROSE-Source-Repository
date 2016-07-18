package ProjectFrontEnd;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

import ProjectBackEnd.ProductManager;
import ProjectBackEnd.BatchManager;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.Date;

public class ProductListPanel extends JPanel implements ActionListener {
	private Date currentDate = new Date();
	private int rowCount = 0;

	private JLabel lblProductListDate = new JLabel("Product List as of " + currentDate);
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
		
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[][][grow][]"));
		panel.add(lblProductListDate, "cell 0 0 2 1");
		
		lblProductListDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtProductSearch, "cell 0 1,growy");
		
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductSearch.setColumns(10);
		panel.add(btnSearch, "flowx,cell 1 1");
		
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(new Color(60, 179, 113));
		btnSearch.addActionListener(this);
		
		tblProductListTable = new JTable();
		panel.add(scrollPane, "cell 0 2 2 1,grow");
		
		scrollPane.setViewportView(tblProductListTable);
		
		tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		viewTable();
		panel.add(lblItemCount, "cell 0 3 2 1");
		
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panel.add(btnClear, "cell 1 1");
		
		btnClear.setBackground(new Color(0, 139, 139));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	}

	public void viewTable()
	{
		tblProductListTable.setModel(productManage.viewProductsWithPrice());
		rowCount = tblProductListTable.getRowCount();
		lblItemCount.setText("Displaying " + rowCount + " items");
	}
	
	public void searchTableWithPrices()
	{
		if(txtProductSearch.getText().toString().equals(""))
		{
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
	}
	
	public void update() {
		currentDate = new Date();
		lblProductListDate = new JLabel("Product List as of " + currentDate);
		viewTable();
	}
}

