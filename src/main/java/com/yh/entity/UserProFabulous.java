package com.yh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.BaseEntity;
import lombok.Data;

/**
 * @author yh
 */
@Data
@TableName("user_pro_fabulous")
public class UserProFabulous extends BaseEntity<UserProFabulous> {
    private static final long serialVersionUID=1L;
    @TableId(value="id",type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long productId;

    private Integer status;

    private Integer num;
}
