package com.example.plugin;

public class JiaguExt {
    private String jarToolPath;
    private String username;
    private String password;
    private String keystore_path;
    private String keystore_password;
    private String alias;
    private String alias_password;
    private String inputPath;
    private String outputPath;
    private String sdkHome;
    private String zipAlignApkPath;
    private String zipAlignSignedApkPath;

    public String getZipAlignApkPath() {
        return zipAlignApkPath;
    }

    public void setZipAlignApkPath(String zipAlignApkPath) {
        this.zipAlignApkPath = zipAlignApkPath;
    }

    public String getZipAlignSignedApkPath() {
        return zipAlignSignedApkPath;
    }

    public void setZipAlignSignedApkPath(String zipAlignSignedApkPath) {
        this.zipAlignSignedApkPath = zipAlignSignedApkPath;
    }

    public String getSdkHome() {
        return sdkHome;
    }

    public void setSdkHome(String sdkHome) {
        this.sdkHome = sdkHome;
    }

    public String getJarToolPath() {
        return jarToolPath;
    }

    public void setJarToolPath(String jarToolPath) {
        this.jarToolPath = jarToolPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeystore_path() {
        return keystore_path;
    }

    public void setKeystore_path(String keystore_path) {
        this.keystore_path = keystore_path;
    }

    public String getKeystore_password() {
        return keystore_password;
    }

    public void setKeystore_password(String keystore_password) {
        this.keystore_password = keystore_password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias_password() {
        return alias_password;
    }

    public void setAlias_password(String alias_password) {
        this.alias_password = alias_password;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}