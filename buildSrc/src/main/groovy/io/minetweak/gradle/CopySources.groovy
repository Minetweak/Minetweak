package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class CopySources extends DefaultTask {
    @TaskAction
    void copySources() {
        FileUtils.deleteDirectory(new File("mcp/src/minecraft_server/io"))
        FileUtils.copyDirectory(new File("src/main/java/"), new File("mcp/src/minecraft_server/"))
    }
}
