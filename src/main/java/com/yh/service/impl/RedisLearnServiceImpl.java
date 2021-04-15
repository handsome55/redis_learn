package com.yh.service.impl;

import com.yh.common.TableVO;
import com.yh.config.RabbitMqConfig;
import com.yh.entity.Product;
import com.yh.entity.UserProFabulous;
import com.yh.mapper.ProductMapper;
import com.yh.mapper.UserProFabulousMapper;
import com.yh.service.IRedisLearnService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yh
 */
@Service
@AllArgsConstructor
public class RedisLearnServiceImpl implements IRedisLearnService {
    private final RedisTemplate redisTemplate;
    private final static String REDIS_KEY = "product";
    private final UserProFabulousMapper proFabulousMapper;
    private final ProductMapper productMapper;
    private final RabbitTemplate rabbitTemplate;


    @Override
    public TableVO rankFabulous(Integer pageNum,Integer pageSize) {
        TableVO tableVO = new TableVO();
        Long nums = redisTemplate.opsForZSet().zCard(REDIS_KEY);
        if(nums < 1){
            return null;
        }
        List<Product> products = new ArrayList<>();
        Set<String> range = redisTemplate.opsForZSet().reverseRange(REDIS_KEY, pageNum, pageSize);
        for(String proId : range){
            Product product = productMapper.selectById(proId.split("_")[1]);
            Double score = redisTemplate.opsForZSet().score(REDIS_KEY, proId);
            product.setFabulous(score.intValue());
            products.add(product);
        }
        tableVO.setTotal(nums);
        tableVO.setPageNum(pageNum);
        tableVO.setPageSize(pageSize);
        tableVO.setRecords(products);
        return tableVO;
    }

    @Override
    public UserProFabulous proFabulous(Long proId, Integer status) {
        //模拟传入不同商品id
        proId = Long.valueOf((int)(1+Math.random()*15)+"");
        UserProFabulous userProFabulous = new UserProFabulous();
        userProFabulous.setNum(1);
        userProFabulous.setProductId(proId);
        userProFabulous.setStatus(1);
        userProFabulous.setUserId(1L);
        proFabulousMapper.insert(userProFabulous);
        rabbitTemplate.convertAndSend(RabbitMqConfig.CHANGE_NAME,RabbitMqConfig.BIND_KEY,proId);
        return userProFabulous;
    }

    @Override
    public Long proRank(Long proId) {
        String value = REDIS_KEY+"_"+proId;
        Long rank = redisTemplate.opsForZSet().rank(REDIS_KEY, value);
        return rank;
    }
}
