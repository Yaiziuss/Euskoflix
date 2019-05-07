package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Euskoflix.FiltradoContenido;

import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class RecomendarPeli extends JDialog {
	private static int opcion; //0-> contenido 1-> Usuario 2-> peliculas
	private final JPanel contentPanel = new JPanel();
	private JLabel txtPorFavorIntroduzca;
	private JTextArea txtrIdusuario;
	
	public void setOp(int pNum) {
		opcion=pNum;
			
	}
	public int getOp() {
		return opcion;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecomendarPeli dialog = new RecomendarPeli();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecomendarPeli() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
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
		}
		contentPanel.setLayout(null);
		JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
		lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuskoflix.setForeground(Color.RED);
		lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblEuskoflix.setBounds(5, 5, 440, 36);
		contentPanel.add(lblEuskoflix);
		contentPanel.add(getTxtPorFavorIntroduzca());
		contentPanel.add(getTxtrIdusuario());
		{
			JButton okButton = new JButton("Recomendar");
			okButton.setBounds(149, 198, 122, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("Recomendar");
			getRootPane().setDefaultButton(okButton);
			okButton.addActionListener(new ActionListener(){ 
				@Override
				public void actionPerformed(ActionEvent e) {
					String idU = txtrIdusuario.getText();
					System.out.println(idU+" Id usuario 1");
					Recomendaciones r=new Recomendaciones();
					r.setVisible(false);
					r.setText(idU);
					System.out.println(opcion +" opcion");
					System.out.println(idU+" id usuario despues de mandarlo");
					r.setVisible(true);
				}
			});
		}
	}
	
	private JLabel getTxtPorFavorIntroduzca() {
		if (txtPorFavorIntroduzca == null) {
			txtPorFavorIntroduzca = new JLabel();
			txtPorFavorIntroduzca.setText("Por favor, introduzca su número de usuario");
			txtPorFavorIntroduzca.setVerticalAlignment(SwingConstants.TOP);
			txtPorFavorIntroduzca.setHorizontalAlignment(SwingConstants.CENTER);
			txtPorFavorIntroduzca.setBounds(42, 53, 372, 36);
			txtPorFavorIntroduzca.setFont(new Font("Lucida Sans", Font.BOLD, 14));
			txtPorFavorIntroduzca.setForeground(Color.RED);
			txtPorFavorIntroduzca.setBackground(Color.getColor(null));
			Border border = txtPorFavorIntroduzca.getBorder();
			Border margin = new EmptyBorder(10,10,10,10);
			txtPorFavorIntroduzca.setBorder(new CompoundBorder(border, margin));
			//txtPorFavorIntroduzca.se
			contentPanel.add(txtPorFavorIntroduzca);
		//	txtPorFavorIntroduzca.setColumns(10);
		}
		return txtPorFavorIntroduzca;
	}
	private JTextArea getTxtrIdusuario() {
		if (txtrIdusuario == null) {
			txtrIdusuario = new JTextArea();
			txtrIdusuario.setText("");
			txtrIdusuario.setBounds(170, 146, 80, 30);
			txtrIdusuario.addKeyListener(new KeyAdapter()
			{
			   public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(!(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/)))
			      {
			         //no hacer nada
			      }else{e.consume();}// ignorar el evento de teclado
			   }
			});
		}
		return txtrIdusuario;
	}
	
}
