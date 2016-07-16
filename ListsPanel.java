package ProjectFrontEnd;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;

public class ListsPanel extends JPanel {
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private ProductListPanel productListPanel = new ProductListPanel();
	
	public ListsPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		tabbedPane.add(productListPanel);
		add(tabbedPane, "cell 0 0,grow");
	}
}