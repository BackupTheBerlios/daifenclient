package graphicalClass;

import infoClass.Kingdom;
import infoClass.Lord;
import infoClass.Trooper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ContainerListener;
import java.util.EventListener;

/**
 * @version 1.0 11/09/98
 */
//class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
//
//    CheckBoxRenderer() {
//        setHorizontalAlignment(JLabel.CENTER);
//    }
//
//    public Component getTableCellRendererComponent(JTable table, Object value,
//                                                   boolean isSelected, boolean hasFocus,
//                                                   int row, int column) {
//        if (isSelected) {
//            setForeground(table.getSelectionForeground());
//            //super.setBackground(table.getSelectionBackground());
//            setBackground(table.getSelectionBackground());
//        } else {
//            setForeground(table.getForeground());
//            setBackground(table.getBackground());
//        }
//        setSelected((value != null && ((Boolean) value).booleanValue()));
//        return this;
//    }
//}


public class AssaultTable extends JScrollPane {

    private final String _NONE = "< Aucun >";

    Kingdom[] knownKingdoms;
    Trooper[] troopers;


    public AssaultTable(Kingdom[] knownKingdoms, Trooper[] troopers) {
        super();

        this.knownKingdoms = knownKingdoms;
        this.troopers = troopers;

        DefaultTableModel dm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column > 1) {
                    return true;
                }
                return false;
            }
        };
        dm.setDataVector(getObject(),
                new Object[]{"Type Troupe",
                             "Reste en Defense",
                             "Assaut 1",
                             "Assaut 2",
                             "Assaut 3"});

        EachRowRenderer rowRenderer = new EachRowRenderer();
        NumSpinnerRenderer numSpinnerRenderer;
        for (int i = 0; i < troopers.length; i++) {
            numSpinnerRenderer = new NumSpinnerRenderer();
            rowRenderer.add(i + 1, numSpinnerRenderer);
        }

        JComboBox comboBox = getKingdomComboBox();

        DefaultCellEditor comboBoxEditor = new DefaultCellEditor(comboBox);


        JTable table = new JTable(dm);
        table.setRowHeight(25);

// TODO ajout du listener sur la JTable        
//        AssaultTableListener assaultTableListener = new AssaultTableListener();
////        table.addContainerListener(assaultTableListener);
//        table.addComponentListener(assaultTableListener);

        EachRowEditor rowEditor = new EachRowEditor(table);
        rowEditor.add(0, comboBoxEditor);
        NumSpinnerEditor numSpinnerEditor;
        for (int i = 0; i < troopers.length; i++) {
            numSpinnerEditor = new NumSpinnerEditor(new JCheckBox());
            rowEditor.add(i + 1, numSpinnerEditor);
        }

        table.getColumn("Assaut 1").setCellRenderer(rowRenderer);
        table.getColumn("Assaut 1").setCellEditor(rowEditor);
        table.getColumn("Assaut 2").setCellRenderer(rowRenderer);
        table.getColumn("Assaut 2").setCellEditor(rowEditor);
        table.getColumn("Assaut 3").setCellRenderer(rowRenderer);
        table.getColumn("Assaut 3").setCellEditor(rowEditor);

        this.setViewportView(table);

    }

    private JComboBox getKingdomComboBox() {

        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new String(_NONE));
        for (int i = 0; i < knownKingdoms.length; i++) {
            comboBox.addItem(new String(knownKingdoms[i].getLord().getLogin()));
        }
        return comboBox;
    }

    private Object[][] getObject() {
        Object[][] object = new Object[troopers.length + 1][5];

        object[0][0] = "Attaque sur";
        object[0][1] = "";
        object[0][2] = _NONE;
        object[0][3] = _NONE;
        object[0][4] = _NONE;

        for (int i = 0; i < troopers.length; i++) {
            object[i + 1][0] = troopers[i].getName();
            object[i + 1][1] = "" + troopers[i].getNb();
            for (int j = 2; j < 5; j++) {
//                data[i + 1][j] = new JSpinner.NumberEditor(new JSpinner());
                object[i + 1][j] = "" + 0;
            }
        }


        return object;
    }


    public static void main(String[] args) {

        Kingdom[] tUKingdoms = {new Kingdom(), new Kingdom(), new Kingdom()};
        tUKingdoms[0].setLord(new Lord("Argawaen", ""));
        tUKingdoms[1].setLord(new Lord("Stollvor", ""));
        tUKingdoms[2].setLord(new Lord("Portekwa", ""));

        Trooper[] tUTroopers = {new Trooper("Scout", 0, 30, 3, 2, 0, 1, 6, 0, 0, 0),
                                new Trooper("Barde", 0, 20, 3, 2, 0, 1, 6, 0, 0, 0),
                                new Trooper("Kass'Bras", 0, 3, 3, 2, 0, 1, 6, 0, 0, 0),
                                new Trooper("Eluros", 0, 50, 3, 2, 0, 1, 6, 0, 0, 0)};

        JFrame frame = new JFrame(GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scroll = new AssaultTable(tUKingdoms, tUTroopers);

        frame.getContentPane().add(scroll);
        frame.setSize(400, 160);
        frame.pack();
        frame.show();

//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
    }
}

