package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import java.util.ArrayList;

public abstract class Algorithms {

    private static final ArrayList<Algorithm> algorithmSingletons = new ArrayList<Algorithm>();

    public static void addAlgorithm (Algorithm algorithm) {
        algorithmSingletons.add(algorithm);
    }

    public static ArrayList<Algorithm> getAlgorithms () {
        return algorithmSingletons;
    }

    public static Algorithm getAlgorithm (String name) {
        for (Algorithm algorithm : algorithmSingletons) {
            if (algorithm.getName().equals(name)) {
                return algorithm;
            }
        }
        return null;
    }
}
