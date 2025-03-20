package examen.parte1;

public class CursoImpl implements Curso {

	private String nombre;
	private int codigo;
	private int cupo;
	public CursoImpl(String nombre, int codigo, int cupo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.cupo = cupo;
	}
	@Override
	public String getNombre() {
		return this.nombre;
	}
	@Override
	public int getCodigo() {
		return this.codigo;
	}
	@Override
	public int getCupo() {
		return this.cupo;
	}
}
