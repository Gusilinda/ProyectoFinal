import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class CrearTicket extends JFrame implements ActionListener {

	Login login = new Login();
	Usuario u = new Usuario();

	JPanel miCapa;
	JLabel fecha;
	JTextField fechaTF;
	JLabel problema;
	JComboBox<String> problemaCB;
	JLabel descripcion;
	JTextArea descripcionTA;

	String fech;
	String descri;
	String email;
	int cod_usu;

	JButton botonS;
	JButton botonAny;

	public CrearTicket(String correo) {
		email = correo;
		this.setTitle("Ticket");
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		miCapa = new JPanel();
		miCapa.setLayout(null);

		fecha = new JLabel("Introduce la fecha en formato dd/mm/aaaa: ");
		fecha.setBounds(10, 70, 400, 30);
		miCapa.add(fecha);

		fechaTF = new JTextField();
		fechaTF.setBounds(500, 70, 120, 30);
		miCapa.add(fechaTF);
		fech = fechaTF.getText();

		problema = new JLabel("Selecciona un Problema: ");
		problema.setBounds(10, 170, 400, 30);
		miCapa.add(problema);

		problemaCB = new JComboBox<String>();
		problemaCB.addItem("Selecciona una opcion");
		problemaCB.addItem("1. Problema con servidor");
		problemaCB.addItem("2. Problema con internet");
		problemaCB.addItem("3. Problema con el correo");
		problemaCB.addItem("4. Problema con el equipo");
		problemaCB.setBounds(500, 170, 270, 40);
		miCapa.add(problemaCB);

		descripcion = new JLabel("Introduce una descripcion: ");
		descripcion.setBounds(10, 270, 400, 30);
		miCapa.add(descripcion);

		descripcionTA = new JTextArea();
		descripcionTA.setBounds(500, 270, 200, 80);
		miCapa.add(descripcionTA);
		descri = descripcionTA.getText();

		// ---------------------- B O T O N E S ---------------------------
		botonS = new JButton("Salir");
		botonS.setBounds(500, 400, 80, 40);
		miCapa.add(botonS);

		botonAny = new JButton("Añadir");
		botonAny.setBounds(300, 400, 80, 40);
		miCapa.add(botonAny);

		botonS.addActionListener(this);
		botonAny.addActionListener(this);

		this.add(miCapa);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonAny) {

			// Cogemos los datos introducidos
			String descripcionI = descripcionTA.getText();
			String fechaI = fechaTF.getText();

			String prio;
			int prioridad;
			prio = (String) problemaCB.getSelectedItem();
			prioridad = problemaCB.getSelectedIndex();
			
			// creamos la prioridad a la vez que seleccionamos el valor
			String problema = prio.substring(2).trim();

			try {
				Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bymatic",
						"root", "");

				// sacamos el id del ususario
				PreparedStatement stt = (PreparedStatement) conexion
						.prepareStatement("SELECT cod_usu FROM usuario WHERE correo = ?");
				stt.setString(1, email);

				ResultSet rss = stt.executeQuery();

				if (rss.next()) {

					u = new Usuario(rss.getInt(1));
					u.setCod_usu(rss.getInt(1));
				}

				PreparedStatement st = (PreparedStatement) conexion.prepareStatement(
						"INSERT into ticket (problema, prioridad, descripcion, email, fecha, cod_usu) VALUES ('"
								+ problema + "','" + prioridad + "', '" + descripcionI + "', '" + email + "', '"
								+ fechaI + "', '" + u.getCod_usu() + "') ");

				int rs = st.executeUpdate();
				conexion.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

		}

		if (e.getSource() == botonS) {
			System.exit(EXIT_ON_CLOSE);
		}
	}

}
