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
		setLayout(new MigLayout("", "[82.00,grow][grow]", "[281.00,grow][grow]"));
		
		JPanel pnlSelectItem = new JPanel();
		pnlSelectItem.setBackground(Color.WHITE);
		pnlSelectItem.setBorder(new TitledBorder(null, "1. Select item sold:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlSelectItem, "cell 0 0,grow");
		pnlSelectItem.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		txtSaleSearch = new JTextField();
		pnlSelectItem.add(txtSaleSearch, "flowx,cell 0 0,growx");
		txtSaleSearch.setColumns(10);
		
		JButton btnSaleSearch = new JButton("Search");
		btnSaleSearch.setBackground(Color.YELLOW);
		btnSaleSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlSelectItem.add(btnSaleSearch, "cell 0 0");
		
		JScrollPane scrollPaneSelectItem = new JScrollPane();
		pnlSelectItem.add(scrollPaneSelectItem, "cell 0 1,grow");
		
		tblSaleSearch = new JTable();
		scrollPaneSelectItem.setViewportView(tblSaleSearch);
		
		JPanel pnlTransaction = new JPanel();
		pnlTransaction.setBackground(Color.WHITE);
		pnlTransaction.setBorder(new TitledBorder(null, "3. Check items in transaction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlTransaction, "cell 1 0,grow");
		pnlTransaction.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JScrollPane scrollPaneTransaction = new JScrollPane();
		pnlTransaction.add(scrollPaneTransaction, "cell 0 0,grow");
		
		tblTransaction = new JTable();
		scrollPaneTransaction.setViewportView(tblTransaction);
		
		JLabel lblNewLabel = new JLabel("Total: ");
		pnlTransaction.add(lblNewLabel, "flowx,cell 0 1");
		
		JLabel lblTransactionTotal = new JLabel("<Amount>");
		lblTransactionTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnlTransaction.add(lblTransactionTotal, "cell 0 1");
		
		JPanel pnlSetQty = new JPanel();
		pnlSetQty.setBackground(Color.WHITE);
		pnlSetQty.setBorder(new TitledBorder(null, "2. Enter quantity: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlSetQty, "cell 0 1,grow");
		pnlSetQty.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		textField = new JTextField();
		pnlSetQty.add(textField, "flowx,cell 0 0,growx");
		textField.setColumns(10);
		
		JLabel lblPieces = new JLabel("pieces");
		pnlSetQty.add(lblPieces, "cell 0 0");
		
		JLabel lblSaleWarning = new JLabel("User warning message");
		lblSaleWarning.setForeground(Color.RED);
		pnlSetQty.add(lblSaleWarning, "flowx,cell 0 1");
		
		JButton btnSaleAdd = new JButton("Add to Transaction");
		btnSaleAdd.setBackground(Color.CYAN);
		pnlSetQty.add(btnSaleAdd, "cell 0 1");
		
		JPanel pnlSetDate = new JPanel();
		pnlSetDate.setBackground(Color.WHITE);
		pnlSetDate.setBorder(new TitledBorder(null, "4. Set sales date:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlSetDate, "cell 1 1,grow");
		pnlSetDate.setLayout(new MigLayout("", "[93.00,grow][53.00,grow][]", "[][]"));
		
		JComboBox comboBoxSalesDateMonth = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateMonth, "cell 0 0,growx");
		
		JComboBox comboBoxSalesDateDay = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateDay, "flowx,cell 1 0,growx");
		
		JComboBox comboBoxSalesDateYear = new JComboBox();
		pnlSetDate.add(comboBoxSalesDateYear, "cell 2 0,growx");
		
		JLabel lblSalePromptMsg = new JLabel("User Prompt Message");
		lblSalePromptMsg.setForeground(new Color(0, 128, 0));
		pnlSetDate.add(lblSalePromptMsg, "flowx,cell 0 1");
		
		JButton btnRecordSale = new JButton("Record");
		btnRecordSale.setBackground(Color.GREEN);
		pnlSetDate.add(btnRecordSale, "cell 2 1");
	}

}
