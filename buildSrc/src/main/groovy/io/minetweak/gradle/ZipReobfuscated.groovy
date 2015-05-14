package io.minetweak.gradle

import org.gradle.api.tasks.bundling.Zip

/**
 * @author Logan Gorence
 */
class ZipReobfuscated extends Zip {

    public ZipReobfuscated() {
        baseName = "Minetweak"
        from "mcp/reobf/minecraft_server/"
    }

}
