package examen.parte1;

import java.util.List;

import examen.Utilidades;

public class MainCurso {

	public static void main(String[] args) {
		
		Master master1 = Utilidades.crearMaster();
		Master master2 = Utilidades.crearMaster();
		Master master3 = Utilidades.crearMaster();
		
		List<Master> listadoMasters = Utilidades.añadirMastersLista(master1, master2, master3);
		
		Utilidades.mostrarNombreMastersOficiales(listadoMasters);
	}
}
