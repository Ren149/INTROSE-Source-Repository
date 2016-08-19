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

import net.miginfocom.swing.MigLayout;

public class ReorderListPanel extends JPanel implements ActionListener{
	private JLabel lblTitle = new JLabel("Reorder List");
	private JButton btnUpdateReorderPoints = new JButton("Update Reorder Points");
	private JScrollPane scrReorderList = new JScrollPane();
	private JTable tblReorderList = new JTable();
	
	public ReorderListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		
		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		
		tblReorderList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Product Name", "Quantity Left", "Demand"
			}
		));
		tblReorderList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrReorderList.setViewportView(tblReorderList);
		
		btnUpdateReorderPoints.setBackground(new Color(0, 204, 0));
		btnUpdateReorderPoints.setForeground(Color.WHITE);
		btnUpdateReorderPoints.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnUpdateReorderPoints.addActionListener(this);
		
		add(lblTitle, "cell 0 0,alignx left");
		add(scrReorderList, "cell 0 1,grow");
		add(btnUpdateReorderPoints, "cell 0 2,alignx right");
	
		loadReorderList();
	}

	public void loadReorderList() {		
		ArrayList<Integer> id;
		DefaultTableModel reorderListTableModel = new DefaultTableModel();
		reorderListTableModel.setColumnIdentifiers(new String[] {"Product Name", "Quantity Left", "Demand"});

		//insert here code that gives arrayList id the list of products whose quantities are below their corresponding reorder points.
		
		tblReorderList.setModel(reorderListTableModel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ReorderPointPanel rpp = new ReorderPointPanel();
		
		rpp.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e) {
				loadReorderList();
			}
		});
	}
}