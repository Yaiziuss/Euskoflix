package packInterfazEuskoFlix;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import packEuskoFlix.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarEtiquetas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

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
			JLabel tituloEtiCarg = new JLabel("Etiquetas cargadas");
			contentPanel.add(tituloEtiCarg);
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
				ArrayList<String> le = CatalogoEtiquetas.getMiCEti().getParejitasFelices();
				Object columnas[] = {"Nº de apariciones","Comentario"};

				    modelotabla = new DefaultTableModel(columnas,0); //0 son las filas

				    tabla1=new JTable(modelotabla);
				    getContentPane().add(tabla1);
				    String[] id;
				    //for para separar el id del nombre de la peli
				    for(int i=0;i<le.size();i++){
				    	id = le.get(i).split(" ");
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
			          getContentPane().add(scroll11);
				    }
			}
		}
	}
