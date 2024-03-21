import github.dennshirennshij.nodedev74.sorting_visual.App;
import javafx.application.Application;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;

public class Main
{
    public static void main(String[] args) {
        // Register any custom sorting algorithms
        AlgorithmLoader.getInstance().registerClass(Bubblesort.class);
        AlgorithmLoader.getInstance().registerClass(Quicksort.class);
        // Launch the application
        // do not change this
        // do not put anything after this statement
        Application.launch(App.class, args);
    }
}
