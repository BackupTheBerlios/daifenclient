package graphicalClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by IntelliJ IDEA.
 * User: Argawaen
 * Date: Jun 24, 2004
 * Time: 11:09:02 AM
 * To change this template use File | Settings | File Templates.
 */
/* (swing1.1beta3) */

public class NumSpinnerEditor extends DefaultCellEditor implements ItemListener {
    protected JSpinner spinner;
    private Integer intValue;
//    private boolean isPushed;

    final Integer _spinnerStep = new Integer(1);
    final Integer _spinnerMin = new Integer(0);

    public NumSpinnerEditor(JCheckBox checkBox) {
        super(checkBox);
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
        spinnerNumberModel.setStepSize(_spinnerStep);
        spinnerNumberModel.setMinimum(_spinnerMin);
        spinner = new JSpinner(spinnerNumberModel);
        spinner.setOpaque(true);
//        spinner.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                fireEditingStopped();
//                // TODO updateData()
//            }
//        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            spinner.setForeground(table.getSelectionForeground());
            spinner.setBackground(table.getSelectionBackground());
        } else {
            spinner.setForeground(table.getForeground());
            spinner.setBackground(table.getBackground());
        }
//        intValue = (value == null) ? new Integer(0) : new Integer(value.toString());
////        spinner.setValue(new Integer(value.toString()));
//        spinner.setValue(intValue);
        intValue = new Integer(spinner.getValue().toString());
//        isPushed = true;
        return spinner;
    }

    public Object getCellEditorValue() {
//        if (isPushed) {
//            //
//            //
//            JOptionPane.showMessageDialog(spinner, intValue + ": Ouch!");
//            // System.out.println(label + ": Ouch!");
//        }
//        isPushed = false;
        return new String("" + intValue);
    }

    public boolean stopCellEditing() {
//        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}


