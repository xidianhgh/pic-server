package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Person;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * Created by MI on 2019/3/27.
 */
public class XmlUtils {
    public static void parseXml(String xml) throws Exception {
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        Iterator it = rootElement.elementIterator("body");
        while (it.hasNext()) {
            Element element=(Element)it.next();
            System.out.println(element.getText());
        }
    }

    public static void main(String[] args) throws Exception {
//        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
//                "<note>\n" +
//                "<to>George</to>\n" +
//                "<from>John</from>\n" +
//                "<heading>Reminder</heading>\n" +
//                "<body>Don't forget the meeting!</body>\n" +
//                "</note>";
//        parseXml(xml);
        Person person=new Person();
        person.setAge(9);
        person.setName("h");
        JSONObject jsonObject=(JSONObject) JSONObject.toJSON(person);
        System.out.println(jsonObject.toJSONString());
        Object object=JSONObject.toJSON(person);
        System.out.println(object.toString());

    }
}
