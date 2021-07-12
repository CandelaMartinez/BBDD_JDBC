package procAlmacenados;
import java.sql.*;

import javax.swing.JOptionPane;

public class Clase219_ActualizaParticipantes {

	public static void main(String[] args) {
		
		
		String poblacion=JOptionPane.showInputDialog("introduce nueva poblacion");
		
		double nDorsal=Double.parseDouble(JOptionPane.showInputDialog("introduce DORSAL del participante al que le quieres cambiar la poblacion"));

		
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");
			
			CallableStatement miSentencia= miConexion.prepareCall("{call ACTUALIZA_POBLACION(?,?)}");
			
			//le paso los parametros
			miSentencia.setString(1, poblacion);
			miSentencia.setDouble(2, nDorsal);
			
		
			
			
			//no necesito un resulset porque no quiero leer. devuelve boolean
			miSentencia.execute();
			
		System.out.println("actualizacion ok");
			
			
		}catch(Exception e) {
			
			
			
			
		}
		
		

	}

}
