package introsePharmacyInventory;

import java.awt.*;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalePanel extends JPanel{
	private JTextField txtSaleSearch;
	private JTable tblSaleSearch;
	private JTextField textField;
	private JTable tblTransaction;
	public SalePanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[82.00,grow][grow]", "[grow][281.00,grow][grow]"));
		
		JPanel pnlSetDate = new JPanel();
		pnlSetDate.setBackground(Color.WHITE);
		pnlSetDate.setBorder(null);
		add(pnlSetDate, "cell 0 0,grow");
		pnlSetDate.setLayout(new MigLayout("", "[][76.00][18.00][61.00]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("1. Select Sales Date");
		pnlSetDate.add(lblNewLabel_1, "cell 0 0");
		
		JComboBox comboBoxSalesDateMonth = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateMonth, "cell 1 0,growx");
		
		JComboBox comboBoxSalesDateDay = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateDay, "cell 2 0,growx");
		
		JComboBox comboBoxSalesDateYear = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateYear, "cell 3 0,growx");
		
		JPanel pnlSelectItem = new JPanel();
		pnlSelectItem.setBackground(Color.WHITE);
		pnlSelectItem.setBorder(null);
		add(pnlSelectItem, "cell 0 1,grow");
		pnlSelectItem.setLayout(new MigLayout("", "[][grow]", "[grow][][grow]"));
		
		JLabel lblNewLabel_2 = new JLabel("2. Select Item Sold");
		pnlSelectItem.add(lblNewLabel_2, "cell 0 1,alignx trailing");
		
		txtSaleSearch = new JTextField();
		pnlSelectItem.add(txtSaleSearch, "flowx,cell 1 1,growx");
		txtSaleSearch.setColumns(10);
		
		JButton btnSaleSearch = new JButton("Search");
		btnSaleSearch.setBackground(Color.YELLOW);
		btnSaleSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlSelectItem.add(btnSaleSearch, "cell 1 1");
		
		JScrollPane scrollPaneSelectItem = new JScrollPane();
		pnlSelectItem.add(scrollPaneSelectItem, "cell 0 2 2 1,grow");
		
		tblSaleSearch = new JTable();
		scrollPaneSelectItem.setViewportView(tblSaleSearch);
		
		JPanel pnlTransaction = new JPanel();
		pnlTransaction.setBackground(Color.WHITE);
		pnlTransaction.setBorder(null);
		add(pnlTransaction, "cell 1 0 1 3,grow");
		pnlTransaction.setLayout(new MigLayout("", "[116.00,grow][]", "[][][grow][][]"));
		
		JLabel lblNewLabel_4 = new JLabel("4. Check Items for Transaction");
		pnlTransaction.add(lblNewLabel_4, "cell 0 0");
		
		JLabel lblNewLabel_5 = new JLabel("Select and item and click remove to delete it from the cart");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
		pnlTransaction.add(lblNewLabel_5, "cell 0 1");
		
		JButton btnSaleRemove = new JButton("Remove");
		btnSaleRemove.setBackground(Color.RED);
		pnlTransaction.add(btnSaleRemove, "cell 1 1");
		
		JScrollPane scrollPaneTransaction = new JScrollPane();
		pnlTransaction.add(scrollPaneTransaction, "cell 0 2 2 1,grow");
		
		tblTransaction = new JTable();
		scrollPaneTransaction.setViewportView(tblTransaction);
		
		JLabel lblNewLabel = new JLabel("Total: ");
		pnlTransaction.add(lblNewLabel, "flowx,cell 1 3");
		
		JLabel lblTransactionTotal = new JLabel("<Amount>");
		lblTransactionTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnlTransaction.add(lblTransactionTotal, "cell 1 3");
		
		JLabel lblSalePromptMsg = new JLabel("User Prompt Message");
		pnlTransaction.add(lblSalePromptMsg, "flowx,cell 0 4");
		lblSalePromptMsg.setForeground(new Color(0, 128, 0));
		
		JButton btnRecordSale = new JButton("Record");
		pnlTransaction.add(btnRecordSale, "cell 1 4,growx");
		btnRecordSale.setBackground(Color.GREEN);
		
		JPanel pnlSetQty = new JPanel();
		pnlSetQty.setBackground(Color.WHITE);
		pnlSetQty.setBorder(null);
		add(pnlSetQty, "cell 0 2,grow");
		pnlSetQty.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblNewLabel_3 = new JLabel("3. Enter Quantity");
		pnlSetQty.add(lblNewLabel_3, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		pnlSetQty.add(textField, "flowx,cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblPieces = new JLabel("pieces");
		pnlSetQty.add(lblPieces, "cell 1 0");
		
		JLabel lblSaleWarning = new JLabel("User warning message");
		lblSaleWarning.setForeground(Color.RED);
		pnlSetQty.add(lblSaleWarning, "flowx,cell 1 1");
		
		JButton btnSaleAdd = new JButton("Add to Cart");
		btnSaleAdd.setBackground(Color.CYAN);
		pnlSetQty.add(btnSaleAdd, "cell 1 1");
	}

}
