package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class ReobfuscateSources extends DefaultTask {

    ReobfuscateSources() {
        setDescription("Reobfuscate Minecraft source code")
        setGroup()
    }

    @TaskAction
    void reobfuscateSources() {
        FileUtils.touch(new File("mcp/temp/client_meta.log"))
        def exit = new ProcessHelper().dir(new File("mcp/"))
                .command("bash", "reobfuscate.sh", "--server")
                .inheritIO()
                .start()
        if (exit != 0) {
            state.failure.initCause(new Throwable("Reobfuscate failed"))
        }
    }

}
