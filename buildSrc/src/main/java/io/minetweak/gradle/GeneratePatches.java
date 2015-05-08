package io.minetweak.gradle;

import com.cloudbees.diff.Diff;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Logan Gorence
 */
public class GeneratePatches extends DefaultTask {

    private static String getFileAsString(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        return new String(bytes);
    }

    @TaskAction
    public void generatePatchesTask() {
        generatePatches(MinetweakPlugin.vanillaSrcDir);
    }

    private void generatePatches(File parent) {
        for (File f : parent.listFiles()) {
            if (f.isFile()) {
                generatePatch(f);
            } else {
                generatePatches(f);
            }
        }
    }

    private void generatePatch(File vanilla) {
        File modded = new File(vanilla.getPath().replace("vanillaSrc", "src"));
        String path = vanilla.getPath().substring(15);
        File patch = new File("patches/" + path + ".patch");
        try {
            String vStr = getFileAsString(vanilla);
            String mStr = getFileAsString(modded);
            Diff diff = Diff.diff(vanilla, modded, false);
            if (!diff.isEmpty()) {
                patch.getParentFile().mkdirs();
                String diffStr = diff.toUnifiedDiff(vanilla.getPath(), modded.getPath(),
                        new StringReader(vStr), new StringReader(mStr), 5);
                PrintWriter pw = new PrintWriter(patch);
                pw.write(diffStr);
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
