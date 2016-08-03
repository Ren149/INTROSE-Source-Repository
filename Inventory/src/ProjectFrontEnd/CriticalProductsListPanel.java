package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;

public class CriticalProductsListPanel extends JPanel implements ActionListener{
	//CRITICAL PRODUCT LIST
	private JPanel pnlCriticalItemsList = new JPanel();
		//--HEADER
	private JLabel lblCriticalItemsList = new JLabel("Critical and Out-of-Stock Items:");
	private JButton btnExport = new JButton("Export List");
		//--TABLE
	private JTable tblCriticalItems = new JTable();

	//ADJUST CRITICAL POINT
	private JPanel pnlAdjustCriticalPoint = new JPanel();
		//--HEADER
	private JLabel lblAdjustCriticalPoint = new JLabel("Adjust the Critical Point:");
		//--SLOW ITEM ADJUSTMENT
	private JLabel lblSlowMovingItems = new JLabel("Slow-moving Items:");
	private JTextField txtSlowCriticalPoint = new JTextField();
	private JLabel lblSlowPiecesTag = new JLabel("pieces");
	private JLabel lblCurrentAmtSlowPieces = new JLabel("<current pieces>");
	
		//--FAST ITEM ADJUSTMENT
	private JLabel lblFastMovingItems = new JLabel("Fast-moving Items:");
	private JTextField txtFastCriticalPoint = new JTextField();
	private JLabel lblFastPiecesTag = new JLabel("pieces");
	private JLabel lblCurrentAmtFastPieces = new JLabel("<current pieces>");
	
		//--ADJUST BUTTON
	private JButton btnAdjust = new JButton("Adjust");
	
	
	
	private CriticalProductsListPanel() {
		//FULL PAGE BACKGROUND
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 1010, 324);
		setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		//-CRITICAL ITEMS LIST PANEL
		pnlCriticalItemsList.setBackground(new Color(255, 255, 255));
		add(pnlCriticalItemsList, "cell 0 0,grow");
		pnlCriticalItemsList.setLayout(new MigLayout("", "[grow][][][][][][]", "[][grow]"));
			//--HEADER LABEL
		lblCriticalItemsList.setFont(new Font("Segoe UI", Font.BOLD, 11));
		pnlCriticalItemsList.add(lblCriticalItemsList, "cell 0 0,alignx left");
		
			//-EXPORT BUTTON
		btnExport.setBackground(Color.BLUE);
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Segoe UI", Font.BOLD, 11));
		pnlCriticalItemsList.add(btnExport, "cell 6 0,alignx left");
		
			//--TABLE
		tblCriticalItems.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlCriticalItemsList.add(tblCriticalItems, "cell 0 1 7 1,grow");
		
		//-ADJUST CRITICAL POINT PANEL
		pnlAdjustCriticalPoint.setBackground(new Color(255, 255, 255));
		add(pnlAdjustCriticalPoint, "cell 1 0,grow");
		pnlAdjustCriticalPoint.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][]"));
		
			//--HEADER LABEL
		lblAdjustCriticalPoint.setFont(new Font("Segoe UI", Font.BOLD, 11));
		pnlAdjustCriticalPoint.add(lblAdjustCriticalPoint, "cell 0 0");
		
		
		
		
		
			//--SLOW MOVING ITEM ADJUSTMENT LABEL
		lblSlowMovingItems.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(lblSlowMovingItems, "cell 0 1,alignx left");
		
			//--SLOW MOVING ITEM ADJUSTMENT TEXT FIELD
		txtSlowCriticalPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(txtSlowCriticalPoint, "flowx,cell 1 1,alignx left");
		txtSlowCriticalPoint.setColumns(10);
		
			//--SLOW MOVING ITEM ADJUSTMENT TAG
		lblSlowPiecesTag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(lblSlowPiecesTag, "cell 1 1,alignx trailing");
		
			//--SLOW MOVING ITEM ADJUSTMENT CURRENT AMOUNT TAG
		lblCurrentAmtSlowPieces.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		pnlAdjustCriticalPoint.add(lblCurrentAmtSlowPieces, "cell 0 2,alignx left,aligny top");
		
		
		
		
			//--FAST MOVING ITEM ADJUSTMENT LABEL
		lblFastMovingItems.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(lblFastMovingItems, "cell 0 4,alignx left");
		
			//--SLOW MOVING ITEM ADJUSTMENT TEXT FIELD
		txtFastCriticalPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(txtFastCriticalPoint, "flowx,cell 1 4,alignx left");
		txtFastCriticalPoint.setColumns(10);
		
			//--SLOW MOVING ITEM ADJUSTMENT TAG
		lblFastPiecesTag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlAdjustCriticalPoint.add(lblFastPiecesTag, "cell 1 4,alignx trailing");
		
			//--SLOW MOVING ITEM ADJUSTMENT CURRENT AMOUNT TAG
		lblCurrentAmtFastPieces.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		pnlAdjustCriticalPoint.add(lblCurrentAmtFastPieces, "cell 0 5,alignx left,aligny top");
		
		
		
		
		//--ADJUST BUTTON
		btnAdjust.setForeground(new Color(255, 255, 255));
		btnAdjust.setBackground(new Color(51, 204, 0));
		btnAdjust.setFont(new Font("Segoe UI", Font.BOLD, 11));
		pnlAdjustCriticalPoint.add(btnAdjust, "cell 1 6,alignx right,aligny bottom");
	}

}
