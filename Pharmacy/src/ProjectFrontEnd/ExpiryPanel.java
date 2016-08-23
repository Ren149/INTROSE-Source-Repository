//MILESTONE
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class ExpiryPanel extends JPanel implements ItemListener, ActionListener, ListSelectionListener, MouseListener {
	private JTable tblExpiryList = new JTable() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JLabel lblTitle = new JLabel("Expiry List");
	private JScrollPane scrNearExpiredProducts = new JScrollPane();
	private JPanel panel = new JPanel();
	private JRadioButton rdbtnThisMonth = new JRadioButton("This month");
	private JRadioButton rdbtnWithinTheNext = new JRadioButton("Within the next");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();
	private JLabel lblItemCount = new JLabel("");
	private JComboBox comboBox = new JComboBox();
	private JComboBox cboMonth = new JComboBox();
	private JLabel lblMonths = new JLabel("month");
	private LocalDate currentDate = LocalDate.now();
	private JButton btnRemove = new JButton("Remove");

	private ArrayList<Integer> id;

	public ExpiryPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow][]"));

		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

		tblExpiryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblExpiryList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblExpiryList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblExpiryList.getSelectionModel().addListSelectionListener(this);

		scrNearExpiredProducts.setViewportView(tblExpiryList);

		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));

		rdbtnThisMonth.setBackground(Color.WHITE);
		rdbtnThisMonth.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		rdbtnThisMonth.setActionCommand("This month");
		rdbtnThisMonth.setSelected(true);
		rdbtnThisMonth.addActionListener(this);

		rdbtnWithinTheNext.setBackground(Color.WHITE);
		rdbtnWithinTheNext.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		rdbtnWithinTheNext.setActionCommand("Within the next");
		rdbtnWithinTheNext.setSelected(false);
		rdbtnWithinTheNext.addActionListener(this);

		buttonGroup.add(rdbtnThisMonth);
		buttonGroup.add(rdbtnWithinTheNext);

		cboMonth.setBackground(Color.WHITE);
		cboMonth.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
		cboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboMonth.addItemListener(this);
		cboMonth.setEnabled(false);

		lblMonths.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		btnRemove.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnRemove.addActionListener(this);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setForeground(Color.BLACK);
		btnRemove.setEnabled(false);

		add(lblTitle, "cell 0 0 2 1");
		add(rdbtnThisMonth, "flowx,cell 0 1,growy");
		add(scrNearExpiredProducts, "cell 0 2 2 1,grow");
		add(lblItemCount, "cell 1 1,alignx right,aligny bottom");
		add(rdbtnWithinTheNext, "cell 0 1,growy");
		add(cboMonth, "cell 0 1");
		add(lblMonths, "cell 0 1,growy");
		add(btnRemove, "cell 1 3,alignx right");

		addMouseListener(this);
	}

	private void loadExpiryList() {
		DefaultTableModel expiryListTableModel = new DefaultTableModel();
<<<<<<< HEAD
		expiryListTableModel.setColumnIdentifiers(new String[] {"Product Name", "Lot Number", "Entry Date", "Expiry Date", "Quantity"});
		
		 if(rdbtnThisMonth.isSelected()) {
			 id = bm.getExpiredBatchIDList(0);
		 }
		 else{
			 id = bm.getExpiredBatchIDList(Integer.parseInt((String) cboMonth.getSelectedItem()));
		 }
		
		for(int i : id) {
=======
		expiryListTableModel
				.setColumnIdentifiers(new String[] { "Product Name", "Lot Number", "Expiry Date", "Quantity" });

		if (rdbtnThisMonth.isSelected()) {
			id = bm.getExpiredBatchIDList(0);
		} else {
			id = bm.getExpiredBatchIDList(Integer.parseInt((String) cboMonth.getSelectedItem()));
		}

		for (int i : id) {
>>>>>>> b15bf2f1bf6f949f8531d3cf767e335ea4fcbc69
			String productName = pm.getProductName(bm.getProductID(i));
			String lotnumber = bm.getLotNumber(i);
			String quantity = bm.getBatchQuantity(i) + "";
			Date entryDateTemp = bm.getEntryDate(i);
			String entryDate = "";
			
			switch(entryDateTemp.getMonth()) {
			case 0: entryDate += "Jan "; break;
			case 1: entryDate += "Feb "; break;
			case 2: entryDate += "Mar "; break;
			case 3: entryDate += "Apr "; break;
			case 4: entryDate += "May "; break;
			case 5: entryDate += "Jun "; break;
			case 6: entryDate += "Jul "; break;
			case 7: entryDate += "Aug "; break;
			case 8: entryDate += "Sep "; break;
			case 9: entryDate += "Oct "; break;
			case 10: entryDate += "Nov "; break;
			case 11: entryDate += "Dec "; break;
		}
		
			entryDate += String.format("%02d", entryDateTemp.getDate()) + ", " + (entryDateTemp.getYear() + 1900);
		
			String expiryDate = "";

			switch (bm.getExpiryMonth(i)) {
			case 1:
				expiryDate += "Jan ";
				break;
			case 2:
				expiryDate += "Feb ";
				break;
			case 3:
				expiryDate += "Mar ";
				break;
			case 4:
				expiryDate += "Apr ";
				break;
			case 5:
				expiryDate += "May ";
				break;
			case 6:
				expiryDate += "Jun ";
				break;
			case 7:
				expiryDate += "Jul ";
				break;
			case 8:
				expiryDate += "Aug ";
				break;
			case 9:
				expiryDate += "Sep ";
				break;
			case 10:
				expiryDate += "Oct ";
				break;
			case 11:
				expiryDate += "Nov ";
				break;
			case 12:
				expiryDate += "Dec ";
				break;
			}
			expiryDate += bm.getExpiryYear(i);
<<<<<<< HEAD
			
			expiryListTableModel.addRow(new Object[] {productName, lotnumber, entryDate, expiryDate, quantity});
=======

			expiryListTableModel.addRow(new Object[] { productName, lotnumber, expiryDate, quantity });
>>>>>>> b15bf2f1bf6f949f8531d3cf767e335ea4fcbc69
		}

		tblExpiryList.setModel(expiryListTableModel);

		DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
		rendererRight.setHorizontalAlignment(SwingConstants.RIGHT);

		tblExpiryList.getColumnModel().getColumn(2).setCellRenderer(rendererRight);
		tblExpiryList.getColumnModel().getColumn(3).setCellRenderer(rendererRight);

		int rowCount = tblExpiryList.getRowCount();

		switch (rowCount) {
		case 0:
			lblItemCount.setText("No items displayed.");
			break;
		case 1:
			lblItemCount.setText("Displaying " + rowCount + " product");
			break;
		default:
			lblItemCount.setText("Displaying " + rowCount + " products");
			break;
		}
	}

	public void update() {
		loadExpiryList();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(cboMonth)) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (cboMonth.getSelectedIndex() == 0) {
					lblMonths.setText("month");
				} else {
					lblMonths.setText("months");
				}
			}
			update();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnRemove)) {
			int batchID = id.get(tblExpiryList.getSelectedRow());

			ExpiryRemoveDialog erd = new ExpiryRemoveDialog(batchID);
			erd.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					loadExpiryList();
				}
			});
		} else if (e.getSource().equals(rdbtnThisMonth)) {
			cboMonth.setEnabled(false);
		} else if (e.getSource().equals(rdbtnWithinTheNext)) {
			cboMonth.setEnabled(true);
		}

		update();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(tblExpiryList.getSelectionModel())) {
			if (tblExpiryList.getSelectedRow() == -1) {
				btnRemove.setEnabled(false);
				btnRemove.setBackground(Color.WHITE);
				btnRemove.setForeground(Color.BLACK);
			} else {
				btnRemove.setEnabled(true);
				btnRemove.setBackground(new Color(51, 204, 0));
				btnRemove.setForeground(Color.WHITE);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(this)) {
			tblExpiryList.transferFocusUpCycle();
			tblExpiryList.clearSelection();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}