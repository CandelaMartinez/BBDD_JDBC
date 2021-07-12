package aplicacionFinal;

import javax.swing.*;
import javax.swing.filechooser.*;

public class Clase227_JFileChooser {

	public static void main(String[] args) {
		
		Marco mimarco= new Marco();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//la busqueda la comienza desde la carpeta por defecto: documentos
		JFileChooser chooser = new JFileChooser();
		    
		//filtro para que se vean los tipo de archivo que quiero
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"archivos de texto", "txt");
		    
		chooser.setFileFilter(filter);
		
		   //argumento componente padre
		int returnVal = chooser.showOpenDialog(mimarco);
		    
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		      
			System.out.println("You chose to open this file: " +
		           
					//obtiene el nombre del archivo elegido
					chooser.getSelectedFile().getName());
			
					//devuelve la ruta del archivo elegido
			
			System.out.println("ruta " +	chooser.getSelectedFile().getAbsolutePath());
		    }
	}

}
class Marco extends JFrame{
	
	public Marco() {
		
		setBounds(300,300,200,200);
		setVisible(true);
	}
	
}