package graphicalClass;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Jun 24, 2004
 * Time: 11:00:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class NumSpinnerRenderer extends JSpinner implements TableCellRenderer {

    public NumSpinnerRenderer() {
        super(new SpinnerNumberModel());
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
//    setText( (value ==null) ? "" : value.toString() );

        Integer intValue = (value == null) ? new Integer(0) : new Integer(value.toString());
        this.getModel().setValue(intValue);

        return this;
    }
}


