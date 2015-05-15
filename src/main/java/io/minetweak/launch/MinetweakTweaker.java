package io.minetweak.launch;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

/**
 * Tweaker class that implements ITweaker to launch Minecraft.
 *
 * @author Logan Gorence
 */
@SuppressWarnings("UnusedDeclaration")
public class MinetweakTweaker implements ITweaker {

    private File gameDir;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        // If the gameDir isn't specified, use the current directory.
        this.gameDir = (gameDir == null ? new File(".") : gameDir);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        // This fixes the logging in the GUI.
        launchClassLoader.addClassLoaderExclusion("com.mojang.util.QueueLogAppender");
        launchClassLoader.addClassLoaderExclusion("org.objectweb.asm.");

        MinetweakLaunch.setup();
    }

    /**
     * Get the path of the targeted MinecraftServer class.
     *
     * @return Path of the MinecraftServer class
     */
    @Override
    public String getLaunchTarget() {
        return "net.minecraft.server.MinecraftServer";
    }

    @Override
    public String[] getLaunchArguments() {
        // TODO @logangorence
        return new String[0];
    }

    public File getGameDir() {
        return gameDir;
    }
}
