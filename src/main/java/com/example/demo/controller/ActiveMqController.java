package com.example.demo.controller;

import com.example.demo.controller.dto.SendMessageReq;
import com.example.demo.service.ActivemqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MI on 2019/8/21.
 */
@RestController
@RequestMapping(value = "/activemq")
@Slf4j
public class ActiveMqController {
    @Resource
    private ActivemqService activemqService;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Map<String, Object> sendMessage(@RequestBody(required = false) SendMessageReq req) throws JMSException {
        Map<String, Object> map = new HashMap<>();
        if (null == req) {
            req = new SendMessageReq();
        }

        activemqService.sendMessage(req.getMessage(),"queue");
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/receiveMessage", method = RequestMethod.GET)
    public Map<String, Object> sendMessage() throws JMSException {
        Map<String, Object> map = new HashMap<>();
        String msg = activemqService.receiveMessage(false,"queue");
        map.put("msg", msg);
        map.put("success", true);
        return map;
    }

}
