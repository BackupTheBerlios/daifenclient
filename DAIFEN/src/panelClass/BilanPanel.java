package panelClass;

import infoClass.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import graphicalClass.MyTableModel;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 24, 2004
 * Time: 4:09:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BilanPanel {

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


    public BilanPanel( Kingdom royaume ) {

        // Update Ressources
        updateRessources( royaume );

        // Update infoClass.Knowledge
        updateConnaissance( royaume );

        // Update infoClass.Building Table
        updateBuildingTable( royaume );

    }

    // Ressources update
    public void updateRessources ( Kingdom royaume ){

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

    // infoClass.Knowledge Table update
    void updateConnaissance( Kingdom royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getConnaissances() == null
                || royaume.getConnaissances().length == 0 ){
            cadreConnaissances.setVisible(false);
        }
        else {
            Knowledge[] connaissances = royaume.getConnaissances();
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

    // infoClass.Building Table update
    public void updateBuildingTable( Kingdom royaume ){

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
    public void updateTroupesTable( Kingdom royaume ){

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
    public void updateConnaissancesTable( Kingdom royaume ){

        // Create a model of the data.

        if ( royaume == null
                || royaume.getConnaissances() == null
                || royaume.getConnaissances().length == 0 ){
            cadreConnaissances.setVisible(false);
        }
        else {
            // titre
            Knowledge[] connaissances = royaume.getConnaissances();
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
    public void updateRoyaumesConnusTable( Kingdom[] royaumesConnus ){

        if ( royaumesConnus == null
                || royaumesConnus.length == 0 ){
            cadreRoyaumesConnus.setVisible(false);
        }
        else {
            // titre
            String[] columnNames = { new String("infoClass.Kingdom de..."),
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


}

