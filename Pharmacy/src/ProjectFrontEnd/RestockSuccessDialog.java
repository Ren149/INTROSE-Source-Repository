package ProjectFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class RestockSuccessDialog extends JFrame implements ActionListener {

	JLabel lblPrompt = new JLabel();
	JButton btnOK = new JButton("OK");
	
	public RestockSuccessDialog(String productName, int quantity) {
		setResizable(false);
		setTitle("Restock Success");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new CardLayout(10, 10));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "name_43730872969978");
		panel.setLayout(new MigLayout("", "[213px]", "[15px][23px]"));
		panel.add(lblPrompt, "cell 0 0,alignx center,aligny top");
		
		lblPrompt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		if(quantity == 1) {
			lblPrompt.setText(quantity + " unit of " + productName + " added to the inventory.");			
		}
		else {
			lblPrompt.setText(quantity + " units of " + productName + " added to the inventory.");
		}
		

		panel.add(btnOK, "cell 0 1,alignx right,aligny top");
		
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnOK.addActionListener(this);
		btnOK.setBackground(new Color(51, 204, 0));

		getRootPane().setDefaultButton(btnOK);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOK)) {
			dispose();
		}
	}
}
