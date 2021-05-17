package com.gavin.oss.service.impl;

import com.gavin.oss.common.OssUtils;
import com.gavin.oss.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author jiwen.cao
 * @Date 2021/5/14
 * @Description
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public void uploadObject(String point, String directory, MultipartFile multfile) {
        if (Objects.isNull(multfile)) {
            log.error("上传文件为空");
        }
        // 文件名
        String fileName = multfile.getOriginalFilename();
        // 上传文件转File
        File file = transformMultipartFileToFile(multfile);
        OssUtils.uploadObject(point, directory, fileName, file, null, null);
    }

    @Override
    public void uploadNetObject(String point, String directory, String name, String url) {
        if (StringUtils.isBlank(url)) {
            log.error("网络文件地址为空");
        }
        String fileName = null;
        if (StringUtils.isNotBlank(name)) {
            fileName = name;
        } else {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        }
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            log.error("获取网络文件数据流失败", e);
        }
        OssUtils.uploadObject(point, directory, fileName, null, inputStream, null);
    }


    /**
     * MultipartFile 转 File
     * @param multfile
     * @return
     */
    private File transformMultipartFileToFile(MultipartFile multfile){
        // 文件名
        String fileName = multfile.getOriginalFilename();
        // 文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(UUID.randomUUID().toString(), prefix);
            multfile.transferTo(file);
        } catch (IOException e) {
            log.error("MultipartFile 转 File 失败", e);
        }
        return file;
    }

}
