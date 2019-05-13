package Interfaces;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarDatosMenu extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmCargaDeDatos;
	private JPanel panel;
	private final JButton btnOk = new JButton("OK");
	private JButton btnSalir;
	private JLabel lblEuskoflix;
	private JComboBox<String> cboxVer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					MostrarDatosMenu window = new MostrarDatosMenu();
					window.frmCargaDeDatos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the application.
	 */
	public MostrarDatosMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCargaDeDatos = new JFrame();
		frmCargaDeDatos.setTitle("Menú Principal");
		frmCargaDeDatos.setBounds(100, 100, 450, 300);
		frmCargaDeDatos.setLocationRelativeTo(null);
		frmCargaDeDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frmCargaDeDatos.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtenemos la opción del combobox
				int index = cboxVer.getSelectedIndex();
				//
				if(index== 0){JOptionPane.showMessageDialog(null,"Seleccione una opción, por favor");
			} else if(index == 1){ 
					MostrarPeliculas ventana = new MostrarPeliculas();
					ventana.setVisible(true);
				} else if (index == 3) {
					MostrarUsuarios vu= new MostrarUsuarios();
					vu.setVisible(true);
				} else if (index == 2) {
					MostrarEtiquetas ve= new MostrarEtiquetas();
					ve.setVisible(true);
				} else if (index == 4) {
					String[] options = {"Contenido", "Usuarios", "Peliculas", "Menu principal"};
					int sel = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Catalogo de modelos de recomendación", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[3]);
					if(sel==0) {
						RecomendarPeliC rp= new RecomendarPeliC();
						rp.setVisible(true);
					} else if(sel==1) {
						RecomendarPeliU rp= new RecomendarPeliU();
						rp.setVisible(true);
					} else if (sel==2) {
						JOptionPane.showMessageDialog(null,"Opción en mantenimiento. Sentimos las molestias");
					} else {
						
					}
				}
			}
		});
		panel.add(btnOk);
		btnOk.addActionListener(cboxVer);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnSalir);
		
		lblEuskoflix = new JLabel("EUSKOFLIX");
		lblEuskoflix.setForeground(Color.RED);
		lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		frmCargaDeDatos.getContentPane().add(lblEuskoflix, BorderLayout.NORTH);
		
		cboxVer = new JComboBox<String>();
		frmCargaDeDatos.getContentPane().add(cboxVer, BorderLayout.CENTER);
		cboxVer.addItem("Elige una opción");
		cboxVer.addItem("Ver películas cargadas");
		cboxVer.addItem("Ver valoraciones cargadas");
		cboxVer.addItem("Ver usuarios cargados");
		cboxVer.addItem("¡Recomiendame unas películas!");
		frmCargaDeDatos.setVisible(true);
	}

}