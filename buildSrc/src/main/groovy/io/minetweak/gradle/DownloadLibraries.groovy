package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class DownloadLibraries extends DefaultTask {

    static final String LW_URL =
            "https://libraries.minecraft.net/net/minecraft/launchwrapper/1.11/launchwrapper-1.11.jar"

    @TaskAction
    void downloadLibraries() {
        def lw = new File("mcp/lib/launchwrapper-1.11.jar")
        if (lw.exists()) {
            state.upToDate()
        } else {
            FileUtils.copyURLToFile(new URL(LW_URL), lw)
        }
    }

}
