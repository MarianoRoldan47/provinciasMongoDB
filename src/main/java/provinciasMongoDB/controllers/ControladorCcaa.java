package provinciasMongoDB.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;

import provinciasMongoDB.model.Ccaa;
import provinciasMongoDB.model.Provincia;

public class ControladorCcaa {
	private ControladorCcaa instance = null;
	 
	 public ControladorCcaa getInstance() {
		 if(instance == null) {
			 instance = new ControladorCcaa();
		 }
		 return instance;
	 }	 
	 
	 public static List<Ccaa> getAllProvincias() {
	        System.out.println("Obteniendo todas las ccaa de la colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = ConnectionManager.getColeccionCCAA().find();
	        MongoCursor<Document> cursor = fi.iterator();

	        List<Ccaa> allCcaa = new ArrayList<Ccaa>();
	        try {
	            while(cursor.hasNext()) {
	            	allCcaa.add(documentToCcaa(cursor.next()));
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return allCcaa;
	    }
	 
	public static Ccaa getCcaa(int id) {
	        FindIterable<Document> fi = ConnectionManager.getColeccionCCAA().find();
	        MongoCursor<Document> cursor = fi.iterator();

	        try {
	            while(cursor.hasNext()) {
	            	Ccaa a = documentToCcaa(cursor.next());
	            	if(Integer.parseInt(a.getCode()) == id) {
	            		return a;
	            	}
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return null;
	    }
	 
	    /**
	     * 
	     * @param doc
	     * @return
	     */
	    public static Ccaa documentToCcaa(Document doc) {
	    	Ccaa ccaa = new Ccaa();
	    	ccaa.setCode(doc.getString("code"));
	    	ccaa.setLabel(doc.getString("label"));
	    	return ccaa;
	    }
	    
	    public static void updateDocument (Ccaa p) {
	        try {
	        	Document query = new Document().append("code",  "" + p.getCode());
	        	Bson update = Updates.combine(Updates.set("label", "" + p.getLabel()));
	        	ConnectionManager.getColeccionCCAA().updateOne(query, update);
	        	
	        	JOptionPane.showMessageDialog(null, "Ccaa guardada correctamente");
	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        
	    }
}
