//MILESTONE
package ProjectFrontEnd;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProjectBackEnd.BatchManager;
import ProjectBackEnd.ProductManager;
import net.miginfocom.swing.MigLayout;

public class DiscontinueDialog extends JFrame implements ActionListener {

	private JLabel lblPrompt = new JLabel();
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private Component verticalStrut = Box.createVerticalStrut(20);

	private JLabel lblRemoveSuccess = new JLabel("Batch removed.");

	private ProductManager pm = new ProductManager();
	private BatchManager bm = new BatchManager();

	private int productID;

	public DiscontinueDialog(int productID) {
		setResizable(false);
		setTitle("Confirm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);

		getContentPane().setLayout(new CardLayout(10, 10));

		JPanel panel = new JPanel();
		getContentPane().add(panel, "PANEL");
		panel.setLayout(new MigLayout("", "[][]", "[][][]"));
		panel.add(lblPrompt, "cell 0 0,alignx center,aligny top");

		String productName = pm.getProductName(productID);
		lblPrompt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		lblPrompt.setText("Discontinue " + productName + " from the inventory?");

		panel.add(verticalStrut, "cell 0 1");

		panel.add(btnOK, "cell 0 2,alignx right,aligny top");

		btnOK.setBackground(new Color(51, 204, 0));
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnOK.addActionListener(this);

		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnCancel.addActionListener(this);

		lblRemoveSuccess.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		getRootPane().setDefaultButton(btnOK);

		panel.add(btnCancel, "cell 1 2");

		this.productID = productID;

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOK)) {
			bm.emptyBatches(productID);
			pm.setDiscontinued(productID, true);
			dispose();
		} else if (e.getSource().equals(btnCancel)) {
			dispose();
		}
	}
}
