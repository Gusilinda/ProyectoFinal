import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Login extends JFrame implements ActionListener {

	private String usuario;
	
	
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	JPanel miCapa;
	JLabel textoT;
	JLabel nombreT;
	JLabel correo;
	JLabel password;

	String corre;
	String passw;

	JTextField correoTF;
	JTextField passwordTF;

	JButton botonAcceder;
	JLabel mensaje;

	public Login() {
		this.setTitle("Login");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		miCapa = new JPanel();
		miCapa.setLayout(null);

		textoT = new JLabel("Bymatic");
		// x y ancho alto
		textoT.setBounds(10, 20, 300, 40);

		miCapa.add(textoT);

		correo = new JLabel("Correo: ");
		correo.setBounds(10, 70, 120, 30);
		miCapa.add(correo);

		correoTF = new JTextField();
		correoTF.setBounds(90, 70, 120, 30);
		miCapa.add(correoTF);
		corre = correoTF.getText();

		password = new JLabel("Password: ");
		password.setBounds(10, 170, 120, 30);
		miCapa.add(password);

		passwordTF = new JTextField();
		passwordTF.setBounds(90, 170, 120, 30);
		miCapa.add(passwordTF);
		passw = passwordTF.getText();

		// ---------------------------BOTONES--------------------------------

		botonAcceder = new JButton("Acceder");
		botonAcceder.setBounds(10, 270, 120, 30);
		miCapa.add(botonAcceder);

		botonAcceder.addActionListener(this);

		this.add(miCapa);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonAcceder) {
			String usuarioI = correoTF.getText();
			String passI = passwordTF.getText();
			Usuario u = null;

			try {
				Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bymatic",
						"root", "");
				PreparedStatement st = (PreparedStatement) conexion
						.prepareStatement("SELECT tipo FROM usuario WHERE correo = ? AND pass = ? ");

				st.setString(1, usuarioI);
				st.setString(2, passI);

				ResultSet rs = st.executeQuery();


				if (rs.next()) {
					// opcion 2 para usuarios (CrearTicket)
					u = new Usuario(rs.getInt(1));
					u.setCorreo(usuarioI);
					if (u.getTipo() == 2) {
						CrearTicket cTicket = new CrearTicket(usuarioI);
						cTicket.setVisible(true);
						setUsuario(usuarioI);
					
						
						this.dispose();
						conexion.close();
						
					} else if (u.getTipo() == 1) {
						// opcion 1 para admins (Vista Tickets)
						VistaTickets vTickets = new VistaTickets();
						vTickets.setVisible(true);
						this.dispose();
						conexion.close();
					}

				}

			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

		}

	}

}
