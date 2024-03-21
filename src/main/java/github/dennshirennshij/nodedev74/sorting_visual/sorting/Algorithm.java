package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting.SortingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public abstract class Algorithm {
    private final SortingWindow window;

    private ArrayList<List<Integer>> visualLists;

    public abstract void start (int[] elements);

    public abstract String getName ();

    public Algorithm (SortingWindow window) {
        this.window = window;
        this.visualLists = new ArrayList<List<Integer>>();
    }

    public Algorithm() {
        window = null;
    }

    protected int addVisualList () {
        while (window.isHalted());

        List<Integer> visualList = new ArrayList<>();

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

        List<Integer> visualList = Arrays.stream(elements).boxed().collect(Collectors.toList());

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

        List<Integer> list = this.visualLists.get(listIndex);

        int old = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, old);

        this.visualLists.set(listIndex, list);
    }

    protected void set(int listIndex, int index, int value) {
        while (window.isHalted());

        window.set(listIndex, index, value);
        window.cooldown();

        this.visualLists.get(listIndex).set(index, value);
    }

    protected int get(int listIndex, int index) {
        while (window.isHalted());

        window.get(listIndex, index);
        window.cooldown();

        return this.visualLists.get(listIndex).get(index);
    }

    protected int[] getArray(int listIndex) {
        while (window.isHalted());

        window.cooldown();

        List<Integer> list = visualLists.get(listIndex);
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
