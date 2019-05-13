package Interfaces;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Euskoflix.*;
import javax.swing.JTextField;
import java.awt.GridLayout;


public class RecomendarPeliU extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txtPorFavorIntroduzca;
	private JTable tabla1;
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
	public RecomendarPeliU() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel);
			JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
			panel.add(lblEuskoflix);
			lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
			lblEuskoflix.setForeground(Color.RED);
			lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			panel.add(getTxtPorFavorIntroduzca());
			{
				txtrIdusuario = new JTextField();
				panel.add(txtrIdusuario);
				txtrIdusuario.setColumns(10);
			}
			{
				JButton recomendarButton = new JButton("Recomendar");
				panel.add(recomendarButton);
				recomendarButton.setActionCommand("Recomendar");
				getRootPane().setDefaultButton(recomendarButton);
				{
					JButton btnVolver = new JButton("Volver");
					//btnVolver.setVisible(false);
					btnVolver.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
					panel.add(btnVolver);
				}
				{
					JLabel lblTusPelculas = new JLabel("Tus 30 Películas aparecerán aquí abajo");
					lblTusPelculas.setForeground(Color.red);
					panel.add(lblTusPelculas);
				}
				panel.add(getTabbedPane());
				recomendarButton.addActionListener(new ActionListener(){ 
					@Override
					public void actionPerformed(ActionEvent e) {
						tabla1=getTabbedPane();
						recomendarButton.setVisible(false);
						rellenarTabla(tabla1);
					}
				});
			}
		}
		{
			{
			}
		}
	}
	private void rellenarTabla(JTable table) {
		JScrollPane scrollPane = new JScrollPane();
		//getContentPane().add(scrollPane);
		{
			ArrayList<String> recom= new ArrayList<>();
			int usu=Integer.parseInt(txtrIdusuario.getText());
			Usuario cu= CatalogoUsuarios.getMiCU().getUsuario(usu);
			if (cu==null) {
				JOptionPane.showMessageDialog(null,"El usuario no existe");
			} else {
				recom= FiltradoPersona.getMiFPer().seleccionar30MejoresPelisPara(usu);
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
			txtPorFavorIntroduzca.setText("Por favor, introduzca su número de usuario");
			txtPorFavorIntroduzca.setVerticalAlignment(SwingConstants.TOP);
			txtPorFavorIntroduzca.setHorizontalAlignment(SwingConstants.CENTER);
			txtPorFavorIntroduzca.setFont(new Font("Lucida Sans", Font.BOLD, 14));
			txtPorFavorIntroduzca.setForeground(Color.RED);
			txtPorFavorIntroduzca.setBackground(Color.getColor(null));
			Border border = txtPorFavorIntroduzca.getBorder();
			Border margin = new EmptyBorder(10,10,10,10);
			txtPorFavorIntroduzca.setBorder(new CompoundBorder(border, margin));
		//	txtPorFavorIntroduzca.setColumns(10);
		}
		return txtPorFavorIntroduzca;
	}
	private JTable getTabbedPane() {
		if (tabla1 == null) {
			tabla1 = new JTable();
		}
		return tabla1;
	}
}