package examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examen.parte1.Master;

public class Utilidades {

	private static final Logger logger = LoggerFactory.getLogger(Utilidades.class);
	private static Connection conn = null;
	private static final String URL_DB = "jdbc:oracle:thin:@localhost:1522:XE";
	private static final String USER_DB = "loprw";
	private static final String PASS_DB = "password";

	// METODOS CORRESPONDIENTES A LA PARTE 1 DEL EXAMEN.

	public static Master crearMaster() {

		String[] parte1 = { "Curso ", "Certificado ", "Diploma ", "Módulo " };
		String[] parte2 = { "Básico ", "Avanzado ", "Profesional ", "Especializado " };
		String parte3 = "de ";
		String[] parte4 = { "Informática ", "Matemáticas ", "Física ", "Química " };
		String[] parte5 = { "Aplicada.", "Cuántica.", "Estándar.", "Histórica." };

		StringBuilder sb = new StringBuilder();

		int azarParte1 = (int) (Math.random() * 4);
		int azarParte2 = (int) (Math.random() * 4);
		int azarParte4 = (int) (Math.random() * 4);
		int azarParte5 = (int) (Math.random() * 4);

		sb.append(parte1[azarParte1]);
		sb.append(parte2[azarParte2]);
		sb.append(parte3);
		sb.append(parte4[azarParte4]);
		sb.append(parte5[azarParte5]);

		String nombre = sb.toString();
		int codigo = (int) (Math.random() * 999999);
		int cupo = (int) (Math.random() * 50);
		boolean oficial = (Math.random() > 0.4 ? true : false);
		Master master = new Master(nombre, codigo, cupo, oficial);

		return master;
	}

	public static List<Master> añadirMastersLista(Master... masters) {

		List<Master> listado = Arrays.asList(masters);

		return listado;
	}

	public static void mostrarNombreMastersOficiales(List<Master> listado) {

		for (Master master : listado) {
			if (master.isOficial()) {
				logger.info(master.getNombre());
			}
		}
	}

	// METODOS CORRESPONDIENTES A LA PARTE 2 DEL EXAMEN.

