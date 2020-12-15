import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaTickets extends JFrame implements ActionListener {

	int cont;

	JPanel miCapa;
	String[] campos;
	Object[][] datos = new Object[][] {};
	JTable miTabla;
	JScrollPane scrollTabla;

	int cod_ticket;
	String nombre;
	String correo;
	String telefono;
	String problema;
	String descripcion;
	int prioridad;

	int cod_ticket1;
	String nombre1;
	String correo1;
	String telefono1;
	String problema1;
	String descripcion1;
	int prioridad1;

		
	DefaultTableModel modelo = new DefaultTableModel();

	public VistaTickets() {
		this.setTitle("Vista Tickets");
		this.setSize(800, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		miCapa = new JPanel();
		miCapa.setLayout(null);

		// encabezado de la tabla
		campos = new String[] { "id_ticket", "Nombre", "Correo", "Telefono", "Problema", "Descripcion", "Prioridad" };

		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bymatic",
					"root", "");

			// creamos la consulta para sacar los datos
			PreparedStatement stt = (PreparedStatement) conexion.prepareStatement(
					"SELECT ticket.cod_ticket, usuario.nombre,usuario.correo,usuario.telefono,ticket.problema,ticket.descripcion,ticket.prioridad FROM"
					+ " usuario RIGHT JOIN ticket ON usuario.cod_usu=ticket.cod_usu WHERE `cod_ticket`=1");
			ResultSet rss = stt.executeQuery();

			PreparedStatement stt1 = (PreparedStatement) conexion.prepareStatement(
					"SELECT ticket.cod_ticket, usuario.nombre,usuario.correo,usuario.telefono,ticket.problema,ticket.descripcion,ticket.prioridad FROM usuario RIGHT JOIN ticket ON usuario.cod_usu=ticket.cod_usu WHERE `cod_ticket`!=1");
			ResultSet rss1 = stt1.executeQuery();

			// creamos la consulta para sacer el numero maximo de tickets y usarlo despues
			PreparedStatement stt2 = (PreparedStatement) conexion.prepareStatement("select count(*) from ticket");
			ResultSet rss2 = stt2.executeQuery();

			while (rss.next()) {
				cod_ticket = rss.getInt("cod_ticket");
				nombre = rss.getString("nombre");
				correo = rss.getString("correo");
				telefono = rss.getString("telefono");
				problema = rss.getString("problema");
				descripcion = rss.getString("descripcion");
				prioridad = rss.getInt("prioridad");

			}

			while (rss1.next()) {
				cod_ticket1 = rss1.getInt("cod_ticket");
				nombre1 = rss1.getString("nombre");
				correo1 = rss1.getString("correo");
				telefono1 = rss1.getString("telefono");
				problema1 = rss1.getString("problema");
				descripcion1 = rss1.getString("descripcion");
				prioridad1 = rss1.getInt("prioridad");
			}

			datos = new Object[][] {

					// Añadimos a la tabla los tickets ticket
					{ cod_ticket, nombre, correo, telefono, problema, descripcion, prioridad },
					{ cod_ticket1, nombre1, correo1, telefono1, problema1, descripcion1, prioridad1 }

			};

			miTabla = new JTable(datos, campos);
			scrollTabla = new JScrollPane(miTabla);
			scrollTabla.setBounds(10, 10, 775, 280);
			miCapa.add(scrollTabla);

			this.add(miCapa);
			conexion.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
