package com.example.demo.service.picture;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(
        prefix = "fileupload.pic"
)
@Component
class PicUploadProperties {

    private static String realpath;
    private static String module;
    private static String urlPrefix;
    private static String filenameSuffix;

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public  String getModule() {
        return module;
    }

    public  void setModule(String module) {
        this.module = module;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getFilenameSuffix() {
        return filenameSuffix;
    }

    public void setFilenameSuffix(String filenameSuffix) {
        this.filenameSuffix = filenameSuffix;
    }
}
