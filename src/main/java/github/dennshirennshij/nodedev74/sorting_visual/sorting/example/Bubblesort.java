package github.dennshirennshij.nodedev74.sorting_visual.sorting.example;

import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

public class Bubblesort extends Algorithm {
    public Bubblesort(SortingWindow window) {
        super(window);
    }

    @Override
    public int[] start(int[] elements) {
        int list = addVisualList(elements);
        for (int i = 1; i < getLength(list); i++) {
            boolean finished = true;
            for (int j = 0; j < getLength(list) - i; j++) {
                if (get(list, j) > get(list, j + 1)){ // j vor j+1
                    trade(list, j, j+1);
                    finished = false;
                }
            }
            if (finished) break;
        }
        return getArray(list);
    }

    @Override
    public String getName() {
        return "Bubblesort";
    }
}
