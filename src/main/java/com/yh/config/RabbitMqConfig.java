package com.yh.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yh
 */
@Configuration
public class RabbitMqConfig {
    public static final String CHANGE_NAME = "redis_fabulous_exchange";

    public static final String QUEUE_NAME = "redis_fabulous_queue";

    public static final String BIND_KEY = "fabulous";

    @Bean("redisFabulousExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(CHANGE_NAME).durable(true).build();
    }


    @Bean("redisFabulousQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding itemQueueExchange(@Qualifier("redisFabulousQueue") Queue queue,
                                     @Qualifier("redisFabulousExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(BIND_KEY).noargs();
    }
}
