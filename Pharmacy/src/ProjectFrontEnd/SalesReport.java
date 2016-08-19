package ProjectFrontEnd;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.util.Date;
import java.util.Calendar;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.time.LocalDate;
import java.awt.Font;
import java.awt.CardLayout;

public class SalesReport extends JFrame {
	private final ButtonGroup salesReportDateSelectionRadioBtn = new ButtonGroup();
	private JTextField salesReportDayInput;
	private String currDateString;
	private String pastDateString;
	private JTable tblDateList;
	private JTable tblProductList;
	
	public SalesReport() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Pharmacia Regine Sales Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel salesReportMainPanel = new JPanel();
		JLabel lblTitle = new JLabel("Sales Report");
		JLabel lblSalesReportTable = new JLabel("Please choose a date range to show its sales report");
		JSeparator separator = new JSeparator();
		
		setBackground(Color.WHITE);
		getContentPane().setLayout(new CardLayout(10, 10));
		
		salesReportMainPanel.setBackground(Color.WHITE);
		getContentPane().add(salesReportMainPanel, "name_448996073156723");
		salesReportMainPanel.setLayout(new MigLayout("", "[189.00,fill][20px:20px][454.00][grow]", "[][][][][][][][]"));
		
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTitle.setBackground(Color.WHITE);
		salesReportMainPanel.add(lblTitle, "cell 0 0");
		
		lblSalesReportTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salesReportMainPanel.add(lblSalesReportTable, "cell 2 0");
		JRadioButton rdbtnToday = new JRadioButton("Today");
		salesReportMainPanel.add(rdbtnToday, "cell 0 1");
		
		rdbtnToday.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnToday.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(rdbtnToday);
		rdbtnToday.setSelected(true);
		
		
		
		
		rdbtnToday.addActionListener(salesReportActionListener);
		
		JScrollPane scrDateList = new JScrollPane();
		salesReportMainPanel.add(scrDateList, "cell 2 1 1 6");
		
		tblDateList = new JTable();
		tblDateList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Sales Total"
			}
		));
		tblDateList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		scrDateList.setViewportView(tblDateList);
		
		JScrollPane scrProductList = new JScrollPane();
		salesReportMainPanel.add(scrProductList, "cell 3 1 1 6");
		
		tblProductList = new JTable();
		tblProductList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Sales Count", "Sales Total"
			}
		));
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		scrProductList.setViewportView(tblProductList);
		JRadioButton rdbtnPast = new JRadioButton("Past");
		salesReportMainPanel.add(rdbtnPast, "flowx,cell 0 2,alignx left");
		
		rdbtnPast.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnPast.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(rdbtnPast);
		rdbtnPast.addActionListener(salesReportActionListener);
		salesReportDayInput = new JTextField();
		salesReportMainPanel.add(salesReportDayInput, "cell 0 2,alignx left");
		salesReportDayInput.setBackground(Color.WHITE);
		
		salesReportDayInput.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportDayInput.setText("7");
		salesReportDayInput.setColumns(3);
		

		salesReportDayInput.setEditable(false);
		
		JLabel lblError = new JLabel("Error");
		lblError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		salesReportMainPanel.add(lblError, "cell 0 3,alignx left");
		JRadioButton rdbtnCustom = new JRadioButton("Custom Range");
		salesReportMainPanel.add(rdbtnCustom, "cell 0 4");
		
		rdbtnCustom.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		rdbtnCustom.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(rdbtnCustom);
		rdbtnCustom.addActionListener(salesReportActionListener);
		JSpinner cboDate_1 = new JSpinner();
		salesReportMainPanel.add(cboDate_1, "flowx,cell 0 5");
		cboDate_1.setForeground(Color.WHITE);
		
		cboDate_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboDate_1.setModel(new SpinnerDateModel());
		cboDate_1.setEditor(new JSpinner.DateEditor(cboDate_1, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		cboDate_1.setEnabled(false);
		
		separator.setForeground(new Color(204, 204, 204));
		separator.setOrientation(SwingConstants.VERTICAL);
		salesReportMainPanel.add(separator, "cell 1 0 1 8,alignx center,growy");

		
		//Radio buttons that update the text above the table
		
		lblSalesReportTable.setText("Sales report for today, " + getCurrentDateString());
		JLabel lblDays = new JLabel(" days");
		salesReportMainPanel.add(lblDays, "cell 0 2,alignx left");
		
		
		
		
		lblDays.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		JLabel lblTo = new JLabel("to");
		salesReportMainPanel.add(lblTo, "cell 0 5");
		
		lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		JSpinner cboDate_2 = new JSpinner();
		salesReportMainPanel.add(cboDate_2, "cell 0 5");
		cboDate_2.setForeground(Color.WHITE);
		
		cboDate_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboDate_2.setModel(new SpinnerDateModel());
		cboDate_2.setEditor(new JSpinner.DateEditor(cboDate_2, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		cboDate_2.setEnabled(false);
		
		JLabel lblTotalCashSales_1 = new JLabel("Total Cash Sales:");
		salesReportMainPanel.add(lblTotalCashSales_1, "flowx,cell 2 7,alignx right");
		lblTotalCashSales_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblTotalCashSalesValue_1 = new JLabel("0.00");
		salesReportMainPanel.add(lblTotalCashSalesValue_1, "cell 2 7,alignx right");
		lblTotalCashSalesValue_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblTotalCashSales_2 = new JLabel("Total Cash Sales:");
		salesReportMainPanel.add(lblTotalCashSales_2, "flowx,cell 3 7,alignx right");
		lblTotalCashSales_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblTotalCashSalesValue_2 = new JLabel("0.00");
		salesReportMainPanel.add(lblTotalCashSalesValue_2, "cell 3 7,alignx right");
		lblTotalCashSalesValue_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ActionListener salesReportActionListener = new ActionListener() {
			
			private String temp = salesReportDayInput.getText();
			
			public void actionPerformed(ActionEvent actionEvent) {
				
				
				
				if(rdbtnToday.isSelected()){
					cardLayout.show(salesReportRightPanel, "Table Set 1");
					
					lblSalesReportTable.setText("Sales report for today, " + getCurrentDateString());
					salesReportDayInput.setText(temp);
					salesReportDayInput.setEditable(false);
					cboDate_1.setEnabled(false);
					cboDate_2.setEnabled(false);
				}
				else if(rdbtnPast.isSelected()){
					
					cardLayout.show(salesReportRightPanel, "Table Set 2");
					salesReportDayInput.setEditable(true);
					cboDate_1.setEnabled(false);
					cboDate_2.setEnabled(false);
					lblSalesReportTable.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
					
					salesReportDayInput.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							if(rdbtnPast.isSelected()){
								
								lblSalesReportTable.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
								temp = salesReportDayInput.getText();
							}
						}

						@Override
						public void removeUpdate(DocumentEvent e) {
							//none
						}

						@Override
						public void changedUpdate(DocumentEvent e) {
							//none
						}
					});
					
				}
				else if(rdbtnCustom.isSelected()){
					
					cardLayout.show(salesReportRightPanel, "Table Set 2");
					
					salesReportDayInput.setEditable(false);
					cboDate_1.setEnabled(true);
					cboDate_2.setEnabled(true);
					
					lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
					
					cboDate_1.addChangeListener(new ChangeListener() { 
						
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(rdbtnCustom.isSelected()){
							
								lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
							}
						}
					});
					
					cboDate_2.addChangeListener(new ChangeListener() { 
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(rdbtnCustom.isSelected()){
							
								lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
							}
						}
					});
				}
			}
		};
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
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
}
