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

public class SalesReport extends JFrame {
	private final ButtonGroup salesReportDateSelectionRadioBtn = new ButtonGroup();
	private JTextField salesReportDayInput;
	private JTable salesReportTable;
	private String currDateString;
	private String pastDateString;
	
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
		JLabel lblDays = new JLabel(" days");
		JSpinner salesReportCustomDate1 = new JSpinner();
		JLabel lblNewLabel = new JLabel("to");
		JSpinner salesReportCustomDate2 = new JSpinner();
		JSeparator separator = new JSeparator();
		JPanel salesReportRightPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		salesReportTable = new JTable();
		JLabel lblTotal = new JLabel("Total: ");
		JLabel salesReportTotalLabel = new JLabel("0.00");
		JButton salesReportExportListBtn = new JButton("Export List");
		
		setBackground(Color.WHITE);
		getContentPane().setLayout(new MigLayout("", "[800,grow]", "[grow]"));
		
		salesReportMainPanel.setBackground(Color.WHITE);
		getContentPane().add(salesReportMainPanel, "cell 0 0,grow");
		salesReportMainPanel.setLayout(new MigLayout("", "[189.00,fill][][][][grow][]", "[][grow]"));
		
		lblSalesReport.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSalesReport.setBackground(Color.WHITE);
		salesReportMainPanel.add(lblSalesReport, "cell 0 0");
		
		salesReportDateRangeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salesReportMainPanel.add(salesReportDateRangeLabel, "cell 4 0");
		
		salesReportLeftPanel.setBackground(Color.WHITE);
		salesReportMainPanel.add(salesReportLeftPanel, "cell 0 1,grow");
		salesReportLeftPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
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
		
		//Radio buttons that update the text above the table
		
		salesReportDateRangeLabel.setText("Sales report for today, " + getCurrentDateString());
		

		salesReportDayInput.setEditable(false);
		salesReportCustomDate1.setEnabled(false);
		salesReportCustomDate2.setEnabled(false);
		ActionListener salesReportActionListener = new ActionListener() {
			
			private String temp = salesReportDayInput.getText();
			
			public void actionPerformed(ActionEvent actionEvent) {
				if(salesReportTodayRadioBtn.isSelected()){
					salesReportDateRangeLabel.setText("Sales report for today, " + getCurrentDateString());
					salesReportDayInput.setText(temp);
					salesReportDayInput.setEditable(false);
					salesReportCustomDate1.setEnabled(false);
					salesReportCustomDate2.setEnabled(false);
				}
				else if(salesReportDayInputRadioBtn.isSelected()){
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
					salesReportDayInput.setEditable(false);
					salesReportCustomDate1.setEnabled(true);
					salesReportCustomDate2.setEnabled(true);
					
					salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate2.getValue()));
					
					salesReportCustomDate1.addChangeListener(new ChangeListener() { 
						
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(salesReportDateRangeRadioBtn.isSelected()){
							
								salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate2.getValue()));
							}
						}
					});
					
					salesReportCustomDate2.addChangeListener(new ChangeListener() { 
						
						@Override
						public void stateChanged(ChangeEvent e) {
							if(salesReportDateRangeRadioBtn.isSelected()){
							
								salesReportDateRangeLabel.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(salesReportCustomDate2.getValue()));
							}
						}
					});
				}
			}
		};
		
		
		
		
		salesReportTodayRadioBtn.addActionListener(salesReportActionListener);
		salesReportDayInputRadioBtn.addActionListener(salesReportActionListener);
		salesReportDateRangeRadioBtn.addActionListener(salesReportActionListener);
		
		separator.setForeground(new Color(204, 204, 204));
		separator.setOrientation(SwingConstants.VERTICAL);
		salesReportMainPanel.add(separator, "cell 1 0 1 2,grow");
		
		
		salesReportRightPanel.setBackground(Color.WHITE);
		salesReportMainPanel.add(salesReportRightPanel, "cell 4 1,grow");
		salesReportRightPanel.setLayout(new MigLayout("", "[352.00,grow][]", "[grow][][37.00]"));
		
		
		salesReportRightPanel.add(scrollPane, "cell 0 0 2 1,grow");
		
		
		salesReportTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Sales Count", "Total Sales"
			}
		));
		salesReportTable.getColumnModel().getColumn(0).setPreferredWidth(290);
		scrollPane.setViewportView(salesReportTable);
		
		
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salesReportRightPanel.add(lblTotal, "cell 0 1,alignx right");
		
		
		salesReportTotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salesReportRightPanel.add(salesReportTotalLabel, "cell 1 1,alignx right");
		
		
		salesReportExportListBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		salesReportExportListBtn.setForeground(Color.WHITE);
		salesReportExportListBtn.setBackground(new Color(51, 153, 0));
		salesReportRightPanel.add(salesReportExportListBtn, "cell 1 2,grow");
		

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private String getCurrentDateString(){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);    
        return dateFormat.format(cal.getTime());
	}

	private String getPastDateString(int i){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);    
        return "Sales report from " + dateFormat.format(cal.getTime()) + " to " + getCurrentDateString();
	}
}
