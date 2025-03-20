package examen.parte2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examen.Utilidades;

public class MainFichero {
	
    private static final Logger logger = LoggerFactory.getLogger(MainFichero.class);

	public static void main(String[] args) {

		String direccion = "C:\\Users\\Tardes\\Desktop\\FicheroEjercicio2.txt";
		Utilidades.leerLineasFichero(direccion);
		
		int numLineas = Utilidades.contarLineas(direccion);
		logger.info("El número de líneas del archivo es: " + numLineas + ".");
		
		String palabra = "java";
		int numPalabra = Utilidades.contadorPalabra(direccion, palabra);
		logger.info("El número de veces que aparace la palabra " + palabra + " es: " + numPalabra + ".");
		
		Utilidades.guardarLineasFicheroEnMapa(direccion);
		
		Utilidades.mostrarLineaATravesMapa(direccion, palabra);
		
		Utilidades.mostrarSumaClaveYCaracteres(direccion);
	}

}
