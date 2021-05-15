package com.gavin.oss.controller;

import com.gavin.oss.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Resource
    private DownloadService downloadService;

//    @GetMapping(value = "/{type}/{point}")
//    public void uploadFileToImg(@PathVariable("type") String type, @PathVariable("point") String point, String fileName) {
//    }
}
