package graphicalClass;

import infoClass.Kingdom;
import infoClass.Lord;
import infoClass.Trooper;

import javax.swing.*;
import javax.swing.text.TableView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Argawaen
 * Date: Jun 22, 2004
 * Time: 7:44:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssaultTable extends JPanel {
    JTable table_1;
    JTable table_2;
    private final String _NONE = "< Aucun >";
    Kingdom[] knownKingdoms;
    Trooper[] troopers;

    public AssaultTable(Kingdom[] knownKingdoms, Trooper[] troopers) {
        this.knownKingdoms = knownKingdoms;
        this.troopers = troopers;

        initTable();

        this.add(table_1);
    }

    public void initTable() {
        table_1 = new JTable();
        final String[] tittleNames = {
            "Type Troupe",
            "Defense",
            "Assaut 1",
            "Assaut 2",
            "Assaut 3"};

        final Object[][] data_1 = getData_1();

        // Create a model of the data.
        TableModel dataModel_1 = new AbstractTableModel() {
            public int getColumnCount() {
                return tittleNames.length;
            }

            public int getRowCount() {
                return data_1.length;
            }

            public Object getValueAt(int row, int col) {
                return data_1[row][col];
            }

            public String getColumnName(int column) {
                return tittleNames[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int row, int col) {
                return col > 1 ;
            }

            public void setValueAt(Object aValue, int row, int column) {
                data_1[row][column] = aValue;
            }
        };


        TableColumn column = table_1.getColumn("Assaut 1");
        // Use the combo box as the editor in the "Favorite Color" column.
        column.setCellEditor(new DefaultCellEditor(getKingdomComboBox()));

        // Create the table
        table_1.setModel(dataModel_1);

    }

    private Object[][] getData_1() {

        final Object[][] data = new Object[1][5];


        JComboBox assault_1 = getKingdomComboBox();
        assault_1.setSelectedIndex(0);
        JComboBox assault_2 = getKingdomComboBox();
        assault_2.setSelectedIndex(0);
        JComboBox assault_3 = getKingdomComboBox();
        assault_3.setSelectedIndex(0);

        data[0][0] = "Attaque sur";
        data[0][1] = "Reste en Defense";
        data[0][2] = _NONE;
        data[0][3] = _NONE;
        data[0][4] = _NONE;

//        for (int i = 0; i < troopers.length; i++) {
//            data[i + 1][0] = troopers[i].getName();
//            data[i + 1][1] = "" + troopers[i].getNb();
//            for (int j = 2; j < 5; j++) {
////                data[i + 1][j] = new JSpinner.NumberEditor(new JSpinner());
//                 data[i + 1][j] = _NONE;
//            }
//        }

        return data;
    }

    private JComboBox getKingdomComboBox() {

        JComboBox comboBox = new JComboBox();
        comboBox.addItem(_NONE);
        for (int i = 0; i < knownKingdoms.length; i++) {
            comboBox.addItem(knownKingdoms[i].getLord().getLogin());
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
        //????     generalPanel.generalPanel.setPreferredSize(new Dimension(600, 500));
//      generalPanel.setVisible(true);
        newFrame.setTitle("AssaultTable...");

        newFrame.getContentPane().add(assaultTable, BorderLayout.CENTER);

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
