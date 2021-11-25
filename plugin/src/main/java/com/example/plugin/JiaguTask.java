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
        //选择最新修改的文件进行加固 省的每次输入apk的名称
//        doJiagu();
        doZipAlign();
        doResign();
    }

    /**
     * 执行加固
     */
    public void doJiagu(){
        File selectedFile = getLatestFile(jiaguExt.getInputPath());
        if(null == selectedFile){
            System.out.println("!!! no file need to jiagu");
            return;
        }
        final String inputApkPath = selectedFile.getAbsolutePath();
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                execSpec.commandLine("java", "-jar", jiaguExt.getJarToolPath(),
                        "-login", jiaguExt.getUsername(), jiaguExt.getPassword());
                System.out.println(execSpec.getCommandLine());
            }
        });
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                execSpec.commandLine("java", "-jar", jiaguExt.getJarToolPath(),
                        "-jiagu", inputApkPath, jiaguExt.getOutputPath());
                System.out.println(execSpec.getCommandLine());
            }
        });
    }

    /**
     * 设置4字节对齐
     */
    public void doZipAlign(){
        File jiaguFile = getLatestFile(jiaguExt.getOutputPath());
        if(null == jiaguFile){
            System.out.println("!!!加固后的文件为空");
            return;
        }
        final String jiaguFilePath = jiaguFile.getAbsolutePath();
        //设置对齐后的名称以及重签名后的名称
        jiaguExt.setZipAlignApkPath(jiaguFilePath.replace(".apk", "_zipAligned.apk"));
        //执行对齐操作
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                System.out.println("!!!开始对齐操作");
                execSpec.commandLine(jiaguExt.getSdkHome() + "\\build-tools\\27.0.3\\zipalign",
                        "-p", "-f", "-v", "4", jiaguFilePath, jiaguExt.getZipAlignApkPath());
                System.out.println(execSpec.getCommandLine());
                System.out.println("!!!对齐结束");
            }
        });
    }

    /**
     * 经过360加固后需要重签名
     */
    public void doResign(){
        File zipAlignFile = getLatestFile(jiaguExt.getOutputPath());
        if(null == zipAlignFile || !zipAlignFile.getName().contains("_zipAligned")){
            System.out.println("zipAlignFile is not valid"+zipAlignFile);
            return;
        }
        jiaguExt.setZipAlignSignedApkPath(zipAlignFile.getAbsolutePath().replace(".apk", "_signed.apk"));
        //执行重签名操作
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                System.out.println("!!!开始重签名");
                execSpec.commandLine(jiaguExt.getSdkHome() + "\\build-tools\\27.0.3\\apksigner",
                        "sign",
                        "--out",jiaguExt.getZipAlignSignedApkPath(),
                        "--ks",jiaguExt.getKeystore_path(),
                        "--ks-key-alias",jiaguExt.getAlias(),
                        "--key-pass","pass:"+jiaguExt.getKeystore_password(),
                        "--ks-pass","pass:"+jiaguExt.getAlias_password(),
                        jiaguExt.getZipAlignApkPath());
                System.out.println(execSpec.getCommandLine());
                System.out.println("!!!重签名结束");
            }
        });
    }

    public static void showFiles(List<File> list,String path){
        System.out.println("开始展示文件---"+path);
        for(File file:list){
            Long time =file.lastModified();
            Calendar cd = Calendar.getInstance();
            cd.setTimeInMillis(time);
            System.out.println(file.getName()+":"+cd.getTime() + ":" +file.getAbsolutePath());
        }
        System.out.println("展示文件结束---"+path);
    }

    public static File getLatestFile(String path){
        List<File> list = getFileSort(path);
        showFiles(list,path);
        return list.size() > 0 ? list.get(0) : null;
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
