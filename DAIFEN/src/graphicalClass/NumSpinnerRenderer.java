package graphicalClass;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Argawaen
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
        Integer intValue = (value == null) ? new Integer(0) : new Integer(value.toString());
        this.setValue(intValue);
        return this;
    }
}


