package viewer;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Table_controller {

	private JTable table;
	private TableModel tm;
	
	public Table_controller() {
		tm = new TableModel();
		table = new JTable(tm) {
			
		};
		
		
	}
	
	
	public JTable gettable() {
		return table;
	}
	
	public TableModel gettablemodel() {
		return tm;
	}
	
	
	
	class TableModel extends AbstractTableModel{
		private String[] colnames = {""};
		private Object[][] data = {{""}};

		
		@Override
		public int getColumnCount() {
			return colnames.length;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return data[arg0][arg1];
		}
		
		public String[] getcolnames() {
			return colnames;
		}
		
		public void setdataarray(Object[][] pArray) {
			data = pArray;
		}
		
		public void settablecols(String[] pCols, JTable table) {
			int colsold = this.getColumnCount();
			int colsnew = pCols.length;
			colnames = pCols;
			
			
			if(colsold == colsnew) {
				for(int i = 0;i<colsold;i++) {
					table.getColumnModel().getColumn(i).setHeaderValue(pCols[i]);
					
				}
			}

			if(colsold > colsnew) {
				for(int i = colsold;i>colsnew;i--) {
					table.getColumnModel().removeColumn(table.getColumnModel().getColumn(i-1));
				}
				for(int i = 0;i<colsnew;i++) {
					table.getColumnModel().getColumn(i).setHeaderValue(pCols[i]);
				}

			}
			
			if(colsold < colsnew) {
				
				for(int i = colsold;i<colsnew;i++) {
					table.getColumnModel().addColumn(new TableColumn(i, 98, null, null));
				}
				for(int i = 0;i<colsnew;i++) {
					table.getColumnModel().getColumn(i).setHeaderValue(pCols[i]);
				}
			}
		}
	}
	
	
}
