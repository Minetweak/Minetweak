package io.minetweak.gradle

import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.model.ZipParameters
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class ZipReobfuscated extends DefaultTask {

    @TaskAction
    void zipReobfuscated() {
        new File("mtrelease.zip").delete()
        ZipParameters zps = new ZipParameters()
        zps.includeRootFolder = false
        new ZipFile("mtrelease.zip").createZipFileFromFolder(
                new File("mcp/reobf/minecraft_server/"),
                zps,
                false,
                0L
        )
    }

}
