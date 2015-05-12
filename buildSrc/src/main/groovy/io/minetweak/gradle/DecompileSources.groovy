package io.minetweak.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class DecompileSources extends DefaultTask {

    @TaskAction
    void decompileSources() {
        if (new File("mcp/src/").exists()) {
            state.upToDate()
        } else {
            def exit = new ProcessHelper().dir(new File("mcp/"))
                    .command("bash", "decompile.sh", "--server", "--nocopy")
                    .inheritIO()
                    .start()
            if (exit != 0) {
                state.failure.initCause(new Throwable("Decompile failed"))
            }
        }
    }

}
