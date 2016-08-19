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

import ProjectBackEnd.LineItemManager;
import ProjectBackEnd.ProductManager;
import ProjectBackEnd.SaleManager;
import java.text.ParseException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SalesReport extends JFrame implements ActionListener, ChangeListener, DocumentListener, ListSelectionListener {
	private ButtonGroup salesReportDateSelectionRadioBtn = new ButtonGroup();
	private JTextField txtDay;
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
        
        //OTHER VARIABLES
        
        private ArrayList<Integer> prodIDList = new ArrayList<Integer>();
        private ArrayList<Integer> salesIDList = new ArrayList<Integer>();
        private ArrayList<Integer> TotalSalesList = new ArrayList<Integer>();
        
        //MANAGERS
        private SaleManager sm = new SaleManager();
        private ProductManager pm = new ProductManager();
        private LineItemManager lm = new LineItemManager();
	
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
		rdbtnToday.addActionListener(this);
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
		tblDateList.getSelectionModel().addListSelectionListener(this);
		
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
		
		txtDay = new JTextField();
		txtDay.setBackground(Color.WHITE);
		txtDay.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtDay.setText("7");
		txtDay.setColumns(3);
		txtDay.setEditable(false);
		txtDay.setEnabled(false);

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
		salesReportMainPanel.add(txtDay, "cell 0 2,alignx left");
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
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void loadDateList() {
		//declare ka ng ArrayList dito
                float TotalCashSale1 = 0;

                Date today = new Date();
		DefaultTableModel dateListTableModel = new DefaultTableModel();
		dateListTableModel.setColumnIdentifiers(new String[] {"Date", "Cash Sales"});
		
		if(rdbtnToday.isSelected()) {
                    //TODAY 
                    String entrydate = "2016-08-16";
                    //entrydate = new SimpleDateFormat("yyyy-MM-dd").format(today);

                    salesIDList = sm.getSalesIDList(entrydate);
                    for(int i = 0; i < salesIDList.size(); i++){
                        TotalSalesList.add(sm.getTotalSales(salesIDList.get(i)));
                        TotalCashSale1 += TotalSalesList.get(i);
                    }
                    dateListTableModel.addRow(new Object[] {entrydate,"P "+TotalCashSale1});    
		}
		else if(rdbtnPast.isSelected()) {
			//PAST N DAYS
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(today);
                    int daysToDecrement = -1;
                    int N = Integer.parseInt(txtDay.getText());
	        
                    for(int i = 0; i < N; i++){
                        int cashSale = 0;
                        cal.add(Calendar.DATE, daysToDecrement);
                        today = cal.getTime();
                        String entrydate = "";
                        entrydate = new SimpleDateFormat("yyyy-MM-dd").format(today);
	            
                        salesIDList = sm.getSalesIDList(entrydate);
                        for(int j = 0; j < salesIDList.size(); j++){
                            TotalSalesList.add(sm.getTotalSales(salesIDList.get(j)));
                            cashSale += TotalSalesList.get(j);
                            TotalCashSale1 += TotalSalesList.get(j);                    
                        }
                        
                        dateListTableModel.addRow(new Object[] {entrydate,"P "+cashSale});
                    }
		} else if(rdbtnCustom.isSelected()) {
                    
                String startDateString = new SimpleDateFormat("yyyy-MM-dd").format(cboDate_2.getValue());
                String endDateString = new SimpleDateFormat("yyyy-MM-dd").format(cboDate_1.getValue());
               
                LocalDate startDate = LocalDate.parse(startDateString);
                LocalDate endDate = LocalDate.parse(endDateString);
                
                
                System.out.println(startDate);
                System.out.println(endDate);
                /*
                    try {
                        startDate = df.parse(startDateString);
                    } catch (ParseException ex) {
                        Logger.getLogger(SalesReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        endDate = df.parse(endDateString);
                    } catch (ParseException ex) {
                        Logger.getLogger(SalesReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                /*
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                int daysToDecrement = -1;
                
                String newDateString1 = df.format(startDate);
                String newDateString2 = df.format(endDate);
                
                do{
                    int cashSale = 0;
                        cal.add(Calendar.DATE, daysToDecrement);
                        startDate = cal.getTime();
                        String entrydate = "";
                        entrydate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
	            
                        salesIDList = sm.getSalesIDList(entrydate);
                        for(int j = 0; j < salesIDList.size(); j++){
                            TotalSalesList.add(sm.getTotalSales(salesIDList.get(j)));
                            cashSale += TotalSalesList.get(j);
                            TotalCashSale1 += TotalSalesList.get(j);                    
                        }
                        
                        dateListTableModel.addRow(new Object[] {entrydate,"P "+cashSale});
                        newDateString1 = df.format(startDate);
                        newDateString2 = df.format(endDate);
                }while(newDateString1.equals(newDateString2) != true);
                	*/
		}
		
                lblTotalCashSalesValue_1.setText(String.valueOf(TotalCashSale1));
		tblDateList.setModel(dateListTableModel);
	}
	
	private void loadProductList() {
		float TotalCashSale2 = 0;
                ArrayList<Integer> prodIDList = new ArrayList<>();
		DefaultTableModel productListTableModel = new DefaultTableModel();
		productListTableModel.setColumnIdentifiers(new String[] {"Product Name", "Quantity sold", "Cash Sales"});
                
                if(tblDateList.getSelectedRow() != -1){
                String entrydate = String.valueOf(tblDateList.getValueAt(tblDateList.getSelectedRow(), 0));

                prodIDList = lm.getProdIDList(entrydate);
                
                for(int i = 0; i < prodIDList.size(); i++){
                    String prodName = pm.getProductName(prodIDList.get(i));
                    int QtySold = lm.getTotalProdQty(prodIDList.get(i), entrydate);
                    float unitPrice = lm.getUnitPrice(prodIDList.get(i), entrydate);
                    float cashSale = QtySold * unitPrice;
                    TotalCashSale2 += cashSale;
                    
                    productListTableModel.addRow(new Object[] {prodName, QtySold, cashSale});
                    }
                }
	
                lblTotalCashSalesValue_2.setText(String.valueOf(TotalCashSale2));
		tblProductList.setModel(productListTableModel);
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
			txtDay.setEditable(false);
			txtDay.setEnabled(false);
			cboDate_1.setEnabled(false);
			cboDate_2.setEnabled(false);
                        loadDateList();
		}
		else if(rdbtnPast.isSelected()){
			txtDay.setEditable(true);
			txtDay.setEnabled(true);
			cboDate_1.setEnabled(false);
			cboDate_2.setEnabled(false);
			lblSalesReportTable.setText(getPastDateString(Integer.parseInt(txtDay.getText())));
			txtDay.getDocument().addDocumentListener(this);
                        loadDateList();
		}
		else if(rdbtnCustom.isSelected()){
			txtDay.setEditable(false);
			txtDay.setEnabled(false);
			cboDate_1.setEnabled(true);
			cboDate_2.setEnabled(true);
			lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd, yyyy").format(cboDate_2.getValue()));
                        loadDateList();
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
			lblSalesReportTable.setText(getPastDateString(Integer.parseInt(txtDay.getText())));
		}
	}


	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
	}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource().equals(tblDateList.getSelectionModel())){
           loadProductList();
        }
    }
}
