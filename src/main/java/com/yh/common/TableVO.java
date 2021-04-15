package com.yh.common;

import lombok.Data;

import java.util.List;

/**
 * 列表数据
 * @author yh
 */
@Data
public class TableVO {

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

    private List<?> records;

    public TableVO(){

    }

    public TableVO(Integer pageNum,Integer pageSize,Long total,List<?> records){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.records = records;
    }
}
