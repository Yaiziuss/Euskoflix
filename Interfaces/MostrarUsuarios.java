package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Euskoflix.*;
import java.util.Map.Entry;
import javax.swing.SwingConstants;

public class MostrarUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarUsuarios dialog = new MostrarUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarUsuarios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				panel.add(btnVolver);
			}
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel tituloUsuCarg = new JLabel("Usuarios cargados");
			contentPanel.add(tituloUsuCarg);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
			DefaultTableModel modelo = new DefaultTableModel(); 
			JTable table = new JTable(modelo);			
			scrollPane.setViewportView(table);
			DefaultTableModel modelotabla;
			JTable tabla1;			
			ArrayList<Entry<Integer, ArrayList<Entry<Integer, Double>>>> listaEntriesUsuarios = CatalogoUsuarios.getMiCU().listaEntriesDeUsuarios();
			Object columnas[] = {"Id Usuario","Pelicula","Valoración"};

                        modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

                        tabla1=new JTable(modelotabla);
                        tabla1.setEnabled(false);
                        getContentPane().add(tabla1);
                        //for para separar el id de la valoracion de la peli y del id de esta
                        for(Entry<Integer, ArrayList<Entry<Integer, Double>>> entriesUsuario : listaEntriesUsuarios){
                            int idUsuario=entriesUsuario.getKey();
                            for(Entry<Integer, Double> par : entriesUsuario.getValue()) {
                                modelotabla.addRow(new Object[] {idUsuario,CatalogoPeliculas.getMiCPeli().getPelicula(par.getKey()).getNombre(),par.getValue()});
                            }
                        }
                        JScrollPane scroll11 = new JScrollPane(tabla1);
                        getContentPane().add(scroll11);


                    }
		}		
		}

}