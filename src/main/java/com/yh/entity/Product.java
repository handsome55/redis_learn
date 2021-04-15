package com.yh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.BaseEntity;
import lombok.Data;

/**
 * 商品
 * @author yh
 */
@Data
@TableName("product")
public class Product extends BaseEntity<Product> {
    private static final long serialVersionUID=1L;
    private Long id;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 点赞数量
     */
    private Integer fabulous;
}
