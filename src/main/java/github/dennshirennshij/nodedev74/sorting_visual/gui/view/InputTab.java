package github.dennshirennshij.nodedev74.sorting_visual.gui.view;

import github.dennshirennshij.nodedev74.sorting_visual.exception.IncorrectArraySyntax;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.Random;

public class InputTab extends Tab {

    private int[] input = null;

    public InputTab() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/element/InputTab.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }
    }

    public static int[] parseStringToArray(String string) throws IncorrectArraySyntax {
        try {
            string = string.trim();
            String[] substrings = string.split(",");

            int[] res = new int[substrings.length];
            for (int i = 0; i < res.length; i++) {
                int number = Integer.parseInt(substrings[i]);
                res[i] = number;
            }

            return res;
        } catch(Exception e) {
            throw new IncorrectArraySyntax();
        }
    }

    public static String parseArrayToString(int[] array) {

        StringBuilder builder = new StringBuilder();
        for(int integer : array) {
            builder.append(integer).append(",");
        }

        return builder.toString();
    }

    public static int[] getRandomArray(int min, int max, int size) {
        Random random = new Random();
        int[] res = new int[size];
        for(int i = 0; i < size; i++) {
            int randomNumber = random.nextInt(max + min) + min;
            res[i] = randomNumber;
        }
        return res;
    }

    public void setInputArray(int[] array) {
        this.input = array;
    }

    public int[] getInputArray() {
        return this.input;
    }

    public boolean hasValidInput() {
        return this.input != null;
    }
}
