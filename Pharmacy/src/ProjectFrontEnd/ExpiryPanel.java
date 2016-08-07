package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class ExpiryPanel extends JPanel {
	private JTable tblNearExpiredProducts;

	public ExpiryPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblNearExpiredProducts = new JLabel("Near-Expired Products");
		panel.add(lblNearExpiredProducts, "cell 0 0");
		lblNearExpiredProducts.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JLabel lblAsOf = new JLabel("as of");
		panel.add(lblAsOf, "cell 0 1");
		
		JScrollPane scrNearExpiredProducts = new JScrollPane();
		panel.add(scrNearExpiredProducts, "cell 0 2,grow");
		
		tblNearExpiredProducts = new JTable();
		tblNearExpiredProducts.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Product Name", "Expiry Date", "Quantity"
			}
		));
		scrNearExpiredProducts.setViewportView(tblNearExpiredProducts);
		
		JButton btnRemove = new JButton("Remove");
		panel.add(btnRemove, "cell 0 3,alignx right");
		
	}
	
	public void update() {
		
	}
}