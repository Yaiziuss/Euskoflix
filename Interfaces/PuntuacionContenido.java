package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PuntuacionContenido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PuntuacionContenido dialog = new PuntuacionContenido();
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);
		
		JLabel lblIdUsuario = new JLabel("ID Usuario");
		lblIdUsuario.setBounds(64, 135, 72, 16);
		contentPanel.add(lblIdUsuario);
		
		JLabel lblIdPelicula = new JLabel("ID Pelicula");
		lblIdPelicula.setBounds(296, 135, 72, 16);
		contentPanel.add(lblIdPelicula);
		
		JTextArea textAreaPeli = new JTextArea();
		textAreaPeli.setColumns(1);
		textAreaPeli.setBounds(296, 163, 72, 16);
		contentPanel.add(textAreaPeli);
		
		JTextArea textAreaUsu = new JTextArea();
		textAreaUsu.setBounds(64, 163, 72, 16);
		contentPanel.add(textAreaUsu);
		
		JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
		lblEuskoflix.setForeground(Color.RED);
		lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblEuskoflix.setBounds(130, 6, 197, 36);
		contentPanel.add(lblEuskoflix);
		
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(165, 204, 117, 29);
		contentPanel.add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int idU = Integer.valueOf(textAreaUsu.getText());
					int idP = Integer.valueOf(textAreaPeli.getText());
					
				}
		});
		
		JLabel lblMensaje = new JLabel("<html>Por favor, introduce un ID correspondiente <br> a un usuario y a una película</html>");
		lblMensaje.setVerticalAlignment(SwingConstants.TOP);
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(104, 49, 301, 71);
		lblMensaje.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBackground(Color.getColor(null));
		Border border = lblMensaje.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		lblMensaje.setBorder(new CompoundBorder(border, margin));
		contentPanel.add(lblMensaje);


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
