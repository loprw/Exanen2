package examen.parte1;

public class Master extends CursoImpl{

	private boolean oficial;

	public Master(String nombre, int codigo, int cupo, boolean oficial) {
		super(nombre, codigo, cupo);
		this.oficial = oficial;
	}

	public boolean isOficial() {
		return oficial;
	}
}
