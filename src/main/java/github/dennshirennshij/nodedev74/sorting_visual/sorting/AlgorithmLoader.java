package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import java.nio.file.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class AlgorithmLoader {

    private static AlgorithmLoader singleton;

    private HashMap<String, Class<? extends Algorithm>> loadedClasses;

    private AlgorithmLoader() {

        loadedClasses = new HashMap<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Path dir = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    dir.register(watchService, ENTRY_CREATE);

                    while (true) {
                        WatchKey key;
                        try {
                            key = watchService.poll(10, TimeUnit.SECONDS); // Warte 10 Sekunden auf ein Event
                        } catch (InterruptedException e) {
                            return;
                        }

                        if (key != null) {
                            for (WatchEvent<?> event : key.pollEvents()) {
                                // todo: add classes threadsafe to array
                            }
                            key.reset();
                        }
                    }
                }catch (Exception e) {
                    System.out.println("failed to watch dir");
                }
            }
        });

        thread.start();
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

    /*public void load() {
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
    }*/
}
