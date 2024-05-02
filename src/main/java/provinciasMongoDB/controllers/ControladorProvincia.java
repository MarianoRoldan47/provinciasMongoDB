package provinciasMongoDB.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import provinciasMongoDB.model.Provincia;


public class ControladorProvincia {
	 private ControladorProvincia instance = null;
	 
	 public ControladorProvincia getInstance() {
		 if(instance == null) {
			 instance = new ControladorProvincia();
		 }
		 return instance;
	 }	 
	 
	 public static List<Provincia> getAllProvincias() {
	        System.out.println("Obteniendo todas las ccaa de la colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = ConnectionManager.getColeccionProvincias().find();
	        MongoCursor<Document> cursor = fi.iterator();

	        List<Provincia> allProvincia = new ArrayList<Provincia>();
	        try {
	            while(cursor.hasNext()) {
	            	allProvincia.add(documentToProvincia(cursor.next()));
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return allProvincia;
	    }
	 
	public static Provincia getProvincia(int id) {
	        FindIterable<Document> fi = ConnectionManager.getColeccionProvincias().find();
	        MongoCursor<Document> cursor = fi.iterator();

	        try {
	            while(cursor.hasNext()) {
	            	Provincia a = documentToProvincia(cursor.next());
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
	    public static Provincia documentToProvincia(Document doc) {
	    	Provincia provincia = new Provincia();
	    	provincia.setParent_code(doc.getString("parent_code"));
	    	provincia.setCode(doc.getString("code"));
	    	provincia.setLabel(doc.getString("label"));
	    	return provincia;
	    }
	    
	    public static void updateDocument (Provincia p) {
	        try {
	        	Document query = new Document().append("code",  "" + p.getCode());
	        	Bson update = Updates.combine(Updates.set("label", "" + p.getLabel()), Updates.set("parent_code", "" + p.getParent_code()));
	        	ConnectionManager.getColeccionProvincias().updateOne(query, update);
	        	
	        	JOptionPane.showMessageDialog(null, "Provincia guardada correctamente");
	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        
	    }
}
