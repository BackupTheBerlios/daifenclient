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
      intValue = (value == null) ? new Integer(0) : new Integer(value.toString());
        spinner.setValue(intValue);
      return spinner;

    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}


