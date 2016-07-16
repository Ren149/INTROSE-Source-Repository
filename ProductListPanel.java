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

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ProductListPanel extends JPanel implements ActionListener {

	private JLabel lblProductListDate = new JLabel("Product List as of <insert date>");
	private JLabel lblItemCount = new JLabel("Displaying 10 items");
	private JTextField txtProductSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	private JTable tblProductListTable;
	private JScrollPane scrollPane = new JScrollPane();
	private ProductManager productManage = new ProductManager();
	
	public ProductListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[][grow]", "[][][][grow][]"));
		
		lblProductListDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProductSearch.setColumns(10);
		btnSearch.setForeground(Color.WHITE);
		
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.addActionListener(this);
		
		tblProductListTable = new JTable();
		scrollPane.setViewportView(tblProductListTable);
		tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tblProductListTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Product Name", "Buying Price", "Selling Price", "Quantity"
			}
		));
		
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		add(lblProductListDate, "cell 0 0");
		add(txtProductSearch, "cell 0 1,grow");
		add(btnSearch, "cell 1 1,alignx left,growy");
		add(scrollPane, "cell 0 3 2 1,grow");
		add(lblItemCount, "cell 0 4");
	}
	
	public void update(){
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch)) {
			update();
		}
	}
}

