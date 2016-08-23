package ProjectFrontEnd;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.LineItemManager;
import ProjectBackEnd.ProductManager;
import ProjectBackEnd.SaleManager;
import net.miginfocom.swing.MigLayout;

public class SalesReport extends JFrame implements ActionListener, ChangeListener, KeyListener, ListSelectionListener {
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
	private JSpinner spnDate_1 = new JSpinner();
	private JSpinner spnDate_2 = new JSpinner();
	private JTable tblDateList = new JTable() {
		public boolean isCellEditable(int row, int column) {                
            return false;               
		}
	};
	private JTable tblProductList = new JTable() {
		public boolean isCellEditable(int row, int column) {                
            return false;               
		}
	};
	private JScrollPane scrDateList = new JScrollPane();
	private JScrollPane scrProductList = new JScrollPane();
	private JRadioButton rdbtnToday = new JRadioButton("Today");
	private JRadioButton rdbtnPast = new JRadioButton("Past");	
	private JRadioButton rdbtnCustom = new JRadioButton("Custom Range");
        
        //OTHER VARIABLES
        
        private ArrayList<Integer> prodIDList = new ArrayList<Integer>();
        private ArrayList<Integer> salesIDList = new ArrayList<Integer>();
        private ArrayList<Float> TotalSalesList = new ArrayList<Float>();
        
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
		
		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblTitle.setBackground(Color.WHITE);
		
