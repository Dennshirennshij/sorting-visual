import github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

public class MergesortTesting extends Algorithm {
    public MergesortTesting(SortingWindow window) {
        super(window);
    }
    public MergesortTesting() {}
    @Override
    public void start(int[] elements) {
        int listI = addVisualList(elements);
        System.out.println("Created first list");
        sort(listI, 0, getLength(listI) - 1);
    }

    void merge(int listI, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int n1id = addVisualList(new int[n1]);
        int n2id = addVisualList(new int[n2]);

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            set(n1id, i, get(listI, l + 1));
        for (int j = 0; j < n2; ++j)
            set(n2id, j, get(listI, m + 1 + j));

        // Merge the temp arrays
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (get(n1id, i) <= get(n2id, j)) {
                set(listI, k, get(n1id, i));
                i++;
            }
            else {
                set(listI, k, get(n2id, j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            set(listI, k, get(n1id, i));
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            set(listI, k, get(n2id, j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int listI, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(listI, l, m);
            sort(listI, m + 1, r);

            // Merge the sorted halves
            merge(listI, l, m, r);
        }
    }

    @Override
    public String getName() {
        return "Mergesort";
    }
}
