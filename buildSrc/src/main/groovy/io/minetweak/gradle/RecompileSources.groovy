package io.minetweak.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class RecompileSources extends DefaultTask {

    @TaskAction
    void recompileSources() {
        def exit = new ProcessHelper().dir(new File("mcp/"))
                .command("bash", "recompile.sh", "--server")
                .inheritIO()
                .start()
        if (exit != 0) {
            state.failure.initCause(new Throwable("Recompile failed"))
        }
    }

}
