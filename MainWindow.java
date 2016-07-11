import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private ProductListPanel productListPanel = new ProductListPanel();
	
	public MainWindow() {
		setTitle("Farmacia Regine Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tabbedPane.addTab("Product List", productListPanel);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
