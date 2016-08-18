package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ReorderListPanel extends JPanel implements ActionListener{

	private JLabel lblTitle = new JLabel("Reorder List");
	private JLabel lblItemCount = new JLabel("Displaying: <No. of items>");
	private JTable tblReorderList = new JTable();
	private JButton btnUpdateReorderPoints = new JButton("Update Reorder point");
	
	
	
	
	public ReorderListPanel() {
		
		//PANEL
		setBackground(Color.WHITE);
		setBounds(100, 100, 458, 356);
		setLayout(new MigLayout("", "[grow][]", "[][][grow][]"));
		
		//TITLE
		lblTitle.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		add(lblTitle, "cell 0 0");
		
		//ITEM COUNT
		lblItemCount.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		add(lblItemCount, "cell 0 1");
		
		//TABLE
		tblReorderList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(tblReorderList, "cell 0 2 2 1,grow");
		
		//BUTTON
		btnUpdateReorderPoints.setForeground(Color.WHITE);
		btnUpdateReorderPoints.setBackground(new Color(51, 204, 0));
		btnUpdateReorderPoints.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		add(btnUpdateReorderPoints, "cell 1 3");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
