package packInterfazEuskoFlix;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import packEuskoFlix.CatalogoEtiquetas;
import packEuskoFlix.CatalogoUsuarios;

public class MostrarEtiquetas extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			contentPanel.add(textArea);
			CatalogoEtiquetas listaEti = CatalogoEtiquetas.getMiCEti();			
			//cargar etiquetas para mostrarlas en el textArea
			}
	}

}
