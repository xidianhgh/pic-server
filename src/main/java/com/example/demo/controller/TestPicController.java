package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.dto.Response;
import com.example.demo.entity.Person;
import com.example.demo.service.APIService;
import com.example.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MI on 2019/7/5.
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestPicController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private APIService apiService;

    @GetMapping("")
    public Map<String,Object> testHttpClient() throws Exception {

//        Map<String,Object> map=restTemplate.getForObject("http://localhost:8089/hello",Map.class);

        String url="http://192.168.199.111:8089/test/generic";
        Person req=new Person();
        req.setAge(124);
        JSONObject response=restTemplate.postForObject(url,req,JSONObject.class);

        Response resp=restTemplate.postForObject(url,req,Response.class);

        Map<String,Object> reqMap=new HashMap<>();
        reqMap.put("age",90);
//        HttpResult resp=apiService.doPost(url,reqMap);

        String respString=apiService.sendHttpPost(url,JSONObject.toJSONString(reqMap));

        JSONObject respJson=JSONObject.parseObject(respString);
        Map<String,Object> map=new HashMap<>();
//        HttpClient httpClient=new HttpClient();
        return map;

    }

    @PostMapping("/generic")
    public Response test(@RequestBody Person req){

        List<Person> list=new ArrayList<>();
        for(int i=0;i<3;i++){
            Person person=new Person();
            person.setName(String.valueOf(i)+"messi");
            person.setAge(req.getAge());
            list.add(person);
        }
        return Response.ok(list);
    }

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
