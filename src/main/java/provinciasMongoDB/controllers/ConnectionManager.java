package provinciasMongoDB.controllers;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class ConnectionManager {

	private static MongoCollection<Document> conexionCCAA = null;
	private static MongoCollection<Document> conexionProvincias = null;
	
	
	public static MongoCollection<Document> getColeccionCCAA () {
		// Si es la primera vez que accedemos a la conexión, debemos instanciarla
		if (conexionCCAA == null) {
			int port_no = 27017;
	        String host_name = "localhost", db_name = "ComunidadesProvinciasPoblaciones", 
	        		db_coll_name = "ccaa";
	        
	        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
	        MongoClientURI uri = new MongoClientURI(client_url);
	 
	        // Conectando y obteniendo un cliente.
	        MongoClient mongo_client = new MongoClient(uri);
	 
	        // Obteniendo un enlace a la base de datos.
	        MongoDatabase db = mongo_client.getDatabase(db_name);
	 
	        // Obteniendo la colección de la base de datos
	        MongoCollection<Document> coll = db.getCollection(db_coll_name);
	        
	        conexionCCAA = coll;
		}
		
		return conexionCCAA;
	}
	
	public static MongoCollection<Document> getColeccionProvincias () {
		// Si es la primera vez que accedemos a la conexión, debemos instanciarla
		if (conexionProvincias == null) {
			int port_no = 27017;
	        String host_name = "localhost", db_name = "ComunidadesProvinciasPoblaciones", 
	        		db_coll_name = "provincias";
	        
	        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
	        MongoClientURI uri = new MongoClientURI(client_url);
	 
	        // Conectando y obteniendo un cliente.
	        MongoClient mongo_client = new MongoClient(uri);
	 
	        // Obteniendo un enlace a la base de datos.
	        MongoDatabase db = mongo_client.getDatabase(db_name);
	 
	        // Obteniendo la colección de la base de datos
	        MongoCollection<Document> coll = db.getCollection(db_coll_name);
	        
	        conexionProvincias = coll;
		}
		
		return conexionProvincias;
	}
}
