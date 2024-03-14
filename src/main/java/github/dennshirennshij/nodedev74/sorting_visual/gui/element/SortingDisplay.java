package github.dennshirennshij.nodedev74.sorting_visual.gui.element;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class SortingDisplay extends BarChart {

    public SortingDisplay() {
        super(new CategoryAxis(), new NumberAxis());
    }
}
