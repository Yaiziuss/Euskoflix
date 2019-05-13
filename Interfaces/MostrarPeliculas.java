package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import Euskoflix.*;
import java.util.Map.Entry;
import javax.swing.JTable;
import javax.swing.SwingConstants;
public class MostrarPeliculas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6070401505046866545L;
	private JPanel contentPanel = new JPanel();
	//private JTable table;

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
		setLocationRelativeTo(null);
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
				ArrayList<Entry<Integer,Pelicula>> lp=CatalogoPeliculas.getMiCPeli().getIdsYPeliculas();
				DefaultTableModel modelotabla;
				JTable tabla1;
				
				Object columnas[] = {"ID","Película"};

                                modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

                                tabla1=new JTable(modelotabla);
                                tabla1.setEnabled(false);
                                add(tabla1);
                                
                                //for para separar el id del nombre de la peli
                                for(Entry<Integer,Pelicula> par: lp){ //.substring(0,par[1].length()-1)
									modelotabla.addRow(new Object[] {par.getKey(),par.getValue().getNombre() });
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
