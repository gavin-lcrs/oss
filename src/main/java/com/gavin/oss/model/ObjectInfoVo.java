package com.gavin.oss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Gavin
 * @Date 2021/5/16 1:49
 */
@Data
public class ObjectInfoVo {
    /** 文件key */
    @ApiModelProperty(value = "文件key")
    private String key;
    /** 文件名 */
    @ApiModelProperty(value = "文件名")
    private String objName;
    /** 文件链接URL */
    @ApiModelProperty(value = "文件链接URL")
    private String url;
    /** 文件所在目录 */
    @ApiModelProperty(value = "文件所在目录")
    private String directory;

}
