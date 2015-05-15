package io.minetweak.plugins;

import com.google.gson.Gson;
import io.minetweak.Minetweak;
import io.minetweak.event.bus.EventBus;
import samrg472.plugins.exception.FileNotFoundException;
import samrg472.plugins.jar.loader.JarLoader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Class to determine and load plugins.
 *
 * @author Logan Gorence
 */
public class PluginManager<T> extends samrg472.plugins.plugin.PluginManager<T> {

    private EventBus eventBus;

    public PluginManager(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    public void loadPlugins(File pluginsDirectory) throws IOException, IllegalAccessException, InstantiationException {
        Gson gson = new Gson();
        File[] plugins = pluginsDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        });
        for (File plugin : plugins) {
            JarFile jarFile = new JarFile(plugin);
            ZipEntry pluginJson = jarFile.getEntry("plugin.json");
            if (pluginJson != null) {
                System.out.println("Loading plugin " + plugin.getPath());
                PluginMetadata meta = gson.fromJson(new InputStreamReader(jarFile.getInputStream(pluginJson)), PluginMetadata.class);
                try {
                    Object o = addPlugin(meta.getName(), plugin, meta.getMainClass());
                    eventBus.register(o);
                    System.out.println(eventBus.getHandlerCount());
                } catch (ClassNotFoundException e) {
                    System.err.println("Main class for plugin '" + meta.getName() + "' " + meta.getMainClass() + " not found");
                }
            } else {
                System.err.println("Missing plugin.json in " + plugin.getPath());
            }
        }
    }

    /*@Override
    public T addPlugin(String name, File jar, String clazz, File... dependencies) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        JarLoader<T> loader = super.addPlugin(name, jar, dependencies);
        T instance = loader.createInstance(clazz);
        Minetweak.getInstance().getEventBus().register(instance);
        return instance;
    }*/

}
