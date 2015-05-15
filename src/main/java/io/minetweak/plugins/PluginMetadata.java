package io.minetweak.plugins;

/**
 * Represents a JSON structure for a plugin's metadata.
 *
 * @author Logan Gorence
 */
public class PluginMetadata {

    private String name;

    private String mainClass;
    public PluginMetadata(String name, String mainClass) {
        this.mainClass = mainClass;
    }

    public String getName() {
        return name;
    }

    public String getMainClass() {
        return mainClass;
    }
}
