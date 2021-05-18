package com.gavin.oss.controller;

import com.gavin.oss.common.ResultMessage;
import com.gavin.oss.service.DirectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/{point}/direct")
public class DirectController {

    @Resource
    private DirectService directService;

    @GetMapping("/list")
    public ResultMessage getObjDirect(@PathVariable("point") String point){
        return directService.getObjDirect(point);
    }

    @PostMapping("/create")
    public ResultMessage createObjDirect(@PathVariable("point") String point){
        directService.createDirectory(point, "/obj/AAA");
        return ResultMessage.success();
    }

}
