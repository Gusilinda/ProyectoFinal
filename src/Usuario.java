
public class Usuario {


	private String nombre;
	private String telefono;
	private String correo;
	private String pass;
	private int tipo;
	private int cod_usu;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	

	public int getCod_usu() {
		return cod_usu;
	}
	

	public void setCod_usu(int cod_usu) {
		this.cod_usu = cod_usu;
	}

	public Usuario(String nombre, String telefono, String correo, String pass, int tipo) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.pass = pass;
		this.tipo = tipo;
	}

	public Usuario(int tipo) {
		super();
		this.tipo = tipo;
	}

	
	
	public Usuario() {
		super();
	}

	
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + ", pass=" + pass
				+ ", tipo=" + tipo + "]";
	}

}
