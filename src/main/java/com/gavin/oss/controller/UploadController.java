package com.gavin.oss.controller;

import com.gavin.oss.model.UploadNetObjectVo;
import com.gavin.oss.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@Api(value = "文件上传", tags = "文件上传")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;



    @ApiOperation(httpMethod = "POST", value = "上传文件到type目录point节点", notes = "上传文件到type目录point节点", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/{type}/{point}")
    public void uploadFileToImg(@PathVariable("type") String type, @PathVariable("point") String point, @RequestParam MultipartFile file) {
        // 上传文件到type目录
        uploadService.uploadObject(point, type, file);
    }

    @ApiOperation(httpMethod = "POST", value = "上传网络文件到type目录point节点", notes = "上传文件到type目录point节点", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/{type}/{point}/net")
    public void uploadNetFileToImg(@PathVariable("type") String type, @PathVariable("point") String point, @RequestBody UploadNetObjectVo vo) {
        uploadService.uploadNetObject(point, type, vo.getName(), vo.getUrl());
    }
}
