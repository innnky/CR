package xyz.innky.graduationproject.common.utils;

import cn.hutool.extra.mail.MailUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {

    @RabbitListener(queues = "mailQueue")
    public void receive(String message) {
        String[] split = message.split(",");
        MailUtil.send(split[0], split[1], split[2], false);
        System.out.println("Receive message: " + message);
    }
}
