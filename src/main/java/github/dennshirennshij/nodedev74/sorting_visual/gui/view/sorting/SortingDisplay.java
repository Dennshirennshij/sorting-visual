package github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.util.ArrayList;
import java.util.List;

public class SortingDisplay extends BarChart<String, Number> {

    private final ArrayList<List<Integer>> dataset;

    public SortingDisplay() {
        super(new CategoryAxis(), new NumberAxis());

        setLegendVisible(false);
        setAnimated(false);

        dataset = new ArrayList<>();
    }

    public void swap(int listIndex, int i1, int i2) {
        List<Integer> data = dataset.get(listIndex);

        // Fill series
        BarChart.Series<String, Number> series = new BarChart.Series<>();

        for(int i = 0; i < data.size(); i++) {
            String category = (listIndex != 0) ? listIndex + "." + i : String.valueOf(i);
            series.getData().add(new BarChart.Data<>(category, data.get(i)));
        }

        dataset.set(listIndex, data);
        getData().set(listIndex, series);
    }

    public void addVisualList(List<Integer> list) {
        dataset.add(list);

        BarChart.Series<String, Number> series = new BarChart.Series<>();

        for(int i = 0; i < list.size(); i++) {
            String category = (dataset.size() > 1) ? (dataset.size() - 1) + "." + i : String.valueOf(i);
            series.getData().add(new BarChart.Data<>(category, list.get(i)));
        }

        getData().add(dataset.size() - 1, series);
    }

    public void removeVisualList(int listIndex) {
        dataset.remove(listIndex);
        getData().remove(listIndex);
    }
}
