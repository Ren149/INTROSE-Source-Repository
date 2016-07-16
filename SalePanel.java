package ProjectFrontEnd;

import java.awt.*;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class SalePanel extends JPanel implements ActionListener {
	
	private JPanel pnlAddToCart = new JPanel();
	private JTextField txtSearch = new JTextField();
	private JButton btnSearch = new JButton("Search");
	private JTable tblSaleSearch = new JTable();
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JTextField txtQuantity = new JTextField();
	private JScrollPane scrollPaneSelectItem = new JScrollPane();
	private JTable tblCart = new JTable();
	private JButton btnAddToCart = new JButton("Add to Cart");
	private JLabel lblAddToCartFeedback = new JLabel("User warning message");
	private JPanel pnlCart = new JPanel();
	private JLabel lblHelpCart = new JLabel("Select and item and click remove to delete it from the cart");
	private JButton btnRemove = new JButton("Remove");
	private JScrollPane scrollPaneCart = new JScrollPane();
	private JLabel lblSalesDate = new JLabel("Sales Date:");
	private JLabel lblTotal = new JLabel("Total: ");
	private JLabel lblTotalValue = new JLabel("<Amount>");
	private JButton btnRecord = new JButton("Record");
	private JLabel lblFeedbackCart = new JLabel("User Prompt Message");
	private JComboBox cboSalesDateMonth = new JComboBox();
	private JComboBox cboSalesDateDay = new JComboBox();
	private JComboBox cboSalesDateYear = new JComboBox();
	
	public SalePanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[300][grow]", "[grow]"));
		
		pnlAddToCart.setBackground(Color.WHITE);
		pnlAddToCart.setBorder(new TitledBorder(null, "Add to Cart", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAddToCart.setLayout(new MigLayout("", "[grow][]", "[][grow][][][]"));
		
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSearch.setColumns(10);
		
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearch.setBackground(new Color(0, 204, 0));
		btnSearch.addActionListener(this);
		
		pnlAddToCart.add(txtSearch, "cell 0 0,grow");
		pnlAddToCart.add(btnSearch, "cell 1 0,alignx left");
		
				btnSearch.setForeground(new Color(255, 255, 255));
				btnSearch.setBackground(new Color(0, 204, 0));
				btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		tblSaleSearch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblSaleSearch.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Item Name", "Quantity"
			}
		));
		tblSaleSearch.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblSaleSearch.getColumnModel().getColumn(1).setMinWidth(65);
		tblSaleSearch.getColumnModel().getColumn(1).setMaxWidth(65);
		scrollPaneSelectItem.setViewportView(tblSaleSearch);
		
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		btnAddToCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAddToCart.setMinimumSize(new Dimension(100, 35));
		btnAddToCart.setBackground(new Color(51, 255, 153));
		
		lblAddToCartFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblAddToCartFeedback.setForeground(Color.RED);
		
		txtQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtQuantity.setColumns(10);
		
		pnlAddToCart.add(scrollPaneSelectItem, "cell 0 1 2 1,grow");
		pnlAddToCart.add(lblQuantity, "cell 0 2,alignx right,growy");
		pnlAddToCart.add(btnAddToCart, "cell 0 3 2 1,alignx right");
		pnlAddToCart.add(lblAddToCartFeedback, "cell 0 4 2 1,alignx right");
		pnlAddToCart.add(txtQuantity, "cell 1 2,grow");
		
		pnlCart.setBackground(Color.WHITE);
		pnlCart.setBorder(new TitledBorder(null, "Cart", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCart.setLayout(new MigLayout("", "[][grow]", "[][grow][][][]"));
		
		lblHelpCart.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRemove.setBackground(Color.RED);
		
		tblCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblCart.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Item Name", "Quantity", "Selling Price", "Subtotal"
			}
		));
		scrollPaneCart.setViewportView(tblCart);
		
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblTotalValue.setFont(new Font("Segoe UI", Font.BOLD, 11));
		
		lblSalesDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cboSalesDateMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cboSalesDateDay.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cboSalesDateYear.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020"}));
		cboSalesDateYear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblFeedbackCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFeedbackCart.setForeground(new Color(0, 128, 0));
		
		btnRecord.setForeground(Color.WHITE);
		btnRecord.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRecord.setMinimumSize(new Dimension(100, 35));
		btnRecord.setBackground(new Color(0, 204, 0));
		
		pnlCart.add(lblHelpCart, "cell 0 0");
		pnlCart.add(btnRemove, "cell 1 0,alignx right");
		pnlCart.add(scrollPaneCart, "cell 0 1 2 1,grow");
		pnlCart.add(lblSalesDate, "flowx,cell 0 2");
		pnlCart.add(lblTotal, "flowx,cell 1 2,alignx right");
		pnlCart.add(lblTotalValue, "cell 1 2,alignx right");
		pnlCart.add(btnRecord, "flowy,cell 1 3,alignx right");
		pnlCart.add(lblFeedbackCart, "cell 0 4 2 1,alignx right");
		pnlCart.add(cboSalesDateMonth, "cell 0 2");
		pnlCart.add(cboSalesDateDay, "cell 0 2");
		pnlCart.add(cboSalesDateYear, "cell 0 2");
		
		add(pnlAddToCart, "cell 0 0,alignx left,growy");
		add(pnlCart, "cell 1 0,grow");
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
