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

        // Add a empty List to Algorithm object
        List<Integer> visualList = new ArrayList<>();
        this.visualLists.add(visualList);

        // Pass information to SortingWindow
        window.addVisualList(visualList);
        window.cooldown();

        return this.visualLists.size() - 1;
    }

    protected void removeVisualList (int listIndex) {
        while (window.isHalted());

        // Remove List from Algorithm object
        this.visualLists.remove(listIndex);

        // Pass information to SortingWindow
        window.removeVisualList(listIndex);
        window.cooldown();
    }
    protected int addVisualList (int[] elements) {
        while (window.isHalted());

        // Create a List in Algorithm object
        List<Integer> visualList = Arrays.stream(elements).boxed().collect(Collectors.toList());
        this.visualLists.add(visualList);

        // Pass information to SortingWindow
        window.addVisualList(visualList);
        window.cooldown();

        return this.visualLists.size() - 1;
    }

    protected int getLength (int listIndex) {
        while(window.isHalted());

        //Pass information to SortingWindow
        window.cooldown();

        return this.visualLists.get(listIndex).size();
    }

    protected void trade (int listIndex, int i1, int i2) {
        while(window.isHalted());

        // Trade given numbers
        List<Integer> list = this.visualLists.get(listIndex);

        int old = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, old);

        this.visualLists.set(listIndex, list);

        // Pass information to SortingWindow
        window.trade(listIndex);
        window.cooldown();
    }

    protected void set(int listIndex, int index, int value) {
        while (window.isHalted());

        // Set value in List
        List<Integer> list = this.visualLists.get(listIndex);
        list.set(index, value);
        this.visualLists.set(listIndex, list);

        // Pass information to SortingWindow
        window.set(listIndex);
        window.cooldown();

        this.visualLists.get(listIndex).set(index, value);
    }

    protected int get(int listIndex, int index) {
        while (window.isHalted());

        // Pass information to SortingWindow
        window.get(listIndex, index);
        window.cooldown();

        return this.visualLists.get(listIndex).get(index);
    }

    protected int[] getArray(int listIndex) {
        while (window.isHalted());

        // Create an array based on the target VisualList
        List<Integer> list = visualLists.get(listIndex);
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        // Pass information to SortingWindow
        window.cooldown();

        return array;
    }
}
