package graphicalClass;

import infoClass.Kingdom;
import infoClass.Lord;
import infoClass.Trooper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Argawaen
 * Date: Jun 22, 2004
 * Time: 7:44:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssaultTable extends JPanel {
    JScrollPane scrollpane_1;
    JScrollPane scrollpane_2;
    JTable table_1;
    JTable table_2;
    private final String _NONE = "< Aucun >";
    Kingdom[] knownKingdoms;
    Trooper[] troopers;

    public AssaultTable(Kingdom[] knownKingdoms, Trooper[] troopers) {
        this.knownKingdoms = knownKingdoms;
        this.troopers = troopers;

        initTable();
        scrollpane_1.setAutoscrolls(true);
//        scrollpane_1.setPreferredSize(table_1.getColumnCount()*table_1.get);
        scrollpane_2.setAutoscrolls(true);
        scrollpane_2.setPreferredSize(new Dimension(-1,-1));

        this.add(scrollpane_1, BorderLayout.NORTH);
        this.add(scrollpane_2, BorderLayout.SOUTH);
    }

    public void initTable() {

        // INIT TABLE 1*********************************************************

        final String[] titleNames_1 = {
            "Type Troupe",
            "",
            "Assaut 1",
            "Assaut 2",
            "Assaut 3"};

        final Object[][] data_1 = getData_1();

        // Create a model of the data.
        TableModel dataModel_1 = new AbstractTableModel() {
            public int getColumnCount() {
                return titleNames_1.length;
            }

            public int getRowCount() {
                return data_1.length;
            }

            public Object getValueAt(int row, int col) {
                return data_1[row][col];
            }

            public String getColumnName(int column) {
                return titleNames_1[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int row, int col) {
                return col > 1;
            }

            public void setValueAt(Object aValue, int row, int column) {
                data_1[row][column] = aValue;
            }
        };

        // Create the table
        table_1 = new JTable(dataModel_1);

        // Use the combo box as the editor in the "Favorite Color" column.
        TableColumn column = table_1.getColumn("Assaut 1");
        column.setCellEditor(new DefaultCellEditor(getKingdomComboBox()));
        column = table_1.getColumn("Assaut 2");
        column.setCellEditor(new DefaultCellEditor(getKingdomComboBox()));
        column = table_1.getColumn("Assaut 3");
        column.setCellEditor(new DefaultCellEditor(getKingdomComboBox()));

        scrollpane_1 = new JScrollPane(table_1);
        // Create the table
//        table_1.setModel(dataModel_1);

        // INIT TABLE 2 ********************************************************

         final String[] titleNames_2 = {
            "Type Troupe",
            "Defense",
            "Assaut 1",
            "Assaut 2",
            "Assaut 3"};

        final Object[][] data_2 = getData_2();

        // Create a model of the data.
        TableModel dataModel_2 = new AbstractTableModel() {
            public int getColumnCount() {
                return titleNames_2.length;
            }

            public int getRowCount() {
                return data_2.length;
            }

            public Object getValueAt(int row, int col) {
                return data_2[row][col];
            }

            public String getColumnName(int column) {
                return titleNames_2[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int row, int col) {
                return col > 1;
            }

            public void setValueAt(Object aValue, int row, int column) {
                data_1[row][column] = aValue;
            }
        };

        // Create the table
        table_2 = new JTable(dataModel_2);

        scrollpane_2 =new JScrollPane(table_2);

    }

    private Object[][] getData_1() {

        Object[][] data = new Object[1][5];

        data[0][0] = new String("Attaque sur");
        data[0][1] = new String("Reste en Defense");
        data[0][2] = new String(_NONE);
        data[0][3] = new String(_NONE);
        data[0][4] = new String(_NONE);

        return data;
    }

        private Object[][] getData_2() {

        Object[][] data = new Object[troopers.length][5];

        for (int i = 0; i < troopers.length; i++) {
            data[i][0] = troopers[i].getName();
            data[i][1] = "" + troopers[i].getNb();
            for (int j = 2; j < 5; j++) {
//                data[i + 1][j] = new JSpinner.NumberEditor(new JSpinner());
                 data[i][j] = new Integer(0);
            }
        }

        return data;
    }

    private JComboBox getKingdomComboBox() {

        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new String(_NONE));
        for (int i = 0; i < knownKingdoms.length; i++) {
            comboBox.addItem(new String(knownKingdoms[i].getLord().getLogin()));
        }
        return comboBox;
    }


    public static void main(String[] args) {
        JFrame newFrame = new JFrame(GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration());
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Kingdom[] tUKingdoms = {new Kingdom(), new Kingdom(), new Kingdom()};
        tUKingdoms[0].setLord(new Lord("Argawaen", ""));
        tUKingdoms[1].setLord(new Lord("Stollvor", ""));
        tUKingdoms[2].setLord(new Lord("Portekwa", ""));

         Trooper[] tUTroopers = {new Trooper("Scout", 0, 30, 3, 2, 0, 1, 6, 0, 0, 0),
                                new Trooper("Barde", 0, 20, 3, 2, 0, 1, 6, 0, 0, 0),
                                new Trooper("Casse Bras", 0, 3, 3, 2, 0, 1, 6, 0, 0, 0)};

        AssaultTable assaultTable = new AssaultTable(tUKingdoms, tUTroopers);
//       generalPanel.generalPanel.setPreferredSize(new Dimension(600, 500));
//      generalPanel.setVisible(true);
        newFrame.setTitle("AssaultTable...");

        newFrame.getContentPane().add(assaultTable);

        newFrame.pack();


        newFrame.show();



//        final JFrame frame = new JFrame("GeneralPanel Form");
//        frame.setContentPane(generalPanelForm.generalPanel);
//
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.show();
//       generalPanelForm.generalPanel.repaint();

    }

}
