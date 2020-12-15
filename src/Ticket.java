
public class Ticket {

	private String cod;
	private String descripcion;
	private String prioridad;
	private String problema;
	private String usuario;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Ticket(String cod, String descripcion, String prioridad, String problema, String usuario) {
		super();
		this.cod = cod;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.problema = problema;
		this.usuario = usuario;
	}
	
	
	
}
