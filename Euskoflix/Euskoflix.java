package Euskoflix;

import Interfaces.MostrarDatosMenu;
import Interfaces.SplashScreen;

public class Euskoflix {
	public static void main(String[] args) { 
		SplashScreen sc = new SplashScreen();
        CargadorDatos.cargarDatos();
        sc.setVisible(false);
        MostrarDatosMenu mdm = new MostrarDatosMenu();
    }
}
