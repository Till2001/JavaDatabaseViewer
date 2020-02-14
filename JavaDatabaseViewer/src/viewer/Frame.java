package viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import sqlite_databaseconnector.QueryResult;

public class Frame {

	private JFrame frame;
	private JMenuBar topmenubar;
	private JMenu[] topmenus;
	private JMenuItem[] datamenus;
	private Container pane;
	private Controller controller;
	private JTable table;
	private JSplitPane endconsole;
	private JTextArea output;
	private JTextField input;
	private String[][] tabledata;
	private String[]   tablenames;
	private int maxarraysize;
	private Table_controller tablecontroller;
	
	public Frame() {
		frame = new JFrame("Java Database Viewer");
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = frame.getContentPane();
		setuptopmenubar(pane);
		tablecontroller= new Table_controller();
		setuptable();
		setupendconsole(pane);
		controller = new Controller(this,tablecontroller);
		frame.pack();
		frame.setVisible(true);	
	}
	

	private void setuptopmenubar(Container pC) {
		topmenubar = new JMenuBar();
		pane.add(topmenubar, BorderLayout.PAGE_START);
		topmenubar.setBackground(new Color(157, 169, 189));
		topmenubar.setPreferredSize(new Dimension(500, 25));
		
		topmenus = new JMenu[5];
		topmenus[0]= new JMenu("Datei");
		topmenus[1]= new JMenu("placeholder");
		topmenus[2]= new JMenu("placeholder");
		topmenus[3]= new JMenu("placeholder");
		topmenus[4]= new JMenu("placeholder");

		
		for(int i = 0;i<topmenus.length;i++) {
			topmenubar.add(topmenus[i]);
		}
		
		datamenus = new JMenuItem[3];
		datamenus[0] = new JMenuItem("Datenbank öffnen");
		datamenus[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jcs = new JFileChooser();
				if ((jcs.showOpenDialog(null) == jcs.APPROVE_OPTION)) {
					File dbfile = jcs.getSelectedFile();
					if(dbfile.exists()&&dbfile.canRead()&&dbfile.canWrite()&&dbfile.isFile()) {
						int dotposition = dbfile.getName().lastIndexOf('.');
						String filetype = dbfile.getName().substring(dotposition+1);
						if (filetype.equalsIgnoreCase("db")) {
							controller.load(dbfile);
						}else {
							throwerror("Die Datei "+dbfile.getAbsolutePath()+" ist kein .db Datei.");
						}
					}else {
						throwerror("Auf die Datei "+dbfile.getAbsolutePath()+" kann nicht zugegriffen werden.");
					}
					
				}

			}
		});
		datamenus[1] = new JMenuItem("Datenbank speichern");
		
		datamenus[2] = new JMenuItem("Beenden");
		datamenus[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		for(int i = 0;i<datamenus.length;i++) {
			topmenus[0].add(datamenus[i]);
		}

	}


	private void setuptable() {
		JScrollPane scroll = new JScrollPane(tablecontroller.gettable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.add(scroll,BorderLayout.CENTER);
		
	}

	
	
	private void setupendconsole(Container pC) {
		endconsole = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		endconsole.setPreferredSize(new Dimension(0, 125));
		
		output = new JTextArea();
		output.setEnabled(false);
		JScrollPane scroll = new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		endconsole.setTopComponent(scroll);
		input = new JTextField();
		input.setPreferredSize(new Dimension(0, 25));
		endconsole.setBottomComponent(input);
		endconsole.setDividerLocation(85);
		pC.add(endconsole,BorderLayout.PAGE_END);
		
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 10:
					output.setText(input.getText());
					controller.executesql(input.getText());
					input.setText("");
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void consoleoutput(String pOutput) {
		output.setText(output.getText()+"\n"+pOutput);
	}
	
	private void setupcentertable(Container pC) {
		String tabledata[][] = { {"A","B","C","D","E","F","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
				                 {"M","N","O","P","Q","R","","","",""},
				                 {"G","H","I","J","K","L","","","",""},
		
		};
		String tablenames[] = {"1","2","3","4","5","6","7","8","9",""};
		table = new JTable(tabledata, tablenames);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pC.add(scroll,BorderLayout.CENTER);
		
	}
	
	public void throwerror(String ErrorMsg) {
		JOptionPane.showMessageDialog(frame, ErrorMsg, "Fehler",JOptionPane.ERROR_MESSAGE);
	}	
	
	public void settabledata(String[][] pDataset) {
		for (int row = 0;row<pDataset.length;row++) {
			for(int col = 0;col<pDataset[row].length;col++) {
				table.getModel().setValueAt(pDataset[row][col], row, col);
			}
		}
	}
	
	public void settabledatabyquery(QueryResult queryResult) {
		
		for (int i=0; i<queryResult.getRowCount(); i=i+1) {
			for (int j=0; j<queryResult.getColumnCount(); j=j+1) {
				System.out.println(table.getModel().getValueAt(i, j));
				table.getModel().setValueAt(queryResult.getData()[i][j].toString(), i, j);
			}
		}
	}
	
	public void settablenames(String[] pNames) {
		TableColumnModel cm = table.getColumnModel();
		int cols = cm.getColumnCount();
		
		for(int i = cols;i>=1;i--) {
			cm.removeColumn(cm.getColumn(i-1));
		}
		for(int i = 0;i<pNames.length;i++) {
			TableColumn tc = new TableColumn();
			tc.setHeaderValue(pNames[i]);
			cm.addColumn(tc);
		}
	}
	
}
