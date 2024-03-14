package github.dennshirennshij.nodedev74.sorting_visual.plugins;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

public class PluginLoader {

    private HashMap<String, Class<? extends Algorithm>> loadedClasses = new HashMap<>();

    public static void load() {
        // Ordnerpfad zu den JAR-Dateien
        String pluginsFolderPath = "plugins/";

        // Array für geladene Klassen
        ArrayList<Class<?>> loadedClasses = new ArrayList<>();

        try {
            // Verzeichnis für JAR-Dateien erstellen
            File pluginsFolder = new File(pluginsFolderPath);
            File[] jarFiles = pluginsFolder.listFiles((dir, name) -> name.endsWith(".jar"));

            if (jarFiles != null) {
                // URLClassLoader erstellen
                URLClassLoader classLoader = new URLClassLoader(
                        new URL[]{pluginsFolder.toURI().toURL()}
                );

                // Durch alle JAR-Dateien iterieren
                for (File jarFile : jarFiles) {
                    // Klasse laden
                    Class<?> loadedClass = classLoader.loadClass("AlgorithmMain");

                    // Überprüfen, ob die geladene Klasse Algorithm erweitert
                    if (Algorithm.class.isAssignableFrom(loadedClass)) {
                        loadedClasses.add(loadedClass);
                        System.out.println("Klasse geladen: " + loadedClass.getName());
                    } else {
                        System.out.println("Klasse in " + jarFile.getName() + " erweitert nicht Algorithm.");
                    }
                }
            } else {
                System.out.println("Keine JAR-Dateien im Ordner " + pluginsFolderPath + " gefunden.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
