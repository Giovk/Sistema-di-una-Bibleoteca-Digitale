package GUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * The type Separator header renderer.
 */
class SeparatorHeaderRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;

    /**
     * Instantiates a new Separator header renderer.
     *
     * @param defaultRenderer the default renderer
     */
    public SeparatorHeaderRenderer(TableCellRenderer defaultRenderer) {
        this.defaultRenderer = defaultRenderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.decode("#222831")));
        }
        return component;
    }
}
