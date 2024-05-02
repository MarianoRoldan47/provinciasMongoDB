package provinciasMongoDB.controllers;

import java.util.List;


import provinciasMongoDB.model.Provincia;


public class DatosDeTabla {

	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"code", "Label", "Parent_code"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla() {
		// Obtengo todas las personas
		List<Provincia> estudiantes = (List<Provincia>) ControladorProvincia.getAllProvincias();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[estudiantes.size()][3];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < estudiantes.size(); i++) {
			Provincia e = estudiantes.get(i);
			datos[i][0] = e.getCode();
			datos[i][1] = e.getLabel();
			datos[i][2] = e.getParent_code();
		}
		
		return datos;
	}
	
	
}
