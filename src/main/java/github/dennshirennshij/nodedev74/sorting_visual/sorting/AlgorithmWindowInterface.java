package github.dennshirennshij.nodedev74.sorting_visual.sorting;

public interface AlgorithmWindowInterface {

    // methods for the generic algorithm to interact with the window its contained in
    // todo

    public void trade (int listIndex, int i1, int i2);
    public void set (int listIndex, int index, int value);
    public int get (int listIndex, int index);
    public void addVisualList (VisualList visualList);
    public boolean isPaused ();

}
