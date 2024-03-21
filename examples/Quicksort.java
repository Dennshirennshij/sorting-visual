import github.dennshirennshij.nodedev74.sorting_visual.gui.node.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

public class Quicksort extends Algorithm {
    
    // Constructors, do not change
    public Quicksort(SortingWindow window) {
        super(window);
    }

    public Quicksort() {}
    
    
    // start method for the algorithm
    // the given elements are the elements of the list that should be sorted
    public void start(int[] elements) {
        
        addVisualList(elements);
        int n = getLength(0);
        
        sort(0, 0, n-1);
 
        System.out.println("sorted array");
    }
    
    private int partition(int listI, int low, int high)
    {
        int pivot = get(listI, high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (get(listI, j) <= pivot)
            {
                i++;
                trade(listI, i, j);
            }
        }
        trade(listI, i+1, high);
 
        return i+1;
    }
 
 
    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    private void sort(int listI, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(listI, low, high);
 
            // Recursively sort elements before
            // partition and after partition
            sort(listI, low, pi-1);
            sort(listI, pi+1, high);
        }
    }
    
    public String getName() {
        // Change this to the name of your algorithm
        return "Quicksort";
    }
}
