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
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Euskoflix.*;
public class MostrarPeliculas extends JDialog {

	private JPanel contentPanel = new JPanel();

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
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(true);
					}
				});
				panel.add(btnVolver);
			}
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel tituloPeliCarg = new JLabel("Películas cargadas");
			contentPanel.add(tituloPeliCarg);
		}
		{
			JTextArea textArea = new JTextArea(150,1);
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setVerticalScrollBarPolicy(
			                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(1000, 1000));
			textArea.setEditable(false);
			//scrollPane.
			contentPanel.add(scrollPane);
			contentPanel.setVisible(true);
			CargadorDatos cd= new CargadorDatos();
			cd.cargarDatos();
			/*Set<Integer> listaPelis =CatalogoPeliculas.getMiCPeli().getListaPeliculas();					
			for(Integer a : listaPelis){
				  
				}
			}	*/
			
			
			ArrayList<String> lp=CatalogoPeliculas.getMiCPeli().getParejitasFelices();
			for (int i=0; i<lp.size();i++) {
				String a= lp.get(i);
				textArea.insert(a+"\n",i);
		}
		}
	}
	}
