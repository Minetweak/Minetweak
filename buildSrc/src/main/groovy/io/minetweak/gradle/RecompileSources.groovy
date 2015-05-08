package io.minetweak.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class RecompileSources extends DefaultTask {

    @TaskAction
    void recompileSources() {
        def pb = new ProcessBuilder()
        pb.directory(new File("mcp/"))
        pb.command("bash", "recompile.sh", "--server")
        // TODO: Redirect output/error streams
        //pb.redirectError()
        //pb.redirectOutput()
        def p = pb.start()
        // Wait for the process, and don't finish the build yet.
        p.waitFor()
    }

}
