package panelClass;

import infoClass.Building;

import javax.swing.*;

import graphicalClass.TrainingTable;


public class Order_TrainingPanel extends JPanel{

    TrainingTable trainingTable ;

    Building[] buildings;


    public Order_TrainingPanel(Building[] buildings) {
        this.buildings = buildings;

        // initialization of the training's table
        initTrainingTable();




    }

    private void initTrainingTable() {
        trainingTable = new TrainingTable();
    }


}
