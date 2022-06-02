package xyz.innky.graduationproject.web.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange dExchange() {
        return new DirectExchange("dExchange");
    }

    @Bean
    public Queue dQueue() {

        return QueueBuilder.durable("mailQueue").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dQueue()).to(dExchange()).with("mail");
    }
}
