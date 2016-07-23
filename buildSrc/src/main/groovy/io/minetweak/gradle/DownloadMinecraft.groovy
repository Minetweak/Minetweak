package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class DownloadMinecraft extends DefaultTask {

    static final String MINECRAFT =
            "https://s3.amazonaws.com/Minecraft.Download/versions/1.10/minecraft_server.1.10.jar"

    @TaskAction
    void downloadMinecraft() {
        def minecraft = new File("mcp/jars/minecraft_server.1.10.jar")
        if (minecraft.exists()) {
            state.upToDate()
        } else {
            FileUtils.copyURLToFile(new URL(MINECRAFT), minecraft)
        }
    }

}
