package xyz.innky.graduationproject.common.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMailUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;
    public void sendMail(String to, String subject, String content) {
        rabbitTemplate.convertAndSend("dExchange", "mail", to+","+subject+","+content);
    }
}
