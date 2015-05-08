package io.minetweak.launch;

/**
 * Entrypoint past the tweaker that starts mod loading.
 *
 * @author Logan Gorence
 */
public class MinetweakLaunch {

    private static MinetweakLaunch instance;

    private MinetweakTweaker tweaker;

    private MinetweakLaunch(MinetweakTweaker tweaker) {
        this.tweaker = tweaker;
    }

    public static MinetweakLaunch getInstance() {
        return instance;
    }

    public static void setup(MinetweakTweaker tweaker) {
        instance = new MinetweakLaunch(tweaker);
    }

}
