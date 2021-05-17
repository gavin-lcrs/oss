package com.gavin.oss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Gavin
 * @Date 2021/5/16 1:46
 */
@Data
public class ObjectListVo {
    /** 总数 */
    @ApiModelProperty(value = "总数")
    private Integer total;
    private Integer pageSize;
    private Integer pageNum;
    /** 总数 */
    @ApiModelProperty(value = "文件起始")
    private String startAfter;
    /** 总数 */
    @ApiModelProperty(value = "文件列表当前token")
    private String continuationToken;
    /** 文件下一个token */
    @ApiModelProperty(value = "文件列表下一个token")
    private String nextContinuationToken;

    /** 文件列表 */
    @ApiModelProperty(value = "文件列表")
    private List<ObjectInfoVo> list;
}
