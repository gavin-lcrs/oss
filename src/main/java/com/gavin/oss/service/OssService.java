package com.gavin.oss.service;

import com.gavin.oss.model.ObjectListVo;

import java.util.List;

/**
 * @Author Gavin
 * @Date 2021/4/19 1:21
 */
public interface OssService {

    /**
     * 图片列表
     * @param point 节点标识
     * @param directory 文件目录
     * @return
     */
    List<String> getObjUrlList(String point, String directory);
    ObjectListVo getObjUrlNextList(String point, String prefix, String next, String maker);



    /**
     * 删除文件
     * @param objName
     */
    void deleteObject(String point, String objName);

    /**
     * 删除文件集
     * @param objNameList
     */
    void deleteObject(String point, List<String> objNameList);

}
