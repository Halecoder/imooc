package com.imooc.cloud.mall.practice.categoryproduct.mq;

import com.imooc.cloud.mall.practice.categoryproduct.model.pojo.Product;
import com.imooc.cloud.mall.practice.categoryproduct.service.ProductService;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收消息
 */
@Component
@RabbitListener(queues="queue1")
public class Receiver {

    @Autowired
    ProductService productService;

    @RabbitHandler
    public void process(String message){
        System.out.println("收到了"+message);
        String[] split = message.split(",");
        productService.updateStock(Integer.valueOf(split[0]),Integer.valueOf(split[1]));
    }
}
