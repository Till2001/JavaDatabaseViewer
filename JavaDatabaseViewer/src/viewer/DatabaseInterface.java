package viewer;
import sqlite_databaseconnector.DatabaseConnector;

public class DatabaseInterface {
	
	private DatabaseConnector DbC;
	
	public DatabaseInterface() {
		 DbC = new DatabaseConnector("", 0, "", "", "");
		 
	}
	
	
	
	
}
