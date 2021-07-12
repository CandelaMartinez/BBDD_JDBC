package metadatos;

import java.sql.*;

public class Clase222_InfoMetadatos {

	public static void main(String[] args) {
		 mostratInfoBBDD();

		mostrarInfoTablas();

	}

	static void mostratInfoBBDD() {

		Connection miConexion = null;

		try {

			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");

			// obtengo metadatos
			DatabaseMetaData datosBBDD = miConexion.getMetaData();

			System.out.println("GESTOR DE BBDD:   " + datosBBDD.getDatabaseProductName());

			System.out.println("VERSION DE GESTOR:  " + datosBBDD.getDatabaseProductVersion());

			System.out.println("DRIVER DE GESTOR " + datosBBDD.getDriverName() + " VERSION DE DRIVER: "
					+ datosBBDD.getDriverVersion());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				miConexion.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	static void mostrarInfoTablas() {

		Connection miConexion = null;
		ResultSet rs=null;
		ResultSet rs2=null;

		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");

			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//almaceno en un resulset la lista de tablas
			System.out.println("LISTA DE TABLAS ");
			
			//las tablas que empiezan con p: "p%"
			rs= datosBBDD.getTables(null, null, null, null);
			
			//columnas de la tabla
			
			while(rs.next()) {
				
				//le pido el nombre de las tablas 
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			System.out.println("*************************************************************************");
			System.out.println("CAMPOS DE PARTICIPANTES ");
			rs2= datosBBDD.getColumns(null, null, "participantes", null);
			
			//columnas de la tabla
			
			while(rs2.next()) {
				
				//le pido el nombre de las tablas 
				System.out.println(rs2.getString("COLUMN_NAME"));
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
		
		}finally {

			try {
				
				miConexion.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

}
