package com.example.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class JiaguPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        final JiaguExt jiaguExt = project.getExtensions().create("jiagu", JiaguExt.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(final Project project) {
                project.getTasks().create("jiagu", JiaguTask.class, jiaguExt);
                /*AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
                appExtension.getApplicationVariants().all(new Action<ApplicationVariant>() {
                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                File outputFile = baseVariantOutput.getOutputFile();
                                String name = baseVariantOutput.getName();
                                if("release".equals(name)){
                                    project.getTasks().create("jiagu", JiaguTask.class, jiaguExt);
                                }
                            }
                        });
                    }
                });*/
            }
        });
    }
}
