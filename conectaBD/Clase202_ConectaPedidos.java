package conectaBD;
import java.sql.*;



public class Clase202_ConectaPedidos {

	public static void main(String[] args) {
		
		try {
			
			//...........................................1: crear la conexion..........................................
			Connection miConexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");
			
			//...........................................2: crear obj statement.........................................
			Statement miStatement= miConexion.createStatement();
			
			//...........................................3: ejecutar sql...............................................
			ResultSet miResulset= miStatement.executeQuery("SELECT * FROM CLIENTE");
			
			//...........................................2: recorrer resulset.........................................
			
			//mientras haya un registro mas en la siguiente posicion 
			while(miResulset.next()) {
				
				System.out.println(miResulset.getString("NOMBRE")+ " "+ miResulset.getString("CODIGO")+ " "+( miResulset.getDouble(3)*2));
				
				
			}
			
			
			
		}catch(Exception e) {
			
			System.out.println("No conecta");
			e.printStackTrace();
			
			
		}
		
		

	}

}
