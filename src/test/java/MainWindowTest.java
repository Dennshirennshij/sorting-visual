import github.dennshirennshij.nodedev74.sorting_visual.App;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import javafx.application.Application;
import org.junit.jupiter.api.Test;

public class MainWindowTest {

    @Test
    void run() {
        AlgorithmLoader.getInstance().registerClass(BubblesortTest.class);
        AlgorithmLoader.getInstance().registerClass(QuicksortTest.class);
        AlgorithmLoader.getInstance().registerClass(MergesortTest.class);
        Application.launch(App.class);
    }
}
