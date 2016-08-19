package ProjectFrontEnd;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class SalesReport extends JFrame implements ActionListener, ChangeListener, DocumentListener {
	private ButtonGroup salesReportDateSelectionRadioBtn = new ButtonGroup();
	private JTextField salesReportDayInput;
	private JPanel salesReportMainPanel = new JPanel();
	private JLabel lblTitle = new JLabel("Sales Report");
	private JLabel lblSalesReportTable = new JLabel("Please choose a date range to show its sales report");
	private JLabel lblDays = new JLabel(" days");
	private JLabel lblTo = new JLabel("to");
	private JLabel lblError = new JLabel("Error");		
	private JLabel lblTotalCashSales_1 = new JLabel("Total Cash Sales:");
	private JLabel lblTotalCashSalesValue_1 = new JLabel("0.00");
	private JLabel lblTotalCashSales_2 = new JLabel("Total Cash Sales:");
	private JLabel lblTotalCashSalesValue_2 = new JLabel("0.00");
	private JSeparator separator = new JSeparator();
	private JSpinner cboDate_1 = new JSpinner();
	private JSpinner cboDate_2 = new JSpinner();
	private JTable tblDateList = new JTable();
	private JTable tblProductList = new JTable();
	private JScrollPane scrDateList = new JScrollPane();
	private JScrollPane scrProductList = new JScrollPane();
	private JRadioButton rdbtnToday = new JRadioButton("Today");
	private JRadioButton rdbtnPast = new JRadioButton("Past");	
	private JRadioButton rdbtnCustom = new JRadioButton("Custom Range");
	
	public SalesReport() {
		getContentPane().setBackground(Color.WHITE);
		
		setBackground(Color.WHITE);
		setTitle("Farmacia Regine Sales Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new CardLayout(10, 10));
		getContentPane().add(salesReportMainPanel, "name_448996073156723");
		
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTitle.setBackground(Color.WHITE);
		
		lblSalesReportTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		rdbtnToday.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnToday.setBackground(Color.WHITE);
		rdbtnToday.setSelected(true);
		
		salesReportDateSelectionRadioBtn.add(rdbtnToday);

		tblDateList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Sales Total"
			}
		));
		tblDateList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		scrDateList.setViewportView(tblDateList);

		tblProductList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Sales Count", "Sales Total"
			}
		));
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		scrProductList.setViewportView(tblProductList);
		
		rdbtnPast.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnPast.setBackground(Color.WHITE);
		rdbtnPast.addActionListener(this);
		
		salesReportDayInput = new JTextField();
		salesReportDayInput.setBackground(Color.WHITE);
		salesReportDayInput.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportDayInput.setText("7");
		salesReportDayInput.setColumns(3);
		salesReportDayInput.setEditable(false);

		lblError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		rdbtnCustom.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnCustom.setBackground(Color.WHITE);
		rdbtnCustom.addActionListener(this);

		salesReportDateSelectionRadioBtn.add(rdbtnPast);
		salesReportDateSelectionRadioBtn.add(rdbtnCustom);
		
		separator.setForeground(new Color(204, 204, 204));
		separator.setOrientation(SwingConstants.VERTICAL);

		lblSalesReportTable.setText("Sales report for today, " + getCurrentDateString());
		
		lblDays.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblTotalCashSales_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTotalCashSalesValue_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTotalCashSales_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTotalCashSalesValue_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		cboDate_1.setForeground(Color.WHITE);
		cboDate_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboDate_1.setModel(new SpinnerDateModel());
		cboDate_1.setEditor(new JSpinner.DateEditor(cboDate_1, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		cboDate_1.setEnabled(false);
		cboDate_1.addChangeListener(this);
		
		cboDate_2.setForeground(Color.WHITE);
		cboDate_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboDate_2.setModel(new SpinnerDateModel());
		cboDate_2.setEditor(new JSpinner.DateEditor(cboDate_2, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		cboDate_2.setEnabled(false);
		cboDate_2.addChangeListener(this);
		
		salesReportMainPanel.setBackground(Color.WHITE);
		salesReportMainPanel.setLayout(new MigLayout("", "[189.00,fill][20px:20px][454.00][grow]", "[][][][][][][][]"));
		salesReportMainPanel.add(lblTitle, "cell 0 0");
		salesReportMainPanel.add(lblSalesReportTable, "cell 2 0");
		salesReportMainPanel.add(rdbtnToday, "cell 0 1");
		salesReportMainPanel.add(scrDateList, "cell 2 1 1 6");
		salesReportMainPanel.add(scrProductList, "cell 3 1 1 6");
		salesReportMainPanel.add(rdbtnPast, "flowx,cell 0 2,alignx left");
		salesReportMainPanel.add(salesReportDayInput, "cell 0 2,alignx left");
		salesReportMainPanel.add(lblError, "cell 0 3,alignx left");
		salesReportMainPanel.add(rdbtnCustom, "cell 0 4");
		salesReportMainPanel.add(separator, "cell 1 0 1 8,alignx center,growy");
		salesReportMainPanel.add(lblDays, "cell 0 2,alignx left");
		salesReportMainPanel.add(cboDate_1, "flowx,cell 0 5");
		salesReportMainPanel.add(lblTo, "cell 0 5");
		salesReportMainPanel.add(cboDate_2, "cell 0 5");
		salesReportMainPanel.add(lblTotalCashSales_1, "flowx,cell 2 7,alignx right");
		salesReportMainPanel.add(lblTotalCashSalesValue_1, "cell 2 7,alignx right");
		salesReportMainPanel.add(lblTotalCashSales_2, "flowx,cell 3 7,alignx right");
		salesReportMainPanel.add(lblTotalCashSalesValue_2, "cell 3 7,alignx right");
		
		loadDateList();
		loadProductList();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void loadDateList() {
		//load ka ng ArrayList of Dates
		//tapos ipakita mo sa Date table
	}
	
	private void loadProductList() {
		//load ka ng ArrayList of ProductIDs
		//tapos ipakita mo sa Products table
	}
	
	public void update() {
		loadDateList();
		loadProductList();
	}
	
	private String getCurrentDateString(){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);    
        return dateFormat.format(cal.getTime());
	}

	private String getPastDateString(int i){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);    
        return "Sales report from " + dateFormat.format(cal.getTime()) + " to " + getCurrentDateString();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(rdbtnToday.isSelected()){
			lblSalesReportTable.setText("Sales report for today, " + getCurrentDateString());
			salesReportDayInput.setText(salesReportDayInput.getText());
			salesReportDayInput.setEditable(false);
			cboDate_1.setEnabled(false);
			cboDate_2.setEnabled(false);
		}
		else if(rdbtnPast.isSelected()){
			salesReportDayInput.setEditable(true);
			cboDate_1.setEnabled(false);
			cboDate_2.setEnabled(false);
			lblSalesReportTable.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
			salesReportDayInput.getDocument().addDocumentListener(this);
		}
		else if(rdbtnCustom.isSelected()){
			salesReportDayInput.setEditable(false);
			cboDate_1.setEnabled(true);
			cboDate_2.setEnabled(true);
			lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(rdbtnCustom.isSelected()){
			lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		if(rdbtnPast.isSelected()){
			lblSalesReportTable.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
		}
	}


	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
	}
}
