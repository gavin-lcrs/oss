package com.gavin.oss.model;

import lombok.Data;

import java.util.List;

/**
 * @Author Gavin
 * @Date 2021/5/16 1:46
 */
@Data
public class ObjectListVo {
    private Integer total;
    private Integer pageSize;
    private Integer pageNum;
    private String startAfter;
    private String continuationToken;
    private String nextContinuationToken;

    private List<ObjectInfoVo> list;
}
