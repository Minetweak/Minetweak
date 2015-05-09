package io.minetweak.gradle

import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.exception.ZipException
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class UnzipMCP extends DefaultTask {

    @TaskAction
    void unzipMCP() {
        if (new File("mcp/decompile.sh").exists()) {
            state.upToDate()
        } else {
            try {
                ZipFile zipFile = new ZipFile("mcp/mcp.zip")
                zipFile.extractAll("mcp/");
            } catch (ZipException e) {
                e.printStackTrace()
            }
        }
    }

}
