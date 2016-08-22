//MILESTONE
package ProjectFrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;

import ProjectBackEnd.LineItemManager;
import ProjectBackEnd.ReorderPointManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;

public class ReorderPointPanel extends JFrame implements ActionListener {

	private JLabel lblTitle = new JLabel("Adjust Reorder Point");
	private JLabel lblHighDemandProducts = new JLabel("High-Demand Products:");
	private JLabel lblLowDemandProducts = new JLabel("Low-Demand Products:");
	private JLabel lblPieces = new JLabel("pieces");
	private JLabel lblPieces_1 = new JLabel("pieces");
	private JTextField txtHighDemandReorderPoint;
	private JTextField txtLowDemandReorderPoint;
	private JButton btnAdjust = new JButton("Adjust");
	private JPanel panel = new JPanel();
	private Component verticalStrut = Box.createVerticalStrut(20);
	private LineItemManager lm = new LineItemManager();
	private ReorderPointManager rm = new ReorderPointManager();
	private String lowReorder = String.valueOf(rm.getLowReorderPoint());
	private String highReorder = String.valueOf(rm.getHighReorderPoint());
	private final JLabel lblHighDemandReorderPointError = new JLabel("");
	private final JLabel lblLowDemandReorderPointError = new JLabel("");

	public ReorderPointPanel() {
		setTitle("Adjust Reorder Point");
		getContentPane().setBackground(Color.WHITE);

		setBackground(new Color(255, 255, 255));
		setFont(new Font("Segoe UI", Font.PLAIN, 11));
		setBounds(100, 100, 350, 252);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(10, 10));

		getContentPane().add(panel, "panel");

		lblTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

		lblHighDemandProducts.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		txtHighDemandReorderPoint = new JTextField();
		txtHighDemandReorderPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtHighDemandReorderPoint.setColumns(3);
		txtHighDemandReorderPoint.setText(highReorder);

		lblPieces.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		lblHighDemandReorderPointError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblHighDemandReorderPointError.setBackground(Color.RED);

		lblLowDemandProducts.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));

		txtLowDemandReorderPoint = new JTextField();
		txtLowDemandReorderPoint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtLowDemandReorderPoint.setColumns(3);
		txtLowDemandReorderPoint.setText(lowReorder);

		lblLowDemandReorderPointError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		
		btnAdjust.setForeground(new Color(255, 255, 255));
		btnAdjust.setBackground(new Color(0, 204, 0));
		btnAdjust.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAdjust.addActionListener(this);

		lblPieces_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][]", "[][][][][][][][]"));
		panel.add(lblTitle, "cell 0 0 2 1");
		panel.add(lblHighDemandProducts, "cell 0 2,alignx trailing");
		panel.add(txtHighDemandReorderPoint, "flowx,cell 1 2");
		panel.add(lblPieces, "cell 1 2,alignx left");
		
		panel.add(lblHighDemandReorderPointError, "cell 1 3,alignx left");
		panel.add(lblLowDemandProducts, "cell 0 4,alignx trailing");
		panel.add(txtLowDemandReorderPoint, "flowx,cell 1 4,aligny top");
		
		panel.add(lblLowDemandReorderPointError, "cell 1 5");
		panel.add(verticalStrut, "cell 0 6");
		panel.add(btnAdjust, "cell 0 7 2 1,alignx right,aligny bottom");
		panel.add(lblPieces_1, "cell 1 4");

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private boolean allValidInputs() {
		boolean valid = true;
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtHighDemandReorderPoint.getText())) {
			lblHighDemandReorderPointError.setText("High demand products must have a reorder point..");
			txtHighDemandReorderPoint.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			try {
				if(Integer.parseInt(txtHighDemandReorderPoint.getText()) <= 0){
					lblHighDemandReorderPointError.setText("Reorder point must be greater than 0.");
					txtHighDemandReorderPoint.setBackground(Color.YELLOW);
					txtHighDemandReorderPoint.setText("");
					valid = false;
				}
				else {
					lblHighDemandReorderPointError.setText("");
					txtHighDemandReorderPoint.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblHighDemandReorderPointError.setText("Reorder point must be a positive whole number.");
				txtHighDemandReorderPoint.setBackground(Color.YELLOW);
				txtHighDemandReorderPoint.setText("");
				valid = false;
			}
		}
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txtLowDemandReorderPoint.getText())) {
			lblLowDemandReorderPointError.setText("Low demand products must have a reorder point..");
			txtLowDemandReorderPoint.setBackground(Color.YELLOW);
			valid = false;
		}
		else {
			try {
				if(Integer.parseInt(txtLowDemandReorderPoint.getText()) <= 0){
					lblLowDemandReorderPointError.setText("Reorder point must be greater than 0.");
					txtLowDemandReorderPoint.setBackground(Color.YELLOW);
					txtLowDemandReorderPoint.setText("");
					valid = false;
				}
				else {
					lblLowDemandReorderPointError.setText("");
					txtLowDemandReorderPoint.setBackground(Color.WHITE);
				}
			} catch(NumberFormatException e) {
				lblLowDemandReorderPointError.setText("Reorder point must be a positive whole number.");
				txtLowDemandReorderPoint.setBackground(Color.YELLOW);
				txtLowDemandReorderPoint.setText("");
				valid = false;
			}
		}
		
		pack();
		
		return valid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdjust)) {
			if(allValidInputs()) {
				rm.setHighReorderPoint(Integer.parseInt(txtHighDemandReorderPoint.getText()));
				rm.setLowReorderPoint(Integer.parseInt(txtLowDemandReorderPoint.getText()));
				dispose();
			}
		}
	}
}