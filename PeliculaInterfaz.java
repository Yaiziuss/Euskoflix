package Euskoflix;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
//import Euskoflix_menu;
public class PeliculaInterfaz {

	private JFrame frame;
	private JLabel lblEuskoflix;
	private JPanel panel;
	private JButton btnOk;
	private JTextArea textArea;
	//private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeliculaInterfaz window = new PeliculaInterfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PeliculaInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().add(getLblEuskoflix(), BorderLayout.NORTH);
		frame.getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
		JScrollPane scrollPane = new JScrollPane(getTextArea()); 
		scrollPane.setViewportView(getTextArea());
		scrollPane.setBounds(30, 30, 300, 200);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
	}

	private JLabel getLblEuskoflix() {
		if (lblEuskoflix == null) {
			lblEuskoflix = new JLabel("EUSKOFLIX");
			lblEuskoflix.setForeground(Color.RED);
			lblEuskoflix.setFont(new Font("Century Schoolbook L", Font.PLAIN, 27));
			lblEuskoflix.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEuskoflix;
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnOk());
		}
		return panel;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
		}
		return btnOk;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea(100,2);
			textArea.setToolTipText("");
		//	JScrollPane scrollPane = new JScrollPane(textArea); 
			textArea.setDropMode(DropMode.ON_OR_INSERT);
			textArea.setForeground(Color.LIGHT_GRAY);
			String[][] table=new String[100][2];
		//	textArea.setFont(new Font("FreeMono", textArea.getFont().getStyle(), textArea.getFont().getSize()));
		//	textArea.setDropMode(DropMode.INSERT_ROWS);
			textArea.insert("hello", 2);
		}
		return textArea;
	}
	
	public int getTextAreaTabSize() {
		return getTextArea().getTabSize();
	}
	public void setTextAreaTabSize(int tabSize) {
		getTextArea().setTabSize(tabSize);
	}
}
