package com.example.plugin;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.ExecSpec;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class JiaguTask extends DefaultTask {
    private final JiaguExt jiaguExt;

    @Inject
    public JiaguTask(JiaguExt jiaguExt){
        setGroup("jiagu");
        this.jiaguExt = jiaguExt;
    }

    @TaskAction
    public void jiaguAction(){
        String path = jiaguExt.getOutputPath();
        showFiles(path);
//        getProject().exec(new Action<ExecSpec>() {
//            @Override
//            public void execute(ExecSpec execSpec) {
//                execSpec.commandLine("java", "-jar", jiaguExt.getJarToolPath(),
//                        "-login", jiaguExt.getUsername(), jiaguExt.getPassword());
//            }
//        });
//        getProject().exec(new Action<ExecSpec>() {
//            @Override
//            public void execute(ExecSpec execSpec) {
//                execSpec.commandLine("java", "-jar", jiaguExt.getJarToolPath(),
//                        "-jiagu", jiaguExt.getInputAPKpath(), jiaguExt.getOutputPath());
//            }
//        });
    }

    public static void showFiles(String path){
        List<File> list = getFileSort(path);
        for(File file:list){
            Long time =file.lastModified();
            Calendar cd = Calendar.getInstance();
            cd.setTimeInMillis(time);
            System.out.println(file.getName()+":"+cd.getTime());
        }
    }

  // 获取目录下所有文件(按时间排序)
  public static List<File> getFileSort(String path) {
        List<File> list = getFiles(path, new ArrayList<File>());
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {//降序<;升序>
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }});
        }
        return list;
    }

 // 获取目录下所有文件
 public static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }
}
