package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * The type Multi line table cell renderer.
 */
public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

    /**
     * Instantiates a new Multi line table cell renderer.
     */
    public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(new Controller().impactFontSize);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
}
