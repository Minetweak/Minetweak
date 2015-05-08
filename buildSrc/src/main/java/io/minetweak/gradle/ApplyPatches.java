package io.minetweak.gradle;

import com.cloudbees.diff.ContextualPatch;
import com.cloudbees.diff.PatchException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

/**
 * @author Logan Gorence
 */
public class ApplyPatches extends DefaultTask {

    @TaskAction
    public void applyPatchesTask() {
        applyPatches(MinetweakPlugin.patchesDir);
    }

    private void applyPatches(File parent) {
        for (File f : parent.listFiles()) {
            if (f.isFile()) {
                applyPatch(f);
            } else {
                applyPatches(f);
            }
        }
    }

    private void applyPatch(File patch) {
        String path = patch.getPath().substring(8);
        path = path.substring(0, path.lastIndexOf(".patch"));
        File context = new File("mcp/src/" + path);
        ContextualPatch cpatch = ContextualPatch.create(patch, context);
        try {
            cpatch.patch(false);
        } catch (PatchException e) {
            System.err.println("Patch failed...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
