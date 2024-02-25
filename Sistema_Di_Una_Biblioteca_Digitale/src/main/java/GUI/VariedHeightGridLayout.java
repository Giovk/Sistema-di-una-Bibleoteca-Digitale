package GUI;

import java.awt.*;

/**
 * The type Varied height grid layout.
 */
class VariedHeightGridLayout extends GridLayout {
    /**
     * Instantiates a new Varied height grid layout.
     *
     * @param rows the rows
     * @param cols the cols
     */
    public VariedHeightGridLayout(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            super.layoutContainer(parent);
            int numRows = getRows();
            int numCols = getColumns();
            int totalHeight = parent.getHeight();
            int rowHeight = totalHeight / numRows;

            for (int i = 0; i < numRows; i++) {
                parent.getComponent(i * numCols).setPreferredSize(new Dimension(0, rowHeight));
            }
        }
}






        }
