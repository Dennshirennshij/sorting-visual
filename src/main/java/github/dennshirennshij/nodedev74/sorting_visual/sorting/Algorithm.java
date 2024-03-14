package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingWindow;

import java.util.ArrayList;

public abstract class Algorithm {
    private final SortingWindow window;

    private ArrayList<VisualList> visualLists;

    public abstract int[] start (int[] elements);

    public abstract String getName ();

    public Algorithm (SortingWindow window) {
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

    protected void removeVisualList (int listIndex) {
        while (window.isPaused());
        window.removeVisualList(listIndex);
        this.visualLists.remove(listIndex);
    }
    protected int addVisualList (int[] elements) {
        while (window.isPaused());
        VisualList visualList = new VisualList(elements);
        window.addVisualList(visualList);
        this.visualLists.add(visualList);
        return this.visualLists.size() - 1;
    }

    protected int getLength (int listIndex) {
        while (window.isPaused());
        window.getLength(listIndex);
        return this.visualLists.get(listIndex).size();
    }

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

    protected int[] getArray (int listIndex) {
        while (window.isPaused());
        window.getArray(listIndex);
        return this.visualLists.get(listIndex).toIntArray();
    }

    // todo: add split operation?

}
