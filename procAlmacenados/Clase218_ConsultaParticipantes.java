package procAlmacenados;
import java.sql.*;

//usar un procedimiento almacenado en mi base de datos

public class Clase218_ConsultaParticipantes {

	public static void main(String[] args) {
		
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");
			
			CallableStatement miSentencia= miConexion.prepareCall(" {call MUESTRA_PARTICIPANTES}");
			
			ResultSet rs= miSentencia.executeQuery();
			
			while(rs.next()) {
				
				System.out.println(rs.getString(1)+ " "+ rs.getString(2)+" "+ rs.getString(3));
				
			}
			
			rs.close();
			
		}catch(Exception e) {
			
			
			
			
		}
		
		
		
		

	}

}
