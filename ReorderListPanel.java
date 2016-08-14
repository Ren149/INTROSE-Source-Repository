//MILESTONE
package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

public class ReorderListPanel extends JPanel implements ActionListener{

	private JLabel lblReorderingListHeader = new JLabel("Items that need reordering or that are nearing the reordering point:");
	private JTable tblReorderList = new JTable();
	private JLabel lblReorderAdjustment = new JLabel("Adjust the Reordering point of the products:");
	private JButton btnUpdateReorderPoint = new JButton("Update");
	
	
	public ReorderListPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][]", "[][grow][]"));
		
		lblReorderingListHeader.setFont(new Font("Segoe UI", Font.BOLD, 11));
		add(lblReorderingListHeader, "cell 0 0,alignx left");
		
		tblReorderList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblReorderList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(tblReorderList, "cell 0 1 2 1,grow");
		
		lblReorderAdjustment.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		add(lblReorderAdjustment, "flowx,cell 0 2,alignx right");
		
		//When clicked the button should open the "ReorderPointPanel"
		btnUpdateReorderPoint.setBackground(new Color(0, 204, 0));
		btnUpdateReorderPoint.setForeground(Color.WHITE);
		btnUpdateReorderPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnUpdateReorderPoint.addActionListener(this);
		add(btnUpdateReorderPoint, "cell 1 2,alignx right");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReorderPointPanel rpp = new ReorderPointPanel();
	}

}
