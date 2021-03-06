package io.minetweak.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Logan Gorence
 */
class MakeVanillaCopy extends DefaultTask {

    @TaskAction
    void makeVanillaCopy() {
        if (new File("mcp/vanillaSrc/").exists()) {
            state.upToDate()
        } else {
            FileUtils.copyDirectory(new File("mcp/src"), new File("mcp/vanillaSrc"))
        }
    }

}
