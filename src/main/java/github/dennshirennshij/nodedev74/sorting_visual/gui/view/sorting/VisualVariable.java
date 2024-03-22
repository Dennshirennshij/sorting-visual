package github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting;

import javafx.scene.text.Text;

public class VisualVariable extends Text {

    private int index;

    public VisualVariable(String text, int index) {
        super(text);

        this.index = index;
    }

    public int getVariableIndex() {
        return this.index;
    }
}
