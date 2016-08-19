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
	private JTable tableSet1SalesReportTable;
	private JTable tableSet2DateTotalTable;
	private JTable table;
	
	public SalesReport() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Pharmacia Regine Sales Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel salesReportMainPanel = new JPanel();
		JLabel lblSalesReport = new JLabel("Sales Report");
		JLabel salesReportDateRangeLabel = new JLabel("Please choose a date range to show its sales report");
		JPanel salesReportLeftPanel = new JPanel();
		JRadioButton salesReportTodayRadioBtn = new JRadioButton("Today");
		JRadioButton salesReportDayInputRadioBtn = new JRadioButton("Past");
		JRadioButton salesReportDateRangeRadioBtn = new JRadioButton("Custom Range");
		salesReportDayInput = new JTextField();
		salesReportDayInput.setBackground(Color.WHITE);
		JLabel lblDays = new JLabel(" days");
		JSpinner salesReportCustomDate1 = new JSpinner();
		salesReportCustomDate1.setForeground(Color.WHITE);
		JLabel lblNewLabel = new JLabel("to");
		JSpinner salesReportCustomDate2 = new JSpinner();
		salesReportCustomDate2.setForeground(Color.WHITE);
		JSeparator separator = new JSeparator();
		JPanel salesReportRightPanel = new JPanel();
		JPanel tableSet1 = new JPanel();
		tableSet1.setBackground(Color.WHITE);
		JPanel tableSet2 = new JPanel();
		tableSet2.setBackground(Color.WHITE);
		
		setBackground(Color.WHITE);
		getContentPane().setLayout(new MigLayout("", "[800,grow]", "[grow]"));
		
		salesReportMainPanel.setBackground(Color.WHITE);
		getContentPane().add(salesReportMainPanel, "cell 0 0,grow");
		salesReportMainPanel.setLayout(new MigLayout("", "[189.00,fill][][454.00][grow]", "[][grow]"));
		
		lblSalesReport.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSalesReport.setBackground(Color.WHITE);
		salesReportMainPanel.add(lblSalesReport, "cell 0 0");
		
		salesReportDateRangeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salesReportMainPanel.add(salesReportDateRangeLabel, "cell 2 0");
		
		salesReportLeftPanel.setBackground(Color.WHITE);
		salesReportMainPanel.add(salesReportLeftPanel, "cell 0 1,grow");
		salesReportLeftPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][][]"));
		
		salesReportTodayRadioBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportTodayRadioBtn.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(salesReportTodayRadioBtn);
		salesReportLeftPanel.add(salesReportTodayRadioBtn, "cell 0 0");
		salesReportTodayRadioBtn.setSelected(true);
		
		salesReportDayInputRadioBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportDayInputRadioBtn.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(salesReportDayInputRadioBtn);
		salesReportLeftPanel.add(salesReportDayInputRadioBtn, "flowx,cell 0 1");
		
		salesReportDateRangeRadioBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportDateRangeRadioBtn.setBackground(Color.WHITE);
		salesReportDateSelectionRadioBtn.add(salesReportDateRangeRadioBtn);
		salesReportLeftPanel.add(salesReportDateRangeRadioBtn, "cell 0 2");
		
		salesReportDayInput.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportDayInput.setText("7");
		salesReportLeftPanel.add(salesReportDayInput, "cell 0 1");
		salesReportDayInput.setColumns(3);
		
		
		
		
		lblDays.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportLeftPanel.add(lblDays, "cell 0 1");
		
		salesReportCustomDate1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportCustomDate1.setModel(new SpinnerDateModel());
		salesReportCustomDate1.setEditor(new JSpinner.DateEditor(salesReportCustomDate1, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		salesReportLeftPanel.add(salesReportCustomDate1, "flowx,cell 0 3");
		
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportLeftPanel.add(lblNewLabel, "cell 0 3");
		
		salesReportCustomDate2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportCustomDate2.setModel(new SpinnerDateModel());
		salesReportCustomDate2.setEditor(new JSpinner.DateEditor(salesReportCustomDate2, new SimpleDateFormat("MMM-dd-yyyy").toPattern()));
		salesReportLeftPanel.add(salesReportCustomDate2, "cell 0 3");
		
		separator.setForeground(new Color(204, 204, 204));
		separator.setOrientation(SwingConstants.VERTICAL);
		salesReportMainPanel.add(separator, "cell 1 0 1 2,grow");
		
		
		salesReportRightPanel.setBackground(Color.WHITE);
		salesReportMainPanel.add(salesReportRightPanel, "cell 2 1 2 1,grow");
		salesReportRightPanel.setLayout(new CardLayout(0, 0));
		
		
		
		salesReportRightPanel.add(tableSet1, "Table Set 1");
		tableSet1.setLayout(new MigLayout("", "[450.00][grow]", "[grow][]"));
		
		JScrollPane tableSet1ScrollPane = new JScrollPane();
		tableSet1.add(tableSet1ScrollPane, "cell 0 0 2 1,grow");
		
		tableSet1SalesReportTable = new JTable();
		tableSet1SalesReportTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tableSet1SalesReportTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Sales Count", "Total Sales"
			}
		));
		tableSet1ScrollPane.setViewportView(tableSet1SalesReportTable);
		
		JLabel tableSet1TotalTxtLbl = new JLabel("Overall Total Sales: ");
		tableSet1TotalTxtLbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet1.add(tableSet1TotalTxtLbl, "cell 0 1,alignx right");
		
		JLabel tableSet1OverallTotalSalesLbl = new JLabel("0.00");
		tableSet1OverallTotalSalesLbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet1.add(tableSet1OverallTotalSalesLbl, "cell 1 1,alignx right");
		
		salesReportRightPanel.add(tableSet2, "Table Set 2");
		tableSet2.setLayout(new MigLayout("", "[158.00][256.00][164.00,grow][81.00,grow]", "[grow][]"));
		
		JScrollPane tableSet2ScrollPane1 = new JScrollPane();
		tableSet2.add(tableSet2ScrollPane1, "cell 0 0 2 1,grow");
		
		tableSet2DateTotalTable = new JTable();
		tableSet2DateTotalTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Sales Total"
			}
		));
		tableSet2DateTotalTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tableSet2ScrollPane1.setViewportView(tableSet2DateTotalTable);
		
		JScrollPane tableSet2ScrollPane2 = new JScrollPane();
		tableSet2.add(tableSet2ScrollPane2, "cell 2 0 2 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Sales Count", "Sales Total"
			}
		));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tableSet2ScrollPane2.setViewportView(table);
		
		JLabel tableSet2TotalTxtLbl1 = new JLabel("Overall Total Sales: ");
		tableSet2TotalTxtLbl1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet2.add(tableSet2TotalTxtLbl1, "cell 0 1,alignx right");
		
		JLabel tableSet2OverallTotalSalesLbl1 = new JLabel("0.00");
		tableSet2OverallTotalSalesLbl1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet2.add(tableSet2OverallTotalSalesLbl1, "cell 1 1,alignx right");
		
		JLabel tableSet2TotalTxtLbl2 = new JLabel("Overall Total Sales: ");
		tableSet2TotalTxtLbl2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet2.add(tableSet2TotalTxtLbl2, "cell 2 1,alignx right");
		
		JLabel tableSet2OverallTotalSalesLbl2 = new JLabel("0.00");
		tableSet2OverallTotalSalesLbl2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableSet2.add(tableSet2OverallTotalSalesLbl2, "cell 3 1,alignx right");
		
		CardLayout cardLayout = (CardLayout) salesReportRightPanel.getLayout();
		cardLayout.show(salesReportRightPanel, "Table Set 1");

		
		//Radio buttons that update the text above the table
		
		salesReportDateRangeLabel.setText("Sales report for today, " + getCurrentDateString());
		

		salesReportDayInput.setEditable(false);
		salesReportCustomDate1.setEnabled(false);
		salesReportCustomDate2.setEnabled(false);
		ActionListener salesReportActionListener = new ActionListener() {
			
			private String temp = salesReportDayInput.getText();
			
			public void actionPerformed(ActionEvent actionEvent) {
				
				
				
				if(salesReportTodayRadioBtn.isSelected()){
					cardLayout.show(salesReportRightPanel, "Table Set 1");
					
					salesReportDateRangeLabel.setText("Sales report for today, " + getCurrentDateString());
					salesReportDayInput.setText(temp);
					salesReportDayInput.setEditable(false);
					salesReportCustomDate1.setEnabled(false);
					salesReportCustomDate2.setEnabled(false);
				}
				else if(salesReportDayInputRadioBtn.isSelected()){
					
					cardLayout.show(salesReportRightPanel, "Table Set 2");
					salesReportDayInput.setEditable(true);
					salesReportCustomDate1.setEnabled(false);
					salesReportCustomDate2.setEnabled(false);
					salesReportDateRangeLabel.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
					
					salesReportDayInput.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							if(salesReportDayInputRadioBtn.isSelected()){
								
								salesReportDateRangeLabel.setText(getPastDateString(Integer.parseInt(salesReportDayInput.getText())));
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
				else if(salesReportDateRangeRadioBtn.isSelected()){
					
					cardLayout.show(salesReportRightPanel, "Table Set 2");
					
					salesReportDayInput.setEditable(false);
					salesReportCustomDate1.setEnabled(true);
					salesReportCustomDate2.setEnabled(true);
					
					salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate2.getValue()));
					
					salesReportCustomDate1.addChangeListener(new ChangeListener() { 
						
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(salesReportDateRangeRadioBtn.isSelected()){
							
								salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate2.getValue()));
							}
						}
					});
					
					salesReportCustomDate2.addChangeListener(new ChangeListener() { 
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(salesReportDateRangeRadioBtn.isSelected()){
							
								salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(salesReportCustomDate2.getValue()));
							}
						}
					});
				}
			}
		};
		
		
		
		
		salesReportTodayRadioBtn.addActionListener(salesReportActionListener);
		salesReportDayInputRadioBtn.addActionListener(salesReportActionListener);
		salesReportDateRangeRadioBtn.addActionListener(salesReportActionListener);
		
		
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
