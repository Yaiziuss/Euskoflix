package Euskoflix;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Euskoflix_menu {

	private JFrame frame;
	private JPanel panel;
	private JButton btnOk;
	private JComboBox comboBox;
	private JLabel lblEuskoflix;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Euskoflix_menu window = new Euskoflix_menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Euskoflix_menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getComboBox().addItem("Elija opción");
		getComboBox().addItem("Ver películas");
		getComboBox().addItem("Ver valoraciones");
		getComboBox().addItem("Ver usuarios");
		frame.getContentPane().add(getLblEuskoflix(), BorderLayout.NORTH);
		frame.getContentPane().add(getPanel_1(), BorderLayout.CENTER);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getBtnOk());
		}
		return panel;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.setVerticalAlignment(SwingConstants.BOTTOM);
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnOk.setBackground(new Color(169, 169, 169));
		}
		return btnOk;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(154, 95, 147, 24);
			comboBox.setEditable(true);
		}
		return comboBox;
	}
	private JLabel getLblEuskoflix() {
		if (lblEuskoflix == null) {
			lblEuskoflix = new JLabel("EUSKOFLIX");
			lblEuskoflix.setForeground(Color.RED);
			lblEuskoflix.setFont(new Font("Century Schoolbook L", Font.PLAIN, 27));
			lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEuskoflix;
	}
	public String getLblEuskoflixText() {
		return getLblEuskoflix().getText();
	}
	public void setLblEuskoflixText(String text) {
		getLblEuskoflix().setText(text);
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getComboBox());
		}
		return panel_1;
	}
}
