package com.yh.service;

import com.yh.common.TableVO;
import com.yh.entity.UserProFabulous;

import java.util.List;

/**
 * @author yh
 */
public interface IRedisLearnService {

    TableVO rankFabulous(Integer pageNum,Integer pageSize);

    /**
     * 商品点赞
     * @param proId 商品id
     * @param status 1点赞 0取消点赞
     */
    UserProFabulous proFabulous(Long proId, Integer status);

    Long proRank(Long proId);
}
