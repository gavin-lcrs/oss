package com.gavin.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
public interface UploadService {

    /**
     * 文件上传
     * @param point 节点标识
     * @param directory
     * @param file 文件流
     */
    void uploadObject(String point, String directory, MultipartFile file);

    /**
     * 根据网络地址上传文件
     * @param point
     * @param directory
     * @param name
     * @param url
     */
    void uploadNetObject(String point, String directory, String name, String url);
}
