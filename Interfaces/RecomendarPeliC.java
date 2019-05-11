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
	private JTabbedPane tabbedPane;
	private JTable table;
	private JTextField txtrIdusuario;

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
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel);
		{
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
		{
			JButton okButton = new JButton("Recomendar");
			okButton.setBounds(239, 92, 122, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("Recomendar");
			getRootPane().setDefaultButton(okButton);
			
			txtrIdusuario = new JTextField();
			txtrIdusuario.setBounds(64, 90, 130, 26);
			contentPanel.add(txtrIdusuario);
			txtrIdusuario.setColumns(10);
			
			JPanel tablePanel = new JPanel();
			getContentPane().add(tablePanel);
			
			JScrollPane scrollPane = new JScrollPane();
			tablePanel.add(scrollPane);
			tablePanel.add(getTabbedPane());
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
                	par[1] = par[1].substring(0,par[1].length()-1);//quitamos comilla del final
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
			txtPorFavorIntroduzca.setBounds(37, 48, 346, 32);
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
	private JTable getTabbedPane() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
}
