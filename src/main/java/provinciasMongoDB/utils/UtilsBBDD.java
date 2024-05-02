package provinciasMongoDB.utils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class UtilsBBDD {

	
	public static void consultarTabla (Connection conn, String tabla) 
			throws SQLException{
		
		Statement s = (Statement) conn.createStatement(); 		
		ResultSet rs = s.executeQuery ("select * from " + tabla);
		
		// A través del objeto ResultSetMetaData obtenemos información sobre las características de los campos que 
		// posee el conjunto de registros que hemos obtenido con la consulta. Gracias a eso podemos hacer una visualización
		// enriquecida del contenido del objeto ResultSet
		ResultSetMetaData rsmd= rs.getMetaData();			

		// Impresión en pantalla de los tipos de las columnas que forman el resultado del ResultSet
		System.out.println("\n-------------------------------------------------------------");
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnTypeName(i) + "\t");
		}
		System.out.println("\n-------------------------------------------------------------");

		// Impresión en pantalla de las etiquetas de nombre de las columnas del objeto ResultSet
		System.out.println("\n-------------------------------------------------------------");
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnLabel(i) + "\t");
		} 
		System.out.println("\n-------------------------------------------------------------");
		
		// Recorrido del ResultSet, diferenciando entre los tipos de datos que pueden tener las columnas
		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("INT")) {
					System.out.print(rs.getInt(rsmd.getColumnLabel(i)) + "\t");
				}
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("VARCHAR")) {
					System.out.print(rs.getString(rsmd.getColumnLabel(i)) + "\t");
				}
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("DATETIME")) {
					System.out.print(rs.getDate(rsmd.getColumnLabel(i)) + "\t");
				}
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("TINYINT")) {
					System.out.print(rs.getBoolean(rsmd.getColumnLabel(i)) + "\t");
				}
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("FLOAT")) {
					System.out.print(rs.getFloat(rsmd.getColumnLabel(i)) + "\t");
				}
				
			}
			System.out.println("\n");			
		}

		rs.close();
		s.close();
	}
	
	/**
	 * Pasar la fecha del usuario en formato dd/MM/yyyy y parsearla para introducirla en la tabla
	 * @param fechaUsuario
	 * @return
	 */
	public static String insertarFechaParseada(String fechaUsuario) {
		
		if (fechaUsuario.trim().equals("")) {
			return null;
		}
		
		SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = sdfEntrada.parse(fechaUsuario);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdfSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdfSalida.format(date);
	}
	
	public static String obtenerFechaParseada(String fechaBaseDeDatos) {
		SimpleDateFormat sdfEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		try {
			date = sdfEntrada.parse(fechaBaseDeDatos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdfSalida = new SimpleDateFormat("dd/MM/yyyy");

		return sdfSalida.format(date);
	}
	
	/**
	 * Comprueba si el id es valido o no en una tabla
	 * @param id Id para saber si es valido en esa tabla
	 * @param tabla Nombre de la tabla para comprobar el id
	 * @param conn 
	 * @return true si el id esta en la tabla / false si el id no esta en la tabla
	 * @throws SQLException
	 */
	public static boolean idValidoDeOtraTabla(int id, String tabla, Connection conn) throws SQLException {

		Statement s = (Statement) conn.createStatement();

		ResultSet rs = s.executeQuery("select id from " + tabla + " where id='" + id + "'");

		return rs.next();
	}
	
	public static boolean isFloat(String str) {
		
		try {
			Float.parseFloat(str);
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El valor del saldo o del limite no es un numero float");
			return false;
		}
	}
	
	public static boolean isInteger(String str) {
		
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean isDouble(String str) {
		
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean isFechaValida(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String strFechaNacimiento = str;

		Date fechaNacimiento = null;
		try {
			fechaNacimiento = sdf.parse(strFechaNacimiento);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean isValorValidoConMatches(String valorAComprobar, String expresionRegular) {
		return valorAComprobar.matches(expresionRegular);
	}
	
	public static JDialog crearJDialog( JPanel panel, String titulo) {
		JDialog dialogo = new JDialog();
		// El usuario no puede redimensionar el diálogo
		dialogo.setResizable(true);
		// título del díalogo
		dialogo.setTitle("Gestion Proveedor");
		// Introducimos el panel creado sobre el diálogo
		dialogo.setContentPane(panel);
		// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que deben y el lugar adecuado
		dialogo.pack();
		// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
		dialogo.setModal(true);
		dialogo.setBounds(0, 0, 500, 300);
		// Centro el di�logo en pantalla
		dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
		
		// Muestro el di�logo en pantalla
		dialogo.setVisible(true);
		
		return dialogo;
}
	
}