		lblSalesReportTable.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));

		tblDateList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblDateList.getSelectionModel().addListSelectionListener(this);
		tblDateList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrDateList.setViewportView(tblDateList);
		
		tblProductList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tblProductList.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrProductList.setViewportView(tblProductList);
		
		rdbtnPast.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		rdbtnPast.setBackground(Color.WHITE);
		rdbtnPast.addActionListener(this);
		
		txtDay = new JTextField();
		txtDay.setDocument(new JTextFieldCharLimit(2));
		txtDay.setBackground(Color.WHITE);
		txtDay.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtDay.setText("7");
		txtDay.setColumns(3);
		txtDay.setEditable(false);
		txtDay.setEnabled(false);
		txtDay.addKeyListener(this);
		

		lblError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		rdbtnCustom.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		rdbtnCustom.setBackground(Color.WHITE);
		rdbtnCustom.addActionListener(this);

		salesReportDateSelectionRadioBtn.add(rdbtnPast);
		salesReportDateSelectionRadioBtn.add(rdbtnCustom);
		
		separator.setForeground(new Color(204, 204, 204));
		separator.setOrientation(SwingConstants.VERTICAL);

		lblSalesReportTable.setText("Sales report for today, " + getCurrentDateString());
		
		lblDays.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		lblError.setText("");
		
		lblTo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		
		lblTotalCashSales_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		
		lblTotalCashSalesValue_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblTotalCashSales_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		
		lblTotalCashSalesValue_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, - 1);
		Date date1_ini = calendar.getTime();
		Date date1_max = calendar.getTime();
                
                String minDateString = sm.getFirstSalesDate();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               
                Date minDate = new Date();     
                
				try {
					minDate = df.parse(minDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                long diff = Math.abs(date1_ini.getTime() - minDate.getTime());
                long diffDays = (diff / (24 * 60 * 60 * 1000)) + 1;
                
		int daysAfterEarliestSaleDate = (int) diffDays; //change this to get the number of days ago the earliest sale was made
		calendar.add(Calendar.DAY_OF_MONTH, - daysAfterEarliestSaleDate); 
		Date date1_min = calendar.getTime();
		
		calendar = Calendar.getInstance();
		Date date2_ini = calendar.getTime();
		Date date2_max = calendar.getTime();
		
		spnDate_1.setForeground(Color.WHITE);
		spnDate_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spnDate_1.setModel(new SpinnerDateModel(date1_ini, date1_min, date1_max, Calendar.DAY_OF_MONTH));
		spnDate_1.setEditor(new JSpinner.DateEditor(spnDate_1, new SimpleDateFormat("dd-MMM-yyyy").toPattern()));
		spnDate_1.setEnabled(false);
		spnDate_1.addChangeListener(this);
		
		spnDate_2.setForeground(Color.WHITE);
		spnDate_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spnDate_2.setModel(new SpinnerDateModel(date2_ini, null, date2_max, Calendar.DAY_OF_MONTH));
		spnDate_2.setEditor(new JSpinner.DateEditor(spnDate_2, new SimpleDateFormat("dd-MMM-yyyy").toPattern()));
		spnDate_2.setEnabled(false);
		spnDate_2.addChangeListener(this);
		
		salesReportMainPanel.setBackground(Color.WHITE);
		salesReportMainPanel.setLayout(new MigLayout("", "[][20px:20px][][grow]", "[][][][][][][grow][]"));
		salesReportMainPanel.add(lblTitle, "cell 0 0");
		salesReportMainPanel.add(lblSalesReportTable, "cell 2 0 2 1,aligny bottom");
		
		rdbtnToday.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		rdbtnToday.setBackground(Color.WHITE);
		rdbtnToday.addActionListener(this);
		rdbtnToday.setSelected(true);
		
		salesReportDateSelectionRadioBtn.add(rdbtnToday);
		
		salesReportMainPanel.add(rdbtnToday, "cell 0 1");
		salesReportMainPanel.add(scrDateList, "cell 2 1 1 6,growy");
		salesReportMainPanel.add(scrProductList, "cell 3 1 1 6,grow");
		salesReportMainPanel.add(rdbtnPast, "flowx,cell 0 2,alignx left");
		salesReportMainPanel.add(txtDay, "cell 0 2,alignx left");
		salesReportMainPanel.add(lblError, "cell 0 3,alignx left");
		salesReportMainPanel.add(rdbtnCustom, "cell 0 4");
		salesReportMainPanel.add(separator, "cell 1 0 1 8,alignx center,growy");
		salesReportMainPanel.add(lblDays, "cell 0 2,alignx left");
		salesReportMainPanel.add(spnDate_1, "flowx,cell 0 5");
		salesReportMainPanel.add(lblTo, "cell 0 5");
		salesReportMainPanel.add(spnDate_2, "cell 0 5");
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
	
	private boolean allValidInputs() {
		boolean valid = true;
		
		if(rdbtnPast.isSelected()) {
			if(StringUtils.isEmptyOrWhitespaceOnly(txtDay.getText())) {
				lblError.setText("Option requires an input.");
				txtDay.setBackground(Color.YELLOW);
				valid = false;
			}
			else {
				try {
					if(Integer.parseInt(txtDay.getText()) <= 0){
						lblError.setText("Day must be greater than 0.");
						txtDay.setBackground(Color.YELLOW);
						valid = false;
					}
					else {
						lblError.setText("");
						txtDay.setBackground(Color.WHITE);
					}
				} catch(NumberFormatException e) {
					lblError.setText("Day must be a positive whole number.");
					txtDay.setBackground(Color.YELLOW);
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	private void loadDateList() {
        float TotalCashSale1 = 0;

        Date today = new Date();
		DefaultTableModel dateListTableModel = new DefaultTableModel();
		dateListTableModel.setColumnIdentifiers(new String[] {"Date", "Cash Sales"});
		
		if(rdbtnToday.isSelected()) {
                    //TODAY 
                    String entrydate = "";
                    entrydate = new SimpleDateFormat("yyyy-MM-dd").format(today);

                    salesIDList = sm.getSalesIDList(entrydate);
                    for(int i = 0; i < salesIDList.size(); i++){
                        TotalSalesList.add(sm.getTotalSales(salesIDList.get(i)));
                        TotalCashSale1 += TotalSalesList.get(i);
                    }
                    dateListTableModel.addRow(new Object[] {entrydate,"P"+ String.format("%.2f", TotalCashSale1)});    
		}
		else if(rdbtnPast.isSelected()) {
			//PAST N DAYS
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(today);
                    int daysToDecrement = -1;
                    int N = Integer.parseInt(txtDay.getText());
	        
                    for(int i = 0; i < N; i++){
                        float cashSale = 0;
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
                        
                        dateListTableModel.addRow(new Object[] {entrydate,"P"+ String.format("%.2f",cashSale)});
                    }
		} else if(rdbtnCustom.isSelected()) {
                    
                String startDateString = new SimpleDateFormat("yyyy-MM-dd").format(spnDate_2.getValue());
                String endDateString =  new SimpleDateFormat("yyyy-MM-dd").format(spnDate_1.getValue());
                
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               
                Date startDate = new Date();
                Date endDate = new Date();
                
				try {
					startDate = df.parse(startDateString);
                                        endDate = df.parse(endDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}             

                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                int daysToDecrement = -1;
                
                String newDateString1 = df.format(startDate);
                String newDateString2 = df.format(endDate);
                
                do{
                	float cashSale = 0;
                	startDate = cal.getTime();
                    String entrydate = "";
                    entrydate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                    newDateString1 = df.format(startDate);
                    newDateString2 = df.format(endDate);
                	if(newDateString1.equals(newDateString2) != true){
                        cal.add(Calendar.DATE, daysToDecrement);
                	}
                		
                	salesIDList = sm.getSalesIDList(entrydate);
                    for(int j = 0; j < salesIDList.size(); j++){
                        TotalSalesList.add(sm.getTotalSales(salesIDList.get(j)));
                        cashSale += TotalSalesList.get(j);
                        TotalCashSale1 += TotalSalesList.get(j);                    
                    }
                    
                    dateListTableModel.addRow(new Object[] {entrydate,"P"+String.format("%.2f",cashSale)});
                }while(newDateString1.equals(newDateString2) != true);
                	
		}
		
        lblTotalCashSalesValue_1.setText("P" + String.format("%.2f",(TotalCashSale1)));

        tblDateList.setModel(dateListTableModel);
        
        DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
		rendererRight.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tblDateList.getColumnModel().getColumn(1).setCellRenderer(rendererRight);
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
                    
                    productListTableModel.addRow(new Object[] {prodName, QtySold, "P" + String.format("%.2f",cashSale)});
                    }
                }
	
        lblTotalCashSalesValue_2.setText("P" + String.format("%.2f",TotalCashSale2));

        tblProductList.setModel(productListTableModel);

        DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
		rendererRight.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tblProductList.getColumnModel().getColumn(1).setCellRenderer(rendererRight);
		tblProductList.getColumnModel().getColumn(2).setCellRenderer(rendererRight);
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
			spnDate_1.setEnabled(false);
			spnDate_2.setEnabled(false);
			lblError.setText("");
			txtDay.setBackground(Color.WHITE);
			txtDay.setText("7");
            loadDateList();
		}
		else if(rdbtnPast.isSelected()){
			txtDay.setEditable(true);
			txtDay.setEnabled(true);
			spnDate_1.setEnabled(false);
			spnDate_2.setEnabled(false);
			lblSalesReportTable.setText(getPastDateString(Integer.parseInt(txtDay.getText())));
			loadDateList();
		}
		else if(rdbtnCustom.isSelected()){
			txtDay.setEditable(false);
			txtDay.setEnabled(false);
			spnDate_1.setEnabled(true);
			spnDate_2.setEnabled(true);
			lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_2.getValue()));
			lblError.setText("");
			txtDay.setBackground(Color.WHITE);
			txtDay.setText("7");
			loadDateList();
			
			spnDate_1.addChangeListener(new ChangeListener() { 
				
				
				@Override
				public void stateChanged(ChangeEvent e) {
					if(rdbtnCustom.isSelected()){
					
						lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_2.getValue()));
					}
				}
			});
			
			spnDate_2.addChangeListener(new ChangeListener() { 
				
				@Override
				public void stateChanged(ChangeEvent e) {
					if(rdbtnCustom.isSelected()){
					
						lblSalesReportTable.setText("Sales report from " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_1.getValue()) + " to " + new SimpleDateFormat("MMMM dd yyyy").format(spnDate_2.getValue()));
					}
				}
			});
			
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(spnDate_1)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String newStart = df.format(spnDate_1.getValue());
			
			try {
				((SpinnerDateModel)spnDate_2.getModel()).setStart(df.parse(newStart));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(spnDate_2)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String newEnd = df.format(spnDate_2.getValue());
			
			try {
				((SpinnerDateModel)spnDate_1.getModel()).setEnd(df.parse(newEnd));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		loadDateList();
	}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource().equals(tblDateList.getSelectionModel())){
           loadProductList();
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(rdbtnPast.isSelected()){
			if(allValidInputs()) {
				lblSalesReportTable.setText(getPastDateString(Integer.parseInt(txtDay.getText())));
				loadDateList();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
