package io.minetweak;

import io.minetweak.event.ServerLoadedEvent;
import io.minetweak.event.bus.EventBus;
import io.minetweak.event.bus.Subscribe;
import io.minetweak.plugins.PluginManager;

import java.io.File;
import java.io.IOException;

/**
 * @author Logan Gorence
 */
public class Minetweak {

    private static Minetweak minetweak;
    private static EventBus eventBus;

    private final File pluginsDirectory;
    private final PluginManager pluginManager;

    private Minetweak() {
        pluginsDirectory = new File("plugins/");
        pluginsDirectory.mkdir();

        eventBus = new EventBus();
        eventBus.register(this);

        pluginManager = new PluginManager();
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

    public static EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }

    @Subscribe
    public void serverLoaded(ServerLoadedEvent event) {
        System.out.println("Server loaded");
        System.out.println(eventBus.getHandlerCount());
    }

    public File getPluginsDirectory() {
        return pluginsDirectory;
    }
}
