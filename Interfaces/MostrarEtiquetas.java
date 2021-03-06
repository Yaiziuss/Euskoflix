package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Euskoflix.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map.Entry;
import javax.swing.SwingConstants;

public class MostrarEtiquetas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -138878762775141998L;
	private final JPanel contentPanel = new JPanel();
	//private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarEtiquetas dialog = new MostrarEtiquetas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarEtiquetas() {
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
		}
		{
			JLabel tituloEtiCarg = new JLabel("Etiquetas cargadas");
			tituloEtiCarg.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(tituloEtiCarg, BorderLayout.NORTH);
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
				table.setRowSelectionAllowed(false);
				table.setColumnSelectionAllowed(false);
				table.setCellSelectionEnabled(false);
				scrollPane.setViewportView(table);
				
				DefaultTableModel modelotabla;
				JTable tabla1;
				ArrayList<Entry<String,Integer>> le = CatalogoEtiquetas.getMiCEti().obtenerEtiquetasYApariciones();
				Object columnas[] = {"Comentario","Nº de apariciones"};

				    modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

				    tabla1=new JTable(modelotabla);
				    tabla1.setEnabled(false);
				    getContentPane().add(tabla1);
                    for(Entry<String,Integer> par: le){
				        modelotabla.addRow(new Object[] {par.getKey(),par.getValue()});
				        }
				    JScrollPane scroll11 = new JScrollPane(tabla1);
				    getContentPane().add(scroll11);
				    }
			}
		}
	}