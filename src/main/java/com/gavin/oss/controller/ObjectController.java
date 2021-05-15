package com.gavin.oss.controller;

import com.gavin.oss.model.ObjectListVo;
import com.gavin.oss.service.OssService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jiwen.cao
 * @Date 2021/4/15
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/obj")
public class ObjectController {

    @Autowired
    private OssService ossService;

    @RequestMapping("/{point}/list")
    public List<String> getObjectList(@PathVariable("point") String point){
        return ossService.getObjUrlList(point, null);
    }


//    @RequestMapping("/{type}/{point}/list")
//    public List<String> getObjList(@PathVariable("type") String type, @PathVariable("point") String point){
//        return ossService.getObjUrlList(point, type);
//    }

    @RequestMapping("/{prefix}/{point}/list")
    public ObjectListVo getObjNextList(@PathVariable("prefix") String prefix, @PathVariable("point") String point,
                                       @Param("next") String next, @Param("maker") String maker) {

        return ossService.getObjUrlNextList(point, prefix, next, maker);
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
