package com.imooc.cloud.mall.practice.cartorder.mq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitMQ配置类
 */
@Configuration
public class MQConfig {

    @Bean
    public Queue queue1(){
        return new Queue("queue1");
    }
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("cloudExchange");

    }

    @Bean
    Binding bindingExchangeMessage (Queue queue1, DirectExchange exchange){
        return BindingBuilder.bind(queue1).to(exchange).with("productStock");
    }

}
