package com.example.demo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlUtil {

    public static void parseXml() throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        String path="C:\\Users\\MI\\Desktop\\test.xml";
        Document document = reader.read(new File(path));
//                String text = "<csdn></csdn>";
//        Document document = DocumentHelper.parseText(text);
//                Document document = DocumentHelper.createDocument();             //创建根节点
//        Element root = document.addElement("csdn");

        Element rootElement = document.getRootElement();
        Document document1 = rootElement.getDocument();


        Element elementTagValueIds = rootElement.element("dependencies").addElement("tag-value-ids");
        Element tagValueIdElement = elementTagValueIds.addElement("tag-value-id");
        tagValueIdElement.addAttribute("id","890");
        elementTagValueIds.addComment("-------------r----------------------");
        Element tagValueIdElement1 = elementTagValueIds.addElement("tag-value-id");
        tagValueIdElement1.addAttribute("id","891");
        //        element.setText("xxxxx");

        String newXml=rootElement.asXML();
        String newPath="C:\\Users\\MI\\Desktop\\test0717.xml";
//        FileUtils.writeStringToFile(new File(newPath),newXml);
        //格式化输出xml
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
        // 设置换行
        xmlFormat.setNewlines(true);
        // 生成缩进
        xmlFormat.setIndent(true);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
//        xmlFormat.setIndent("    ");
        XMLWriter writer = new XMLWriter(new FileWriter(new File(newPath)),xmlFormat);
        writer.write(document);
        writer.flush();
        writer.close();
        System.out.println("success!!!!");
    }

    public static void main(String[] args) throws Exception {
        parseXml();
    }

}
