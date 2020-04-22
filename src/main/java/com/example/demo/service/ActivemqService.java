package com.example.demo.service;

import com.example.demo.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by MI on 2019/3/24.
 */
@Service
@Slf4j
public class ActivemqService implements ApplicationRunner {
    private String tcp = "tcp://";
    private String url = tcp + "127.0.0.1:61616";

    /**
     * 发送消息
     *
     * @param msg
     * @throws JMSException
     */
    public void sendMessage(String msg) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("xidian");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        this.send(session, producer, msg);

        connection.close();

    }

    public void sendMessage(String msg,String mode) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("xidian");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        this.send(session, producer, msg);

        connection.close();

    }

    private void send(Session session, MessageProducer messageProducer, String msg) throws JMSException {
        TextMessage textMessage = session.createTextMessage(msg);
        messageProducer.send(textMessage);
    }

    /**
     * 接收消息
     *
     * @return
     * @throws JMSException
     */
    public String receiveMessage(boolean asynchronous) throws JMSException {
        log.info("开始监听");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("xidian");
        MessageConsumer messageConsumer = session.createConsumer(destination);

        if (asynchronous) {
            StringBuilder msg = new StringBuilder();
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String msgString=((TextMessage) message).getText();
                        msg.append(((TextMessage) message).getText());
                        log.info(TimeUtil.getCurrentTime()+" 收到消息为：" + msgString);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            return msg.toString();
        } else {
            Message message = messageConsumer.receive();
            //p2p模式，如果不关闭连接，第二次请求将会有两个消费者，导致消息被另一个消费掉
//            connection.close();
            return ((TextMessage) message).getText();
        }
    }

    public String receiveMessage(boolean asynchronous,String mode) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("xidian");
        MessageConsumer messageConsumer = session.createConsumer(destination);

        if (asynchronous) {
            StringBuilder msg = new StringBuilder();
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String msgString=((TextMessage) message).getText();
                        msg.append(((TextMessage) message).getText());
                        log.info(TimeUtil.getCurrentTime()+" 收到消息为：" + msgString);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            return msg.toString();
        } else {
            Message message = messageConsumer.receive();
            //p2p模式，如果不关闭连接，第二次请求将会有两个消费者，导致消息被另一个消费掉
//            connection.close();
            return ((TextMessage) message).getText();
        }
    }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        try {
            this.receiveMessage(true);
        }catch (Exception e){
            log.error("监听MQ异常");
        }
    }
}
