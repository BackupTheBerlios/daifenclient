
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 24, 2004
 * Time: 4:09:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BilanPanel {

    private boolean DEBUG = true;

    public JPanel bilanPanel;

    private JPanel cadreBatiments;
    private JTable buildingTable;


    private JLabel or;
    private JLabel intellect;
    private JPanel cadreConnaissances;
    private JTable connaissancesTable;
    private JPanel cadreRessources;
    private JPanel cadreTroupes;
    private JTable troupesTable;
    private JPanel cadreRoyaumesConnus;
    private JTable royaumesConnusTable;


    public BilanPanel( Royaume royaume ) {

        // Update Ressources
        updateRessources( royaume );

        // Update Connaissance
        updateConnaissance( royaume );

        // Update Building Table
        updateBuildingTable( royaume );

    }

    // Ressources update
    void updateRessources ( Royaume royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getRessource() == null ){
            cadreRessources.setVisible(false);
        }
        else {
            Ressource ressource = royaume.getRessource();
            or.setText( new String( "" + ressource.getOr() ) );
            intellect.setText( new String( "" + ressource.getIntellect() ) );

            cadreRessources.setVisible(true);
        }
    }

    // Connaissance Table update
    void updateConnaissance( Royaume royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getConnaissances() == null
                || royaume.getConnaissances().length == 0 ){
            cadreConnaissances.setVisible(false);
        }
        else {
            Connaissance[] connaissances = royaume.getConnaissances();
            String[] columnNames = {new String("Type") };


            Object[][] data = new Object[connaissances.length][1];
            for ( int i=0; i<connaissances.length; i++) {
                data[i][0] = new String( connaissances[0].getName() );
            }

            TableModel dataModel = new MyTableModel( data, columnNames);


            // Create the table
            connaissancesTable.setModel(dataModel);

            cadreConnaissances.setVisible(true);
        }
    }

    // Building Table update
    void updateBuildingTable( Royaume royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getBuildings() == null
                || royaume.getBuildings().length == 0 ){
            cadreBatiments.setVisible(false);
        }
        else {
            Building[] buildings = royaume.getBuildings();
            String[] columnNames = {new String("Type"),
                                    new String("Nombre"),
                                    new String("Resistance"),
                                    new String("Apport Or"),
                                    new String("Apport Intellect")};


            Object[][] data = new Object[buildings.length][5];
            for ( int i=0; i<buildings.length; i++) {
                data[i][0] = new String(buildings[i].getName());
                data[i][1] = new Integer(buildings[i].getNb());
                data[i][2] = new Integer(0);
                data[i][3] = new Integer(0);
                data[i][4] = new Integer(0);

            }

            // Create the table
            TableModel dataModel = new MyTableModel( data, columnNames);
            buildingTable.setModel(dataModel);

            cadreBatiments.setVisible(true);
        }
        //buildingTable = new JTable(dataModel);
    }

    // Troupes Table update
    void updateTroupesTable( Royaume royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getTroopers() == null
                || royaume.getTroopers().length == 0 ){
            cadreTroupes.setVisible(false);
        }
        else {
            Trooper[] troopers = royaume.getTroopers();
            String[] columnNames = {new String("Type"),
                                    new String("Nombre"),
                                    new String("Resistance"),
                                    new String("Apport Or"),
                                    new String("Apport Intellect")};


            Object[][] data = new Object[troopers.length][5];
            for ( int i=0; i<troopers.length; i++) {
                data[i][0] = new String(troopers[i].getName());
                data[i][1] = new Integer(troopers[i].getNb());
                data[i][2] = new Integer(0);
                data[i][3] = new Integer(0);
                data[i][4] = new Integer(0);

            }

            // Create the table
            TableModel dataModel = new MyTableModel( data, columnNames);
            troupesTable.setModel(dataModel);

            cadreTroupes.setVisible(true);
        }
    }

    // Connaissances Table update
    void updateConnaissancesTable( Royaume royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getConnaissances() == null
                || royaume.getConnaissances().length == 0 ){
            cadreConnaissances.setVisible(false);
        }
        else {
            // titre
            Connaissance[] connaissances = royaume.getConnaissances();
            String[] columnNames = { new String("nom") };

            // donnees
            Object[][] data = new Object[connaissances.length][1];
            for ( int i=0; i<connaissances.length; i++) {
                data[i][0] = new String(connaissances[i].getName());
            }

            // Create the table
            TableModel dataModel = new MyTableModel( data, columnNames);
            connaissancesTable.setModel(dataModel);

            cadreConnaissances.setVisible(true);
        }
    }

    // Connaissances Table update
    void updateRoyaumesConnusTable( Royaume[] royaumesConnus ){

        if ( royaumesConnus == null
                || royaumesConnus.length == 0 ){
            cadreRoyaumesConnus.setVisible(false);
        }
        else {
            // titre
            String[] columnNames = { new String("Royaume de..."),
                                     new String("Race"),
                                     new String("Adresse Mail"),
                                     new String("est un allie")};

            // donnees
            Object[][] data = new Object[royaumesConnus.length][4];

            for ( int i=0; i<royaumesConnus.length; i++) {
                data[i][0] = new String(royaumesConnus[i].getLord().getLogin());
                data[i][1] = new String(royaumesConnus[i].getLord().getRace());
                data[i][2] = new String(royaumesConnus[i].getLord().getEMail());
                data[i][3] = new Boolean(false);
            }

            // Create the table
            TableModel dataModel = new MyTableModel( data, columnNames);
            royaumesConnusTable.setModel(dataModel);

            cadreRoyaumesConnus.setVisible(true);
        }
    }


    class MyTableModel extends AbstractTableModel {
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


}

