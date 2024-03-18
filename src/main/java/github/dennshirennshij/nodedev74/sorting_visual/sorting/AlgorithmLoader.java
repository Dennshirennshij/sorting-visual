package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import java.util.HashMap;

public class AlgorithmLoader {

    private static AlgorithmLoader singleton;

    private HashMap<String, Class<? extends Algorithm>> loadedClasses;

    private AlgorithmLoader() {
        loadedClasses = new HashMap<>();
    }

    public static AlgorithmLoader getInstance() {
        if(singleton == null) {
            singleton = new AlgorithmLoader();
        }
        return singleton;
    }

    public String[] getAlgorithmNames() {
        return loadedClasses.keySet().toArray(new String[0]);
    }

    public void registerClass(Class<?extends Algorithm> target) {
        try {
            Algorithm algo = target.getDeclaredConstructor().newInstance();
            loadedClasses.put(algo.getName(), target);
        } catch(Exception e) {
            System.out.println("Cannot create instance" + e.getMessage());
        }
    }

    public Class<?extends Algorithm> getAlgorithmClassByName(String name) {
        return loadedClasses.get(name);
    }

}
