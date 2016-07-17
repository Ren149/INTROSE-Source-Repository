package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private ProductListPanel productListPanel = new ProductListPanel();
	private StockPanel stockPanel = new StockPanel();
	private SalePanel salePanel = new SalePanel();
	
	public MainWindow() {
		setTitle("Farmacia Regine Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//SALE PANEL
		
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Sale", salePanel);
		
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		//PRODUCT LIST PANEL
		
		//STOCK PANEL
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Product List", productListPanel);
		tabbedPane.addTab("Stock", stockPanel);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(e.getSource().equals(salePanel))
				{
					salePanel.repaint();
				}
				if(e.getSource().equals(productListPanel))
				{
					salePanel.repaint();
				}
				if(e.getSource().equals(stockPanel))
				{
					stockPanel.repaint();
				}
				
			}
		});
	}
	
	
}