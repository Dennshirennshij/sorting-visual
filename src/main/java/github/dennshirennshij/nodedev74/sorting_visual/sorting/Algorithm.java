package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import java.util.ArrayList;

public abstract class Algorithm {
    private AlgorithmWindowInterface window;

    private ArrayList<VisualList> visualLists;

    public abstract void start(int[] elements);

    public Algorithm (AlgorithmWindowInterface window) {
        this.window = window;
        this.visualLists = new ArrayList<VisualList>();
    }

    protected int addVisualList () {
        while (window.isPaused());
        VisualList visualList = new VisualList();
        window.addVisualList(visualList);
        this.visualLists.add(visualList);
        return this.visualLists.size() - 1;
    }

    // todo: add visualList.length equivalent

    protected void trade (int listIndex, int i1, int i2) {
        while (window.isPaused());
        window.trade(listIndex, i1, i2);
        this.visualLists.get(listIndex).trade(i1, i2);
    }

    protected void set (int listIndex, int index, int value) {
        while (window.isPaused());
        window.set(listIndex, index, value);
        this.visualLists.get(listIndex).set(index, value);
    }

    protected int get (int listIndex, int index) {
        while (window.isPaused());
        window.get(listIndex, index);
        return this.visualLists.get(listIndex).get(index);
    }

    // todo: add split operation?

}
