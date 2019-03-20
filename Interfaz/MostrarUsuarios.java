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
			ArrayList<ArrayList<String>> le = CatalogoUsuarios.getMiCU().listaValoracionesUsuarios();
			Object columnas[] = {"Id Pelicula","Id Usuario","Valoración"};

			    modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

			    tabla1=new JTable(modelotabla);
			    tabla1.setEnabled(false);
			    getContentPane().add(tabla1);
			    ArrayList<String> aux;
			    String[] id;
			    //for para separar el id de la valoracion de la peli y del id de esta
			    for(int i=0;i<le.size();i++){
			    	aux=le.get(i);
			    	for (int k=1; k<aux.size();k++) {
			    		id = aux.get(k).split(" ");
				    	String nombre="";
				    	String valoracion="";
				    	//for que junta el id de las pelis y el id del usuario con la valoracion, ya que con el split separa en trozos, con todos los espacios
			    	for(int j=1;j<id.length;j++){
			    		nombre+=" "+id[j];
			    		valoracion+=" "+" "+id[j];
			    		
			    	}
			    
			    	//para quitar la comilla final
			    	nombre = nombre.substring(0,nombre.length()-1);
			        modelotabla.addRow(new Object[] {id[0],nombre,valoracion});
			        }
			    }
			    JScrollPane scroll11 = new JScrollPane(tabla1);
		          getContentPane().add(scroll11);
			
			
			}
		}		
		}

}