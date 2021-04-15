package com.yh.controller;

import com.yh.common.BaseResult;
import com.yh.common.TableVO;
import com.yh.entity.UserProFabulous;
import com.yh.service.IRedisLearnService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品
 * @author yh
 */
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final IRedisLearnService redisLearnService;

    @GetMapping("/rankFabulous")
    public BaseResult rankFabulous(Integer pageNum,Integer pageSize){
        TableVO tableVO = redisLearnService.rankFabulous(pageNum,pageSize);
        return BaseResult.ok(tableVO);
    }

    @PostMapping("/proFabulous")
    public BaseResult proFabulous(Long proId,Integer status){
        UserProFabulous proFabulous = redisLearnService.proFabulous(proId,status);
        return BaseResult.ok(proFabulous);
    }

    @PostMapping("/proRank")
    public BaseResult proRank(Long proId){
        Long rank = redisLearnService.proRank(proId);
        return BaseResult.ok(rank);
    }
}
