package conectaBD;

import java.sql.*;


public class Clase205_consultaPreparada {

	public static void main(String[] args) {
		
		try {
			
			//1 - crear coneccion
			Connection miConexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");
		
			//2 -preparar consulta
			PreparedStatement miSentencia= miConexion.prepareStatement("SELECT NOMBRE FROM CLIENTE WHERE CODIGO=? AND CREDITO=?");
			
			//3- establecer los parametros de consulta
			miSentencia.setString(1, "124");
			
			miSentencia.setDouble(2, 24000);
			
			//4- ejecutar y recorrer la consulta
			ResultSet rs= miSentencia.executeQuery();
			
			while (rs.next()) {
				
				System.out.println(rs.getString(1));
				
			}
			
			rs.close();
		
			//reuso de consulta
			
			System.out.println("******************************************************************");
			
			miSentencia.setString(1, "123");
			
			miSentencia.setDouble(2, 24000);
			
			//4- ejecutar y recorrer la consulta
			rs= miSentencia.executeQuery();
			
			while (rs.next()) {
				
				System.out.println(rs.getString(1));
				
			}
			
			rs.close();
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		

	}

}
