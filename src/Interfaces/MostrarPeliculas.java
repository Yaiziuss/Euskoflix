package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Euskoflix.*;
import javax.swing.JTable;
import javax.swing.SwingConstants;
public class MostrarPeliculas extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarPeliculas dialog = new MostrarPeliculas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarPeliculas() {
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel tituloPeliCarg = new JLabel("Películas cargadas");
				panel.add(tituloPeliCarg);
				tituloPeliCarg.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
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
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				DefaultTableModel modelo = new DefaultTableModel(); 
				JTable table = new JTable(modelo);
				scrollPane.setViewportView(table);
				ArrayList<String> lp=CatalogoPeliculas.getMiCPeli().getParejitasFelices();
				DefaultTableModel modelotabla;
				JTable tabla1;
				
				Object columnas[] = {"ID","Película"};

				    modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

				    tabla1=new JTable(modelotabla);
				    tabla1.setEnabled(false);
				    add(tabla1);
				    String[] id;
				    //for para separar el id del nombre de la peli
				    for(int i=0;i<lp.size();i++){
				    	id = lp.get(i).split(" ");
				    	String nombre="";
				    	//for que junta el nombre de las pelis, ya uqe con el split separa en trozos, con todos los espacios
				    	for(int j=1;j<id.length;j++){
				    		nombre+=" "+id[j];
				    	}
				    	//para quitar la comilla final
				    	nombre = nombre.substring(0,nombre.length()-1);
				        modelotabla.addRow(new Object[] {id[0],nombre});
				        }
				    JScrollPane scroll11 = new JScrollPane(tabla1);
				    JScrollPane scroll12 = new JScrollPane(tabla1);
			          add(scroll11); 
			          add(scroll12);
				    }
				    //CONTADORLISTA es para agregar n Filas, y esa n lo define un contador que va sumando 1, cada vez que 
				    //se agrega una nueva persona (o sea, cada vez que se presiona el jbutton)

				    
			}
		}
		{
			contentPanel.setVisible(true);
		}
	}
