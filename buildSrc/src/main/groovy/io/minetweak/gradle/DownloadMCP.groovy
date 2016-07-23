package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class DownloadMCP extends DefaultTask {

    private static final String MCP_DOWNLOAD = "http://www.modcoderpack.com/website/sites/default/files/releases/mcp931.zip"

    @TaskAction
    void downloadMCP() {
        def mcp = new File("mcp/mcp.zip")
        if (mcp.exists()) {
            state.upToDate()
        } else {
            FileUtils.copyURLToFile(new URL(MCP_DOWNLOAD), mcp)
        }
    }

}