	public static void leerLineasFichero(String direccion) {

		File fichero = new File(direccion);

		try (Scanner scan = new Scanner(fichero)) {

			scan.useDelimiter("\n");
			String linea = null;
			int contador = 1;

			while (scan.hasNext()) {

				linea = scan.next();
				logger.info("La línea " + contador++ + " ha sido leída. Su texto es: \n" + linea);

			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			logger.error(fnfe.getMessage());
		}
	}

	public static int contarLineas(String direccion) {

		int contador = 0;
		File fichero = new File(direccion);

		try (Scanner scan = new Scanner(fichero)) {

			scan.useDelimiter("\n");

			while (scan.hasNext()) {

				scan.next();
				contador++;
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			logger.error(fnfe.getMessage());
		}

		return contador;
	}

	public static int contadorPalabra(String direccion, String busqueda) {

		int contador = 0;
		File fichero = new File(direccion);

		try (Scanner scan = new Scanner(fichero)) {

			scan.useDelimiter("\n");
			String[] linea = null;

			while (scan.hasNext()) {

				linea = scan.next().split(" ");
				for (String palabra : linea) {
					if (palabra.toLowerCase().contains(busqueda)) {
						contador++;
					}
				}
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			logger.error(fnfe.getMessage());
		}

		return contador;
	}

	public static Map<Integer, String> guardarLineasFicheroEnMapa(String direccion) {

		File fichero = new File(direccion);
		Map<Integer, String> mapa = new LinkedHashMap<Integer, String>();
		int contador = 0;

		try (Scanner scan = new Scanner(fichero)) {

			scan.useDelimiter("\n");
			String linea = null;

			while (scan.hasNext()) {

				contador++;
				linea = scan.next();
				mapa.put(contador, linea);
				logger.info("Se guarda en el mapa la línea:\n" + linea + "\n en el valor de clave " + contador + ".");
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			logger.error(fnfe.getMessage());
		}

		return mapa;
	}

	public static void mostrarLineaATravesMapa(String direccion, String palabraBusqueda) {

		Map<Integer, String> mapa = Utilidades.guardarLineasFicheroEnMapa(direccion);

		Set<Entry<Integer, String>> entrySet = mapa.entrySet();

		for (Entry<Integer, String> entry : entrySet) {
			String[] array = entry.getValue().split(" ");
			for (String palabra : array) {
				if (palabra.toLowerCase().contains(palabraBusqueda)) {
					logger.info("La palabra " + palabraBusqueda + " aparece en la línea " + entry.getKey() + ".");
				}
			}
		}
	}

	public static void mostrarSumaClaveYCaracteres(String direccion) {

		Map<Integer, String> mapa = Utilidades.guardarLineasFicheroEnMapa(direccion);
		Set<Entry<Integer, String>> entrySet = mapa.entrySet();
		String letras = "abcdefghijklmnñopqrstuvwxyzáéíóú,;.: ";

		for (Entry<Integer, String> entry : entrySet) {
			int suma = 0;
			Set<Character> stringSet = new LinkedHashSet<Character>();
			suma += entry.getKey();
			String linea = entry.getValue();
			for (int i = 0; i < linea.length(); i++) {
				String letra = String.valueOf(linea.charAt(i));
				stringSet.add(linea.charAt(i));
			}
			suma += stringSet.size();
			logger.debug("Tamaño de stringSet: " + stringSet.size());
			logger.info("Para la línea " + entry.getKey()
					+ ", el valor de la suma de su clave y del total de sus caracteres es " + suma + ".");
		}
	}
	
	// METODOS CORRESPONDIENTES A LA PARTE 3 DEL EXAMEN.
	
	public static Connection crearConexion() {
		
		try {
			conn = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
			if (conn != null) {
				logger.debug("Conexión correcta.");
			}
		} catch (SQLException sqle) {
			logger.error("Error al crear la conexión: " + sqle.getMessage());
		}

		return conn;
	}
	
	public static void cerrarConexion(Connection connection) {

		try {
			connection.close();
		} catch (SQLException sqle) {
			logger.error("Error al cerrar la conexión: " + sqle.getMessage());
			;
		}
	}
	
	public static void consultarTodo(Connection conexion) {
		
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT id, nombre, tipo, precio, stock FROM TB_Productos";
		
		try {
			st = conexion.createStatement();
			rs = st.executeQuery(query);

			StringBuilder sb = new StringBuilder();
			
			logger.debug((rs.next()?"Lee linea.":"No lee linea."));
			
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				logger.debug(nombre);
				String tipo = rs.getString("tipo");
				double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
			
				sb.append("Id: "+ id + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Precio: " + precio
					+ ", Stock: " + stock + ".\n");
				
				
				System.out.println("Id: "+ id + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Precio: " + precio
						+ ", Stock: " + stock + ".\n");
				
				logger.debug("Registro leído correctamente");
			}
			
			logger.info(sb.toString());
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage());
		}
	}
	
	public static void mostrarPrecioMedioYProductosDisponibles(Connection conexion) {
		
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT tipo, ROUND(AVG(precio), 2) AS preciomedio, SUM(stock) AS productodisponible "
		+ " FROM TB_Productos GROUP BY tipo";
		
		try {
			st = conexion.createStatement();
			rs = st.executeQuery(query);

			StringBuilder sb = new StringBuilder();
			
			while (rs.next()) {
				
				String tipo = rs.getString("tipo");
				double media = rs.getDouble("PRECIOMEDIO");
				int suma = rs.getInt("PRODUCTODISPONIBLE");
			
				sb.append("Tipo: " + tipo + ", Media de precio: " + media + ", Productos totales: " + suma + ".\n");
				
				logger.debug("Registro leído correctamente");
			}
			
			logger.info(sb.toString());
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage());
		}		
	}
}
