//MILESTONE
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.LineItemManager;
import ProjectBackEnd.ProductManager;
import ProjectBackEnd.ReorderPointManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.ListSelectionModel;

public class ReorderListPanel extends JPanel implements ActionListener {
	private JLabel lblTitle = new JLabel("Reorder List");
	private JButton btnUpdateReorderPoints = new JButton("Update Reorder Points");
	private JScrollPane scrReorderList = new JScrollPane();
	private JTable tblReorderList = new JTable() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();
	private LineItemManager lm = new LineItemManager();
	private ReorderPointManager rm = new ReorderPointManager();
	private int lowReorder;
	private int highReorder;
	private final JLabel lblItemCount = new JLabel("");

	public ReorderListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][]", "[][grow][]"));

		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		tblReorderList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblReorderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblReorderList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));

		scrReorderList.setViewportView(tblReorderList);

		btnUpdateReorderPoints.setBackground(new Color(0, 204, 0));
		btnUpdateReorderPoints.setForeground(Color.WHITE);
		btnUpdateReorderPoints.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnUpdateReorderPoints.addActionListener(this);

		add(lblTitle, "cell 0 0,alignx left");
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));

		add(lblItemCount, "cell 1 0");
		add(scrReorderList, "cell 0 1 2 1,grow");
		add(btnUpdateReorderPoints, "cell 0 2 2 1,alignx right");

		loadReorderList();
	}

	public void loadReorderList() {
		ArrayList<Integer> id;
		DefaultTableModel reorderListTableModel = new DefaultTableModel();
		reorderListTableModel.setColumnIdentifiers(new String[] { "Product Name", "Quantity Left", "Demand" });

		id = lm.getProductIDs();

		lowReorder = rm.getLowReorderPoint();
		highReorder = rm.getHighReorderPoint();

		for (int i : id) {
			ArrayList<Integer> totalSaleCounts = new ArrayList<>();

			String productName = pm.getProductName(i);
			String quantity = String.valueOf(bm.getBatchQuantityFromProduct(i));
			String demand = lm.determineDemand(i);

			if ((demand.equals("High") && bm.getBatchQuantityFromProduct(i) <= highReorder)
					|| (demand.equals("Low") && bm.getBatchQuantityFromProduct(i) <= lowReorder)) {
				reorderListTableModel.addRow(new Object[] { productName, quantity, demand });
			}
		}

		tblReorderList.setModel(reorderListTableModel);

		int rowCount = tblReorderList.getRowCount();

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
		loadReorderList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReorderPointPanel rpp = new ReorderPointPanel();

		rpp.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				loadReorderList();
			}
		});
	}
}