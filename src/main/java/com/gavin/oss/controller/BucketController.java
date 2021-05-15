package com.gavin.oss.controller;

import com.aliyun.oss.model.BucketInfo;
import com.gavin.oss.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private BucketService bucketService;

    @RequestMapping("/info/{point}")
    public BucketInfo getBucketInfo(@PathVariable("point") String point){
        return bucketService.getBucketInfo(point);
    }

}
