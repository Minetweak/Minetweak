package io.minetweak;

import io.minetweak.event.bus.EventBus;
import io.minetweak.plugins.PluginManager;

import java.io.File;
import java.io.IOException;

/**
 * @author Logan Gorence
 */
public class Minetweak {

    private static Minetweak minetweak;

    private final File pluginsDirectory;
    private final EventBus eventBus;
    private final PluginManager<Object> pluginManager;

    private Minetweak() {
        pluginsDirectory = new File("plugins/");
        pluginsDirectory.mkdir();

        eventBus = new EventBus();
        eventBus.register(this);

        pluginManager = new PluginManager<Object>(eventBus);
        try {
            pluginManager.loadPlugins(pluginsDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(eventBus.getHandlerCount());
    }

    public static Minetweak getInstance() {
        if (minetweak == null) {
            minetweak = new Minetweak();
        }
        return minetweak;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public File getPluginsDirectory() {
        return pluginsDirectory;
    }
}
