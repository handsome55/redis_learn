package com.yh.mq;

import com.yh.config.RabbitMqConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author yh
 */
@Component
@AllArgsConstructor
public class FabulousHandle {
    private final RedisTemplate redisTemplate;
    private final static String REDIS_KEY = "product";


    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void msg(Long proId){
        System.out.println("=================================");
        String value = REDIS_KEY+"_"+proId;
        //查询是否已存在 不存在插入 存在更新score
        Double score = redisTemplate.opsForZSet().score(REDIS_KEY, value);
        if(StringUtils.isEmpty(score)){
            redisTemplate.opsForZSet().add(REDIS_KEY,value,1d);
        }else {
            score = score +1d;
            redisTemplate.opsForZSet().add(REDIS_KEY,value,score);
        }
    }
}
