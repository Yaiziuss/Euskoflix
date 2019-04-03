package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.SystemColor;

public class PuntuacionContenido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private SpringLayout sl_contentPanel;
	private JComboBox comboBox_1;
	private JTextPane txtpnPorFavorSeleccione;
	private JLabel lblEuskoflix;
	private JButton btnCalcular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PuntuacionContenido dialog = new PuntuacionContenido();
			dialog.setBackground(Color.black);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PuntuacionContenido() {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(Color.red);
		setBounds(100, 100, 450, 300);
		contentPanel.setLayout(new BorderLayout());
		getContentPane().setBackground(SystemColor.textHighlight);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			sl_contentPanel = new SpringLayout();
			contentPanel.setLayout(sl_contentPanel);
			lblEuskoflix = new JLabel("EUSKOFLIX");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEuskoflix, 5, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEuskoflix, 5, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblEuskoflix, 445, SpringLayout.WEST, contentPanel);
			lblEuskoflix.setForeground(Color.RED);
			lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblEuskoflix);
		}
		getComboBox().addItem("Id Usuario");
		contentPanel.add(getComboBox());
		getComboBox_1().addItem("Id película");
		contentPanel.add(getComboBox_1());
		contentPanel.add(getTxtpnPorFavorSeleccione());
		contentPanel.add(getBtnCalcular());
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox, 43, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, comboBox, 180, SpringLayout.WEST, contentPanel);
			comboBox.setEditable(false);
		}
		return comboBox;
	}
	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox_1, 77, SpringLayout.EAST, getComboBox());
			sl_contentPanel.putConstraint(SpringLayout.EAST, comboBox_1, -27, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, getComboBox(), 1, SpringLayout.NORTH, comboBox_1);
		}
		return comboBox_1;
	}
	private JTextPane getTxtpnPorFavorSeleccione() {
		if (txtpnPorFavorSeleccione == null) {
			txtpnPorFavorSeleccione = new JTextPane();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, getComboBox_1(), 54, SpringLayout.SOUTH, txtpnPorFavorSeleccione);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, txtpnPorFavorSeleccione, 17, SpringLayout.SOUTH, lblEuskoflix);
			sl_contentPanel.putConstraint(SpringLayout.WEST, txtpnPorFavorSeleccione, 58, SpringLayout.WEST, contentPanel);
			txtpnPorFavorSeleccione.setFont(new Font("Lucida Sans", Font.BOLD, 14));
			txtpnPorFavorSeleccione.setEditable(false);
			txtpnPorFavorSeleccione.setText("Por favor, seleccione un Id correspondiente \n a un usuario y a una película");
			getTxtpnPorFavorSeleccione().setForeground(Color.RED);
			getTxtpnPorFavorSeleccione().setBackground(Color.getColor(null));
			}
		return txtpnPorFavorSeleccione;
	}
	private JButton getBtnCalcular() {
		if (btnCalcular == null) {
			btnCalcular = new JButton("Calcular");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, getComboBox_1(), -18, SpringLayout.NORTH, btnCalcular);
			btnCalcular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnCalcular, -10, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnCalcular, -173, SpringLayout.EAST, contentPanel);
		}
		return btnCalcular;
	}
}
