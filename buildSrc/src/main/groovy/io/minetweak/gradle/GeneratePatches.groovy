package io.minetweak.gradle

import com.cloudbees.diff.Diff
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author Logan Gorence
 */
class GeneratePatches extends DefaultTask {

    private static String getFileAsString(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()))
        return new String(bytes)
    }

    @TaskAction
    void generatePatchesTask() {
        generatePatches(MinetweakPlugin.vanillaSrcDir)
    }

    private void generatePatches(File parent) {
        for (File f : parent.listFiles()) {
            if (f.isFile()) {
                generatePatch(f)
            } else {
                generatePatches(f)
            }
        }
    }

    private void generatePatch(File vanilla) {
        File modded = new File(vanilla.path.replace("vanillaSrc", "src"))
        String path = vanilla.path.substring(15)
        File patch = new File("patches/${path}.patch")
        try {
            String vStr = getFileAsString(vanilla)
            String mStr = getFileAsString(modded)
            Diff diff = Diff.diff(vanilla, modded, false)
            if (!diff.isEmpty()) {
                patch.parentFile.mkdirs()
                String diffStr = diff.toUnifiedDiff(vanilla.path, modded.path,
                        new StringReader(vStr), new StringReader(mStr), 5)
                PrintWriter pw = new PrintWriter(patch)
                pw.write(diffStr)
                pw.close()
            }
        } catch (IOException e) {
            e.printStackTrace()
        }
    }
}
