package com.example.demo.controller;

import com.example.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MI on 2019/7/5.
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestPicController {

    @Value("${fileupload.pic.urlPrefix}")
    private String picUrlPrefix;
    @Value("${fileupload.pic.realpath}")
    private String fileRealPath;
    @PostMapping("/upload")
    public Map<String,Object> upload(@RequestParam MultipartFile multipartFile) throws Exception {
//        String path="./pic/";
        String path=fileRealPath;
        createDir(path);
        File file=new File(path+multipartFile.getOriginalFilename());

        FileUtil.inputstream2File(multipartFile.getInputStream(),file);

        Map<String,Object> result=new HashMap<>();

        String absolutePath=file.getPath();
        int beginIndex=absolutePath.replace(File.separator,"/").indexOf(fileRealPath)+fileRealPath.length();
        String relativePath=absolutePath.substring(beginIndex);
        String picUrl=picUrlPrefix+relativePath.replace(File.separator,"/");
//        result.put("picUrl",file.getPath());
        result.put("picUrl",picUrl);
        return result;

    }

    private void createDir(String dir){
        File file=new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
    }


}
