package com.gavin.oss.controller;

import com.gavin.oss.model.ObjectListVo;
import com.gavin.oss.service.OssService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author jiwen.cao
 * @Date 2021/4/15
 * @Description
 */
@Slf4j
@Api(value = "文件管理", tags = "文件管理")
@RestController
@RequestMapping("/obj")
public class ObjectController {

    @Resource
    private OssService ossService;

    @ApiOperation(httpMethod = "GET", value = "获取节点文件URL列表", notes = "获取节点文件URL列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(paramType = "query", name = "point", value = "节点", required = true)
    @GetMapping("/{point}/list")
    public List<String> getObjectList(@PathVariable("point") String point){
        return ossService.getObjUrlList(point, null);
    }

    @ApiOperation(httpMethod = "GET", value = "查看type目录point节点文件列表", notes = "查看type目录point节点文件列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "next", value = "下一个token", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "after", value = "下一个文件的文件名", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping("/{prefix}/{point}/list")
    public ObjectListVo getObjNextList(@PathVariable("prefix") String prefix, @PathVariable("point") String point,
                                       @Param("next") String next, @Param("after") String after) {
        return ossService.getObjUrlNextList(point, prefix, next, after);
    }

//    @RequestMapping("/img/{point}/list")
//    public List<String> getImgList(@PathVariable("point") String point){
//        return ossService.getObjUrlList(point, ObjectTypeEnums.IMG.code);
//    }
//
//    @RequestMapping("/doc/{point}/list")
//    public List<String> getDocList(@PathVariable("point") String point){
//        return ossService.getObjUrlList(point, ObjectTypeEnums.DOC.code);
//    }
}
