//MILESTONE
package ProjectFrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReorderPointPanel extends JFrame implements ActionListener{

	//HEADER
	private JLabel lblReorderPointHeader = new JLabel("Adjust Reorder Point");

	//SLOW ITEMS
	private JLabel lblSlowMovingItems = new JLabel("Slow-moving Items:");
	private JTextField txtSlowMovingItemPoint = new JTextField();
	private JLabel lblSlowPieces = new JLabel("pieces");
	private JLabel lblSlowMovingFormula = new JLabel("(Top 50 of ");
	
	//FAST ITEMS
	private JLabel lblFastMovingItemPoint = new JLabel("Fast-moving Items:");
	private JTextField txtFastMovingItemPoint = new JTextField();
	private JLabel lblFastPieces = new JLabel("pieces");
	private JLabel lblFastMovingFormula = new JLabel("<current formula for fast-moving items>");
	
	//ADJUST BUTTON
	private JButton btnAdjustReorderPoint = new JButton("Adjust");
	
	
	public ReorderPointPanel() {
		//PANEL
		setBackground(new Color(255, 255, 255));
		setFont(new Font("Segoe UI", Font.PLAIN, 11));
		setBounds(100, 100, 350, 252);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		//HEADER
		lblReorderPointHeader.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		getContentPane().add(lblReorderPointHeader, "cell 0 0 2 1");
		
		//SLOW ITEMS
		lblSlowMovingItems.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblSlowMovingItems, "cell 0 1,alignx trailing");
		
		txtSlowMovingItemPoint = new JTextField();
		txtSlowMovingItemPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtSlowMovingItemPoint.setText("<current reordering point>");
		getContentPane().add(txtSlowMovingItemPoint, "flowx,cell 1 1,growx");
		txtSlowMovingItemPoint.setColumns(10);
		
		lblSlowMovingFormula.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblSlowMovingFormula, "cell 0 2,alignx right,aligny top");
		
		
		//FAST ITEMS
		lblFastMovingItemPoint.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblFastMovingItemPoint, "cell 0 3,alignx trailing");
		
		txtFastMovingItemPoint = new JTextField();
		txtFastMovingItemPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtFastMovingItemPoint.setText("<current reordering point>");
		getContentPane().add(txtFastMovingItemPoint, "flowx,cell 1 3,growx,aligny top");
		txtFastMovingItemPoint.setColumns(10);
		
		lblFastMovingFormula.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblFastMovingFormula, "cell 0 4,alignx right,aligny top");
		
		
		//ADJUST BUTTON
		btnAdjustReorderPoint.setForeground(new Color(255, 255, 255));
		btnAdjustReorderPoint.setBackground(new Color(0, 204, 0));
		btnAdjustReorderPoint.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(btnAdjustReorderPoint, "cell 0 5 2 1,alignx right,aligny bottom");
		
		lblSlowPieces.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblSlowPieces, "cell 1 1,alignx left");
		
		lblFastPieces.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		getContentPane().add(lblFastPieces, "cell 1 3");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
