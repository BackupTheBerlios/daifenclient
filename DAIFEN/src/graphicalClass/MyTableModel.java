package graphicalClass;

import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel {
     private boolean DEBUG = true;
    
     private String[] columnNames;
     private Object[][] data;

     public MyTableModel( Object[][] data, String[] columnNames ){
         this.columnNames = columnNames;
         this.data = data;
     }

     /*
         public int getColumnCount() { return buildingColumnNames.length; }
         public int getRowCount() { return data.length;}
         public Object getValueAt(int row, int col) {return data[row][col];}
         public String getColumnName(int column) {return buildingColumnNames[column];}
         public Class getColumnClass(int c) {return getValueAt(0, c).getClass();}
         public boolean isCellEditable(int row, int col) {return col != 5;}
         public void setValueAt(Object aValue, int row, int column) { data[row][column] = aValue; }
     */


     public int getColumnCount() {
         return columnNames.length;
     }

     public int getRowCount() {
         return data.length;
     }

     public String getColumnName(int col) {
         return columnNames[col];
     }

     public Object getValueAt(int row, int col) {
         return data[row][col];
     }

     /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
     public Class getColumnClass(int c) {
         return getValueAt(0, c).getClass();
     }

     /*
     * Don't need to implement this method unless your table's
     * editable.
     */
     public boolean isCellEditable(int row, int col) {
         //Note that the data/cell address is constant,
         //no matter where the cell appears onscreen.

             return false;


     }

     /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
     public void setValueAt(Object value, int row, int col) {
         if (DEBUG) {
             System.out.println("Setting value at " + row + "," + col
                     + " to " + value
                     + " (an instance of "
                     + value.getClass() + ")");
         }

         if (data[0][col] instanceof Integer
                 && !(value instanceof Integer)) {
             //With JFC/Swing 1.1 and JDK 1.2, we need to create
             //an Integer from the value; otherwise, the column
             //switches to contain Strings.  Starting with v 1.3,
             //the table automatically converts value to an Integer,
             //so you only need the code in the 'else' part of this
             //'if' block.
             //XXX: See TableEditDemo.java for a better solution!!!
             try {
                 data[row][col] = new Integer(value.toString());
                 fireTableCellUpdated(row, col);
             } catch (NumberFormatException e) {
                 /*        JOptionPane.showMessageDialog(TableDemo.this,
                 "The \"" + getColumnName(col)
                 + "\" column accepts only integer values.");
                 */  }
         } else {
             data[row][col] = value;
             fireTableCellUpdated(row, col);
         }

         if (DEBUG) {
             System.out.println("New value of data:");
             printDebugData();
         }
     }

     private void printDebugData() {
         int numRows = getRowCount();
         int numCols = getColumnCount();

         for (int i=0; i < numRows; i++) {
             System.out.print("    row " + i + ":");
             for (int j=0; j < numCols; j++) {
                 System.out.print("  " + data[i][j]);
             }
             System.out.println();
         }
         System.out.println("--------------------------");
     }
 }

