package github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SortingDisplay extends BarChart<String, Number> {

    // Contains references to the Lists in the Algorithm object
    private final ArrayList<List<Integer>> dataset;

    private ArrayList<AtomicInteger> variables;

    public SortingDisplay() {
        super(new CategoryAxis(), new NumberAxis());

        setLegendVisible(false);
        setAnimated(false);

        dataset = new ArrayList<>();
    }

    public void renderListChanges(int listIndex) {
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

    public void renderVariableChanges(int index) {
        // Get the node of the first series
        Node node = getData().get(0).getNode();
        VisualVariable text = new VisualVariable(String.valueOf(variables.get(index).intValue()), index);

        Group parentGroup = (Group) node.getParent();

        // Remove old text from graph
        List<Node> targets = parentGroup.getChildren().stream().filter((child) -> child instanceof VisualVariable && ((VisualVariable) child).getVariableIndex() == index).toList();
        if(!targets.isEmpty()) {
            Node target = targets.get(0);
            parentGroup.getChildren().remove(target);
        }

        // Add text to graph
        parentGroup.getChildren().add(text);

        // Set text position
        text.setLayoutX(0.0);
        text.setLayoutY(0.0);
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
