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
        while (window.isHalted());
        VisualList visualList = new VisualList();
        window.addVisualList(visualList);
        this.visualLists.add(visualList);
        window.cooldown();
        return this.visualLists.size() - 1;
    }

    protected void removeVisualList (int listIndex) {
        while (window.isHalted());
        window.removeVisualList(listIndex);
        this.visualLists.remove(listIndex);
        window.cooldown();
    }
    protected int addVisualList (int[] elements) {
        while (window.isHalted());
        VisualList visualList = new VisualList(elements);
        window.addVisualList(visualList);
        this.visualLists.add(visualList);
        window.cooldown();
        return this.visualLists.size() - 1;
    }

    protected int getLength (int listIndex) {
        while (window.isHalted());
        window.getLength(listIndex);
        window.cooldown();
        return this.visualLists.get(listIndex).size();
    }

    protected void trade (int listIndex, int i1, int i2) {
        while (window.isHalted());
        window.trade(listIndex, i1, i2);
        window.cooldown();
        this.visualLists.get(listIndex).trade(i1, i2);
    }

    protected void set (int listIndex, int index, int value) {
        while (window.isHalted());
        window.set(listIndex, index, value);
        window.cooldown();
        this.visualLists.get(listIndex).set(index, value);
    }

    protected int get (int listIndex, int index) {
        while (window.isHalted());
        window.get(listIndex, index);
        window.cooldown();
        return this.visualLists.get(listIndex).get(index);
    }

    protected int[] getArray (int listIndex) {
        while (window.isHalted());
        window.getArray(listIndex);
        window.cooldown();
        return this.visualLists.get(listIndex).toIntArray();
    }

    // todo: add split operation?

}
