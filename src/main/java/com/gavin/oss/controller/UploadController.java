package com.gavin.oss.controller;

import com.gavin.oss.model.UploadNetObjectVo;
import com.gavin.oss.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping(value = "/{type}/{point}")
    public void uploadFileToImg(@PathVariable("type") String type, @PathVariable("point") String point, @RequestParam MultipartFile file) {
        // 上传文件到type目录
        uploadService.uploadObject(point, type, file);
    }

    @PostMapping(value = "/{type}/{point}/net")
    public void uploadNetFileToImg(@PathVariable("type") String type, @PathVariable("point") String point, @RequestBody UploadNetObjectVo vo) {
        // 上传网络文件到type目录
        uploadService.uploadNetObject(point, type, vo.getName(), vo.getUrl());
    }
}
