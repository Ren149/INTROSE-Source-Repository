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

public class ProductListPanel extends JPanel implements ActionListener {
	//PRODUCT SEARCH PANEL
	private JLabel lblProductListDate = new JLabel("Product List as of ");
	private JTextField txtProductSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	
	//TABLE LIST PANEL
	JPanel pnlProductListTable = new JPanel();
	private JTable tblProductListTable;
	
	//BOTTOM PANEL
	private JLabel lblNoItemsShown = new JLabel("Items Showed");
	private final JPanel pnlProducSearchPanel = new JPanel();
	private final JPanel panel = new JPanel();

	//MANAGER INITIALIZERS
	private ProductManager productManage = new ProductManager();
	
	public ProductListPanel() {
		setLayout(new MigLayout("", "[grow][grow][][][][][][][][][grow]", "[grow][grow][]"));
		//PRODUCT SEARCH PANEL
		add(pnlProducSearchPanel, "cell 0 0 11 1,alignx left,aligny top");
		pnlProducSearchPanel.setLayout(new MigLayout("", "[100px:n][][][][][][][][][][][][][][]", "[26px]"));
		lblProductListDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlProducSearchPanel.add(lblProductListDate, "cell 0 0 6 1,alignx left,aligny top");
		txtProductSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		pnlProducSearchPanel.add(txtProductSearch, "cell 13 0");
		txtProductSearch.setColumns(10);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlProducSearchPanel.add(btnSearch, "cell 14 0");
		
		btnSearch.setBackground(Color.YELLOW);
		btnSearch.addActionListener(this);
		add(pnlProductListTable, "cell 0 1 11 1,grow");
		
		
		//TABLE PANEL
		tblProductListTable = new JTable();
		tblProductListTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductListTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tblProductListTable.setModel(productManage.viewProductsWithPrice());
		pnlProductListTable.add(tblProductListTable);
		
		
		//BOTTOM PANEL
		add(panel, "flowx,cell 0 2 11 1");
		lblNoItemsShown.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panel.add(lblNoItemsShown);

	}
	
	public void updateTable()
	{
		if(txtProductSearch.getText().toString().equals(""))
			tblProductListTable.setModel(productManage.viewProductsWithPrice());
			else
				tblProductListTable.setModel(productManage.searchProductWithPrice(txtProductSearch.getText()));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch))
		{
			updateTable();
		}
		}
}

