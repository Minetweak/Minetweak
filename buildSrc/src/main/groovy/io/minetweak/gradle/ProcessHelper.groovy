package io.minetweak.gradle

/**
 * @author Logan Gorence
 */
class ProcessHelper {

    ProcessBuilder processBuilder

    ProcessHelper() {
        processBuilder = new ProcessBuilder()
    }

    ProcessHelper dir(File directory) {
        processBuilder.directory(directory)
        this
    }

    ProcessHelper command(String... command) {
        processBuilder.command(command)
        this
    }

    ProcessHelper inheritIO() {
        processBuilder.inheritIO()
        this
    }

    int start() {
        def p = processBuilder.start()
        p.waitFor()
    }

}
