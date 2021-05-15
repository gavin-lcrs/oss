package com.gavin.oss.comment;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectRequest;
import com.gavin.oss.enums.EndPointEnums;
import com.gavin.oss.enums.ObjectTypeEnums;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * @Author jiwen.cao
 * @Date 2021/5/14
 * @Description
 */
@Slf4j
@Component
public class OssUtils {

    /**
     * 上传文件到OSS
     * @param point
     * @param directory
     * @param fileName
     * @param file
     * @param inputStream
     * @param byteArrayInputStream
     */
    public static void uploadObject(String point, String directory, String fileName, File file, InputStream inputStream, ByteArrayInputStream byteArrayInputStream){
        // 创建连接
        OSS ossClient = ClientUtils.getOssClient(point);
        // 存储空间名
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        if (StringUtils.isBlank(bucketName)) {
            log.error("存储空间不存在");
            return;
        }
        try {
            String prefix = ObjectTypeEnums.getTypeCodeByKey(directory);
            // 上传路径
            String filePath = null;
            if (StringUtils.isBlank(prefix)) {
                filePath = fileName;
            } else {
                filePath = String.format("%s/%s", prefix, fileName);
            }
            // 上传文件
            if (Objects.nonNull(file)) {
                ossClient.putObject(new PutObjectRequest(bucketName, filePath, file));
                deleteFile(file);
            } else if (Objects.nonNull(inputStream)) {
                ossClient.putObject(bucketName, filePath, inputStream);
            } else if (Objects.nonNull(byteArrayInputStream)) {
                ossClient.putObject(bucketName, filePath, byteArrayInputStream);
            }
        } catch (Exception e) {
            log.error("上传文件失败", e);
        } finally {
            // 关闭连接
            ossClient.shutdown();
        }
    }

    public static List<String> deleteObject(String point, String objName, List<String> objNameList){
        List<String> list = Lists.newArrayList();
        // 创建连接
        OSS ossClient = ClientUtils.getOssClient(point);
        // 存储空间名
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        if (StringUtils.isBlank(bucketName)) {
            log.error("存储空间不存在");
            return list;
        }
        try {
            if (StringUtils.isNotBlank(objName)) {
                ossClient.deleteObject(bucketName, objName);
                list.add(objName);
            } else if (CollectionUtils.isNotEmpty(objNameList)){
                DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(objNameList));
                // 删除成功的结果集
                list.addAll(deleteObjectsResult.getDeletedObjects());
            }
        } catch (Exception e) {
            log.error("删除文件失败", e);
        } finally {
            // 关闭连接
            ossClient.shutdown();
        }
        return list;
    }

    /**
     * 删除文件
     * @param files
     */
    static void deleteFile(File...files){
        for (File f : files) {
            if (f.exists()) {
                f.delete();
            }
        }
    }
}
