package viewer;

import java.io.File;

import javax.swing.JTable;

import sqlite_databaseconnector.DatabaseConnector;

public class Controller {

	private Frame frame;
	private DatabaseConnector Interface;
	private Table_controller tablecontroller;
	
	
	public Controller(Frame pFrame, Table_controller pTablecontroller) {
		frame = pFrame;
		tablecontroller = pTablecontroller;
		Interface = new DatabaseConnector("", 0, "", "", "");
		
	}
	
	
	
	
	public void load(File dbfile) {
		Interface = new DatabaseConnector("", 0, dbfile.getPath(), "", "");
		
		
	}

	public void executesql(String pCommand) {
		Interface.executeStatement(pCommand);
		if(!(Interface.getErrorMessage()==null)) {
			frame.throwerror(Interface.getErrorMessage());
		}else {
			if(Interface.getCurrentQueryResult()==null) {
				frame.consoleoutput(pCommand);
			}else {
				tablecontroller.gettablemodel().settablecols(Interface.getCurrentQueryResult().getColumnNames(),tablecontroller.gettable());	
				
				
				tablecontroller.gettablemodel().setdataarray(Interface.getCurrentQueryResult().getData());
				
				
				tablecontroller.gettable().updateUI();
//				System.out.println(Interface.getCurrentQueryResult().getColumnNames()[1]);
				  }
			}		
		}
		
}
	
