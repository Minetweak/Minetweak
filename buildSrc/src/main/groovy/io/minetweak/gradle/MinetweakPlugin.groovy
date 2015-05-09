package io.minetweak.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Logan Gorence
 */
class MinetweakPlugin implements Plugin<Project> {

    public static File patchesDir = new File("patches/")
    public static File vanillaSrcDir = new File("mcp/vanillaSrc/")

    void apply(Project project) {
        patchesDir.mkdir()

        GeneratePatches generatePatches =
                project.getTasks().create("generatePatches", GeneratePatches.class)
        generatePatches.setDescription("Generate Minecraft source patches")
        generatePatches.setGroup("minetweak")

        ApplyPatches applyPatches =
                project.getTasks().create("applyPatches", ApplyPatches.class)
        applyPatches.setDescription("Apply patches to the Minecraft source")
        applyPatches.setGroup("minetweak")

        CopySources copySources =
                project.getTasks().create("copySources", CopySources.class)
        copySources.setDescription("Copy Minetweak sources to Minecraft sources")
        copySources.setGroup("minetweak")

        DownloadMCP downloadMCP =
                project.getTasks().create("downloadMCP", DownloadMCP.class)
        downloadMCP.setDescription("Download MCP")
        downloadMCP.setGroup("minetweak")

        UnzipMCP unzipMCP =
                project.getTasks().create("unzipMCP", UnzipMCP.class)
        unzipMCP.setDescription("Unzip MCP")
        unzipMCP.setGroup("minetweak")
        unzipMCP.dependsOn(downloadMCP)

        DownloadMinecraft downloadMinecraft =
                project.getTasks().create("downloadMinecraft", DownloadMinecraft.class)
        downloadMinecraft.setDescription("Download Minecraft server JAR")
        downloadMinecraft.setGroup("minetweak")
        downloadMinecraft.dependsOn(unzipMCP)

        DecompileSources decompileSources =
                project.getTasks().create("decompileSources", DecompileSources.class)
        decompileSources.setDescription("Decompile Minecraft sources")
        decompileSources.setGroup("minetweak")
        decompileSources.dependsOn(downloadMinecraft)

        RecompileSources recompileSources =
                project.getTasks().create("recompileSources", RecompileSources.class)
        recompileSources.setDescription("Recompile Minecraft sources")
        recompileSources.setGroup("minetweak")
        recompileSources.dependsOn(decompileSources)

        ReobfuscateSources reobfuscateSources =
                project.getTasks().create("reobfuscateSources", ReobfuscateSources.class)
        reobfuscateSources.setDescription("Reobfuscate Minecraft sources")
        reobfuscateSources.setGroup("minetweak")
        reobfuscateSources.dependsOn(recompileSources)
    }

}
