package github.dennshirennshij.nodedev74.sorting_visual.gui.layout;

import github.dennshirennshij.nodedev74.sorting_visual.gui.node.SortingWindow;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class WindowLayout extends VBox {

    public static final int MAX_LAYERS = 2;
    public static final int MAX_LAYER_NODES = 2;

    private final ArrayList<HBox> layers;

    private int count;

    public WindowLayout() {
        layers = new ArrayList<>();

        setFillWidth(true);

        createLayer();
    }

    public void addWindow(SortingWindow window) {
        if(!isFull()) {
            HBox targetLayer = getCurrentLayer();
            HBox.setHgrow(window, Priority.ALWAYS);
            targetLayer.getChildren().add(window);
            count++;
        }
    }

    private HBox getCurrentLayer() {
        if(!isLayerFull(layers.size() - 1)) {
            return layers.getLast();
        } else {
            return createLayer();
        }
    }

    private HBox createLayer() {
        HBox hbox = new HBox();
        VBox.setVgrow(hbox, Priority.ALWAYS);

        this.getChildren().add(hbox);
        layers.add(hbox);
        return hbox;
    }

    public boolean isLayerFull(int index) {
        return layers.get(index).getChildrenUnmodifiable().size() == MAX_LAYERS;
    }

    public boolean isFull() {
        if(layers.size() == MAX_LAYERS) {
            return !isLayerFull(layers.size() - 1);
        }
        return false;
    }

    public int countWindows() {
        return this.count;
    }
}
