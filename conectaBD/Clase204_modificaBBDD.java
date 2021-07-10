package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Clase204_modificaBBDD {

	public static void main(String[] args) {
try {
			
			Connection miConexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");
			
			Statement miStatement= miConexion.createStatement();
			
			//insertar registros en una table
			//String instruccionSql="INSERT INTO CLIENTE (NOMBRE,CODIGO,CREDITO) VALUES ('PEPITO','300',45000)";
			
			//miStatement.executeUpdate(instruccionSql);
			
			//System.out.println("datos insertador correctamente");
			
			//actualizar informacion en una table
			
			//String instruccionSql2="UPDATE CLIENTE SET NOMBRE= 'Anita' WHERE CODIGO='300'";
			
			//miStatement.executeUpdate(instruccionSql2);
			
			//System.out.println("datos actualizados correctamente");
			
			//eliminar registros
			
			String instruccionSql3="DELETE FROM CLIENTE WHERE CODIGO='300'";
			
			miStatement.executeUpdate(instruccionSql3);
			
			System.out.println("datos eliminados correctamente");
			
			
		}catch(Exception e) {
			
			System.out.println("No conecta");
			e.printStackTrace();
			
			
		}
		
	}

}
