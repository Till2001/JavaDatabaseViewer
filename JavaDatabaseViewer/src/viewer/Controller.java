package viewer;

import java.io.File;

import javax.swing.JTable;

import sqlite_databaseconnector.DatabaseConnector;

public class Controller {

	private Frame frame;
	private DatabaseConnector Interface;
	private JTable table;
	
	
	public Controller(Frame pFrame, JTable pTable) {
		frame = pFrame;
		table = pTable;
		Interface = new DatabaseConnector("", 0, "", "", "");
		
	}
	
	
	
	
	public void load(File dbfile) {
		Interface = new DatabaseConnector("", 0, dbfile.getPath(), "", "");
//		frame.settablenames(Interface.getCurrentQueryResult().getColumnNames());
//		frame.settabledata(Interface.getCurrentQueryResult().getData());
	}

	public void executesql(String pCommand) {
		Interface.executeStatement(pCommand);
		if(!(Interface.getErrorMessage()==null)) {
			frame.throwerror(Interface.getErrorMessage());
		}else {
			if(Interface.getCurrentQueryResult()==null) {
				frame.consoleoutput(pCommand);
			}else {
				  if(!(Interface.getCurrentQueryResult().getColumnCount()>=2||Interface.getCurrentQueryResult().getRowCount()>=2)) {
					  for (int i=0; i<Interface.getCurrentQueryResult().getRowCount(); i=i+1) {
						   for (int j=0; j<Interface.getCurrentQueryResult().getColumnCount(); j=j+1) {
						    frame.consoleoutput(Interface.getCurrentQueryResult().getData()[i][j]+" ");
						   }
					  } 
				  }else {
					  frame.settablenames(Interface.getCurrentQueryResult().getColumnNames());
//					  frame.settabledata(Interface.getCurrentQueryResult().getData());
				  }
			}
			
			
			
			
		}
		
	}
	
}
