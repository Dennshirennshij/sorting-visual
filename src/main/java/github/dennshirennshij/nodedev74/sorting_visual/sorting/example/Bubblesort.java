package github.dennshirennshij.nodedev74.sorting_visual.sorting.example;

import github.dennshirennshij.nodedev74.sorting_visual.gui.node.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

public class Bubblesort extends Algorithm {

    public Bubblesort(SortingWindow window) {
        super(window);
    }

    public Bubblesort() {}

    @Override
    public void start(int[] elements) {
        int list = addVisualList(elements);

        for(int i = 1; i < getLength(list); i++) {
            for(int j = 0; j < (getLength(list) - i); j++) {
                if(get(list, j) > get(list, j + 1)) {
                    trade(list, j, j + 1);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubblesort";
    }
}
