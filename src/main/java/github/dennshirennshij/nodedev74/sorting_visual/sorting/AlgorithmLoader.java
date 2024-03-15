package github.dennshirennshij.nodedev74.sorting_visual.plugins;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class AlgorithmLoader {

    private static AlgorithmLoader singleton;
    private HashMap<String, Class<? extends Algorithm>> loadedClasses = new HashMap<>();

    private AlgorithmLoader() {
        try {
            Path jarDir = Paths.get("");
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey key = jarDir.register(watcher, ENTRY_CREATE);
        } catch (Exception e) {
            System.out.println("Unable to watch directory");
        }
    }

    public static AlgorithmLoader getInstance() {
        if(singleton == null) {
            singleton = new AlgorithmLoader();
        }
        return singleton;
    }

    private void onNewFileAccessible() {
        // todo: file load logic
        System.out.println("new file found");
    }

    public void load() {
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
