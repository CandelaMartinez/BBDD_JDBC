package aplicacionFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Clase224_AplicacionUniversal {

	public static void main(String[] args) {
		MarcoBBDD mimarco = new MarcoBBDD();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);

	}

}

class MarcoBBDD extends JFrame {

	public MarcoBBDD() {

		setBounds(300, 300, 700, 700);
		LaminaBBDD milamina = new LaminaBBDD();
		add(milamina);

	}

}

class LaminaBBDD extends JPanel {

	private JComboBox comboTablas;
	private JTextArea areaInfo;
	private Connection miConexion2;
	private FileReader entrada;
	private String datos[];
	

	public LaminaBBDD() {

		setLayout(new BorderLayout());

		comboTablas = new JComboBox();
		areaInfo = new JTextArea();

		obtenerTablas();

		// ..........................................................................JComboBox
		// responde a eventos
		comboTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombreTabla = (String) comboTablas.getSelectedItem();

				mostrarInfoTabla(nombreTabla);

			}
		});

		add(comboTablas, BorderLayout.NORTH);
		add(areaInfo, BorderLayout.CENTER);

	}

	// ...........................................................................conectar
	// a la bbdd
	public void conectarBBDD() {

		miConexion2=null;
		//ruta, nombre bbdd, usuario, contraseña,
		datos= new String[4];
		
		
		
		try {
			
			//leer un archivo externo
			entrada= new FileReader("D:/lenguajes/JAVA/eclipse/BBDD_JDBC/src/aplicacionFinal/datos.txt");
			
			
		}catch(IOException e1) {
			
			JOptionPane.showMessageDialog(this, "no se ha encontrado el archivo");
			
			//la busqueda la comienza desde la carpeta por defecto: documentos
			JFileChooser chooser = new JFileChooser();
			    
			//filtro para que se vean los tipo de archivo que quiero
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"archivos de texto", "txt");
			    
			chooser.setFileFilter(filter);
			
			   //argumento componente padre
			int returnVal = chooser.showOpenDialog(this);
			    
			//en el caso que aprete abrir, guarde alli la ruta del archivo elegido
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			  
				try {
					entrada= new FileReader(chooser.getSelectedFile().getAbsolutePath());
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
				
			    }
			
			
			
		}
			
			
		try {
			BufferedReader bf= new BufferedReader(entrada);
			
			//almacenar el contenido en un array
			for( int i=0; i<datos.length;i++) {
				
				datos[i]=bf.readLine();
			}
			
			/*for (String string : datos) {
				System.out.println(string);
			}*/
			

			miConexion2 = DriverManager.getConnection(datos[0], datos[2],datos[3]);
			
			
			entrada.close();

		
		}catch(Exception e) {
			
			e.printStackTrace();
		}
			
		

	}

	// .........................................................................carga
	// el JComboBox
	public void obtenerTablas() {

		conectarBBDD();

		ResultSet mirs = null;

		try {

			//miConexion2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila_es", "root", "");

			DatabaseMetaData datosBBDD = miConexion2.getMetaData();

			mirs = datosBBDD.getTables(datos[1], null, null, null);

			while (mirs.next()) {

				comboTablas.addItem(mirs.getString("TABLE_NAME"));

			}

		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this, "error en bloque obtener tablas");
			
		}

	}

	// .......................................................................muestra la informacion de la tabla seleccionada

	public void mostrarInfoTabla(String tabla) {

		ArrayList<String> campos = new ArrayList<String>();

		String consultaSQL = "SELECT * FROM " + tabla;

		try {
			areaInfo.setText("");
			
			Statement miSt = miConexion2.createStatement();

			ResultSet rs = miSt.executeQuery(consultaSQL);

			// almaceno los metadatos del resulset
			ResultSetMetaData rsBBDD = rs.getMetaData();

			// hasta que no haya mas columnas
			for (int i = 1; i <= rsBBDD.getColumnCount(); i++) {

				// almacena nombre de la columna en posicion i
				campos.add(rsBBDD.getColumnLabel(i));

			}

			//lee el primer registro del resulset
			while (rs.next()) {

				
				//me extraiga del arrayList el texto del  primer campo del primer registro del resulset
				for (String nombreCampo : campos) {

					//me saque el texto de ese primer campo del arrayList
					areaInfo.append( "  ---  "+ rs.getString(nombreCampo) );

				}

				areaInfo.append("\n");

			}

		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this, "error en bloque mostrar la informacion de las tablas");
		
		}

	}

}