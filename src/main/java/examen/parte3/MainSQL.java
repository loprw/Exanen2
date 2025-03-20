package examen.parte3;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examen.Utilidades;

public class MainSQL {
    
    public static void main(String[] args) {
		
    	Connection conn = Utilidades.crearConexion();
    	
    	Utilidades.consultarTodo(conn);
    	Utilidades.mostrarPrecioMedioYProductosDisponibles(conn);
    	
    	Utilidades.cerrarConexion(conn);
	}
}
