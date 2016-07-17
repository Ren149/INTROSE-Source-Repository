package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame implements ChangeListener{

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private ProductListPanel productListPanel = new ProductListPanel(this);
	private StockPanel stockPanel = new StockPanel(this);
	private SalePanel salePanel = new SalePanel(this);
	
	public MainWindow() {
		setTitle("Farmacia Regine Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Sale", salePanel);
		tabbedPane.addTab("Stock", stockPanel);
		tabbedPane.addTab("Product List", productListPanel);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		tabbedPane.addChangeListener(this);
	}

	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(salePanel)) {
			salePanel.repaint();
		}
		if(e.getSource().equals(productListPanel)) {
			productListPanel.repaint();
		}
		if(e.getSource().equals(stockPanel)) {
			stockPanel.repaint();
		}
	}
	
	public void update() {
		productListPanel.update();
		stockPanel.update();
		salePanel.update();
	}
}