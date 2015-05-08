package io.minetweak.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

/**
 * @author Logan Gorence
 */
public class MinetweakPlugin implements Plugin<Project> {

    public static File patchesDir = new File("patches/");
    public static File modSrcDir = new File("mcp/src/");
    public static File vanillaSrcDir = new File("mcp/vanillaSrc/");

    public void apply(Project project) {
        patchesDir.mkdir();

        project.getTasks().create("generatePatches", GeneratePatches.class);
        project.getTasks().create("applyPatches", ApplyPatches.class);
    }

}
