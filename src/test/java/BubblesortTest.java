import github.dennshirennshij.nodedev74.sorting_visual.gui.node.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

public class BubblesortTest extends Algorithm {

    // Constructors, do not change
    public BubblesortTest(SortingWindow window) {
        super(window);
    }

    public BubblesortTest() {}


    // start method for the algorithm
    // the given elements are the elements of the list that should be sorted
    public void start(int[] elements) {
        // this is bubblesort for example
        int list = addVisualList(elements);

        for(int i = 1; i < getLength(list); i++) {
            for(int j = 0; j < (getLength(list) - i); j++) {
                if(get(list, j) > get(list, j + 1)) {
                    trade(list, j, j + 1);
                }
            }
        }
    }

    public String getName() {
        // Change this to the name of your algorithm
        return "Bubblesort";
    }
}
