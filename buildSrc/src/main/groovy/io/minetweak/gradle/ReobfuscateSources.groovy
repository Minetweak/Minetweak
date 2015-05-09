package io.minetweak.gradle

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
        new ProcessHelper().dir(new File("mcp/"))
                .command("bash", "reobfuscate.sh", "--server")
                .inheritIO()
                .start()
    }

}
