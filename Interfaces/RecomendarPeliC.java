package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Euskoflix.*;

import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class RecomendarPeliC extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel txtPorFavorIntroduzca;
	private JTextArea txtrIdusuario;
	private JTabbedPane tabbedPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecomendarPeliC dialog = new RecomendarPeliC();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecomendarPeliC() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
		}
		contentPanel.setLayout(null);
		JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
		lblEuskoflix.setBounds(5, 0, 440, 36);
		lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuskoflix.setForeground(Color.RED);
		lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		contentPanel.add(lblEuskoflix);
		contentPanel.add(getTxtPorFavorIntroduzca());
		contentPanel.add(getTxtrIdusuario());
		{
			JButton okButton = new JButton("Recomendar");
			okButton.setBounds(149, 92, 122, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("Recomendar");
			getRootPane().setDefaultButton(okButton);
			contentPanel.add(getTabbedPane());
			okButton.addActionListener(new ActionListener(){ 
				@Override
				public void actionPerformed(ActionEvent e) {
					table=getTabbedPane();
					okButton.setVisible(false);
					rellenarTabla(table);
				}
			});
		}
		}
	}
	private void rellenarTabla(JTable table) {
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		{
			ArrayList<String> recom= new ArrayList<>();
		
			int usu=Integer.parseInt(txtrIdusuario.getText());
			recom= FiltradoContenido.getMiFiltro().seleccionar30MejoresPelisPara(usu);
			Usuario cu= CatalogoUsuarios.getMiCU().getUsuario(usu);
			if (cu==null) {
				JOptionPane.showMessageDialog(null,"El usuario no existe, por favor");
			} else {
			DefaultTableModel modelo = new DefaultTableModel(); 
			table.setRowSelectionAllowed(false);
			table.setColumnSelectionAllowed(false);
			table.setCellSelectionEnabled(false);
			scrollPane.setViewportView(table);
			
			DefaultTableModel modelotabla;
			JTable tabla1;
			
			Object columnas[] = {"Puntuación pelicula","Titulo Pelicula"};

			    modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

			    tabla1=new JTable(modelotabla);
			    tabla1.setEnabled(false);
			    getContentPane().add(tabla1);
                for(String puntuTitu: recom){
                	String[] par= puntuTitu.split("//");
                	modelotabla.addRow(new Object[] {par[0],par[1]});
			        }
			    JScrollPane scroll11 = new JScrollPane(tabla1);
			    getContentPane().add(scroll11);
			    }
		}
}
	
	private JLabel getTxtPorFavorIntroduzca() {
		if (txtPorFavorIntroduzca == null) {
			txtPorFavorIntroduzca = new JLabel();
			txtPorFavorIntroduzca.setBounds(5, 48, 346, 32);
			txtPorFavorIntroduzca.setText("Por favor, introduzca su número de usuario");
			txtPorFavorIntroduzca.setVerticalAlignment(SwingConstants.TOP);
			txtPorFavorIntroduzca.setHorizontalAlignment(SwingConstants.CENTER);
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
			txtrIdusuario.setBounds(365, 48, 80, 30);
			txtrIdusuario.setText("");
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
	private JTable getTabbedPane() {
		if (table == null) {
			table = new JTable();
			table.setBounds(12, 146, 415, 77);
		}
		return table;
	}
}
