package packInterfazEuskoFlix;
import packEuskoFlix.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class MostrarDatosMenu extends javax.swing.JFrame {

	private JFrame frmCargaDeDatos;
	private JPanel panel;
	private final JButton btnOk = new JButton("OK");
	private JButton btnSalir;
	private JLabel lblEuskoflix;
	private JComboBox cboxVer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarDatosMenu window = new MostrarDatosMenu();
					window.frmCargaDeDatos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frmCargaDeDatos.setTitle("Carga de datos");
		frmCargaDeDatos.setBounds(100, 100, 450, 300);
		frmCargaDeDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frmCargaDeDatos.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtenemos la opción del combobox
				int index = cboxVer.getSelectedIndex();
				//
				if(index == 0){JOptionPane.showMessageDialog(null,"Selecciona otra opción, por favor");}
				else if(index == 1){ 
					MostrarPeliculas ventana = new MostrarPeliculas();
					ventana.setVisible(true);
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
		lblEuskoflix.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		frmCargaDeDatos.getContentPane().add(lblEuskoflix, BorderLayout.NORTH);
		
		cboxVer = new JComboBox();
		frmCargaDeDatos.getContentPane().add(cboxVer, BorderLayout.CENTER);
		cboxVer.addItem("Elige una opción");
		cboxVer.addItem("Ver peliculas cargadas");
		cboxVer.addItem("Ver valoraciones cargadas");
		cboxVer.addItem("Ver usuarios cargados");
	}

}
