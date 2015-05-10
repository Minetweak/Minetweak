package io.minetweak.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class RecompileSources extends DefaultTask {

    @TaskAction
    void recompileSources() {
        new ProcessHelper().dir(new File("mcp/"))
                .command("bash", "recompile.sh", "--server", "--nocopy", "-w ../minecraftDir/")
                .inheritIO()
                .start()
    }

}
