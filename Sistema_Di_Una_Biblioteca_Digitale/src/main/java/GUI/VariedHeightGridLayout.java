package GUI;

import java.awt.*;

class VariedHeightGridLayout extends GridLayout {
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
