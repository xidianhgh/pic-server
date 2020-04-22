package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.dto.SendMessageReq;
import com.example.demo.controller.dto.TestEnumField;
import com.example.demo.entity.Log;
import com.example.demo.entity.Tiger;
import com.example.demo.service.ActivemqService;
import com.example.demo.service.MyWebSocket;
import com.example.demo.service.StudyService;
import com.example.demo.service.ThreadService;
import com.example.demo.service.springStudy.mapper.MybatisMapper;
import com.example.demo.service.springStudy.mybatis.TestA;
import com.example.demo.utils.BeanUtil;
import com.example.demo.utils.HttpUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by MI on 2019/3/2.
 */
@RestController
@RequestMapping(value = "/hello")
@Slf4j
public class HelloController {
    @Resource
    private ActivemqService activemqService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private MybatisMapper mybatisMapper;
    @Resource
    private TestA testA;
    @Autowired
    private Tiger zl;
    @Resource
    private StudyService studyService;
    @Resource
    private MyWebSocket webSocket;

    @GetMapping("/mybatis_mapper")
    public Map<String, Object> mybatisMapper() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("result", mybatisMapper.selectList());
        map.put("result2", testA.testa());
        map.put("study_service", studyService.test());
        return map;
    }

//    @GetMapping("/redis/session")
//    public Map<String, Object> redisSession(HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        session.setAttribute("xxx", "ss");
////        session.setMaxInactiveInterval(100);
//
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//
//        Pipeline pipeline = jedis.pipelined();
////        new RedisTemplate<>().
//        pipeline.hset("setHash", "key pipeline", "value pipeline");
//        pipeline.syncAndReturnAll();
//        Map<String, Object> map = new HashMap<>();
//        map.put("sessionId", session.getId());
//        map.put("jedis", jedis.get("kkkvvv"));
//        jedis.setnx(session.getId(),"x");
//        jedis.expire(session.getId(),9);
//        return map;
//    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> hello(HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {
        request.getSession();
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(90);
        Map<String, Object> map = new HashMap<>();
//        map.putAll(testService.testThread());
//        map.put("threadlocal",threadLocal.get());
        map.put("success", true);
        map.put("Brazil", "Champion");
//        Cookie cookie=new Cookie("userId","90");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);

//        for(Cookie cookie:request.getCookies()){
//            cookie.setHttpOnly(true);
//            cookie.setSecure(true);
//            response.addCookie(cookie);
//        }
//        response.setHeader("Set-Cookie", "cookiename=value; Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");

        Cookie cookie = new Cookie("TGC-xidian", "666");
        response.addCookie(cookie);

        return map;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> helloPost(@RequestBody TestEnumField req, HttpServletRequest httpServletRequest) throws ExecutionException, InterruptedException {

        HttpSession session = httpServletRequest.getSession();
//        session.setMaxInactiveInterval(1); //7 minutes
        Map<String, Object> map = new HashMap<>();
        map.put("param", req.getPlateColor());
        map.put("success", true);
        map.put("name", "I am " + BeanUtil.getBean(ThreadService.class).test());
        ThreadService threadService = BeanUtil.getBean(ThreadService.class);
        for (int i = 0; i < 3; i++) {
//            ThreadService threadService=new ThreadService();
            threadService.testAsync();
        }
        return map;
    }


    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Map<String, Object> sendMessage(@RequestBody(required = false) SendMessageReq req) throws JMSException {
        Map<String, Object> map = new HashMap<>();
        if (null == req) {
            req = new SendMessageReq();
        }

        activemqService.sendMessage(req.getMessage());
        map.put("success", true);
        return map;
    }

    @Log("vv")
    @RequestMapping(value = "/receiveMessage", method = RequestMethod.GET)
    public Map<String, Object> sendMessage() throws JMSException {
        Map<String, Object> map = new HashMap<>();
//        threadPoolTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    activemqService.receiveMessage(true);
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        String msg = activemqService.receiveMessage(true);
        map.put("msg", msg);
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    @ApiOperation("测试")
    public Map<String, Object> restart() throws JMSException {
        Map<String, Object> map = new HashMap<>();
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                try {
//                    String restartCmd = "nohup java -jar ./demo-0.0.1-SNAPSHOT.jar";
//                    Thread.sleep(10000);//等10秒，保证分身启动完成后，再关掉自己
//                    Runtime.getRuntime().exec(restartCmd);
//                    log.debug("程序重启完成！");
//                } catch (Exception e) {
//                    log.error("重启失败，原因：", e);
//                }
//            }
//        });
        log.debug("程序准备重启！");
        System.exit(0);
        map.put("success", true);
        return map;
    }

    @GetMapping("/testdel")
    public Map<String, Object> testDel(@RequestParam String taskId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String url = "http://192.168.199.111:8089/hello/del?task_id=" + taskId;
        map.put("yes", HttpUtil.sendHttpDelete(url));
        return map;
    }

    @GetMapping("/get")
    public Map<String, Object> getTest(@RequestParam String param) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/testSendHttpGet")
    public Map<String, Object> testSendHttpGet(@RequestParam String taskId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String url = "http://192.168.199.111:8089/hello/testdel?taskId=" + taskId;
        map.put("yes", JSONObject.parseObject(HttpUtil.sendHttpGet(url)));
        return map;
    }

}
