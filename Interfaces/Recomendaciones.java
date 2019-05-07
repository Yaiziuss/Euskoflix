package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import Euskoflix.*;


public class Recomendaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int usu;
	private int op;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Recomendaciones dialog = new Recomendaciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void setText (String elTexto) {
		 JLabel aux= new JLabel();
		 aux.setText(elTexto);
		 String aux1=aux.getText();
		 usu=Integer.parseInt(aux1);
	   }
	 

	/**
	 * Create the dialog.
	 */
	public Recomendaciones() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		System.out.println(op + "id opcion en recomendaciones");
		System.out.println(usu + " id usuario recomendacionesS");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setLayout(null);
			JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
			lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
			lblEuskoflix.setForeground(Color.RED);
			lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			lblEuskoflix.setBounds(5, 5, 440, 36);
			contentPanel.add(lblEuskoflix);
			rellenarTabla();
		}
	}
	private void rellenarTabla() {
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
					ArrayList<String> recom= new ArrayList<>();
					RecomendarPeli p= new RecomendarPeli();
					//int op=p.getOp();
					//System.out.println(op);
					//System.out.println(usu);
					if (op==1) {
						recom= FiltradoContenido.getMiFiltro().seleccionar30MejoresPelisPara(usu);
					} else if (op==0) {
						recom= FiltradoPersona.getMiFPer().seleccionar30MejoresPelisPara(usu);
					}
				DefaultTableModel modelo = new DefaultTableModel(); 
				JTable table = new JTable(modelo);
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
                    	String[] punt= puntuTitu.split("//");
                    	String titulo="";
                    	for(int i=1;i<punt.length;i++){
				    		titulo+=" "+punt[i];
				    	}
				        modelotabla.addRow(new Object[] {punt,titulo});
				        }
				    JScrollPane scroll11 = new JScrollPane(tabla1);
				    getContentPane().add(scroll11);
				    }
	}

}
