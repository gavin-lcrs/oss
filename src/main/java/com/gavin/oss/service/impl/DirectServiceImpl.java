package com.gavin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.gavin.oss.common.ClientUtils;
import com.gavin.oss.common.ResultMessage;
import com.gavin.oss.enums.EndPointEnums;
import com.gavin.oss.enums.ResultCode;
import com.gavin.oss.service.DirectService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.util.List;

/**
 * @Author jiwen.cao
 * @Date 2021/5/17
 * @Description
 */
@Slf4j
@Service
public class DirectServiceImpl implements DirectService {
    @Override
    public ResultMessage getObjDirect(String point) {
        OSS ossClient = ClientUtils.getOssClient(point);
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        ListObjectsV2Request request = new ListObjectsV2Request (bucketName);
        request.setDelimiter("/");
        ListObjectsV2Result result = ossClient.listObjectsV2(request);
        return ResultMessage.success(result.getCommonPrefixes());
    }

    @Override
    public ResultMessage createDirectory(String point, String directoryName) {
        if (StringUtils.isBlank(directoryName)) {
            return ResultMessage.fail(ResultCode.DIRECTORY_NULL);
        }
        if (!directoryName.endsWith("/")) {
            return ResultMessage.fail(ResultCode.POINT_ERROR);
        }
        String content = "";
        OSS ossClient = ClientUtils.getOssClient(point);
        try {
            String bucketName = EndPointEnums.getBucketNameByPoint(point);
            ossClient.putObject(new PutObjectRequest(bucketName, directoryName, new ByteArrayInputStream(content.getBytes())));
        } catch (Exception e) {
            log.error("异常", e);
        }
//        finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//        OssUtils.uploadObject(point, directoryName, null, null, null, null);
//        OSS ossClient = ClientUtils.getOssClient(point);
//        String bucketName = EndPointEnums.getBucketNameByPoint(point);
//        String endPoint = ClientUtils.getEndPoint(point);
//        ListObjectsV2Request request = new ListObjectsV2Request (bucketName);
//        try {
////            CreateDirectoryRequest createDirectoryRequest = new CreateDirectoryRequest(bucketName, directoryName);
////            ossClient.createDirectory(createDirectoryRequest);
//
//
//        } catch (Exception e) {
//            log.error("创建OSS目录异常", e);
//            return ResultMessage.fail("创建OSS目录异常");
//        } finally {
//            // 关闭连接
//            ossClient.shutdown();
//        }
        return ResultMessage.success();

    }

    @Override
    public ResultMessage deleteDirectory(String point, String directoryName) {
        OSS ossClient = ClientUtils.getOssClient(point);
        try {
            // 删除目录及目录下的所有文件。
            String nextMarker = null;
            ObjectListing objectListing = null;
            do {
                String bucketName = EndPointEnums.getBucketNameByPoint(point);
                ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName)
                        .withPrefix(directoryName)
                        .withMarker(nextMarker);
                objectListing = ossClient.listObjects(listObjectsRequest);
                if (objectListing.getObjectSummaries().size() > 0) {
                    List<String> keys = Lists.newArrayList();
                    for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                        log.info("key name:{}", s.getKey());
                        keys.add(s.getKey());
                    }
//                    DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName).withKeys(keys).withEncodingType("url");
                    DeleteObjectsResult result = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys).withEncodingType("url"));
                    List<String> deletedObjects = result.getDeletedObjects();
                    for (String s : deletedObjects) {
                        log.info("deleted : {}", URLDecoder.decode(s, "UTF-8"));
                    }
                }
                nextMarker = objectListing.getNextMarker();
            } while (objectListing.isTruncated());
        } catch (Exception e) {
            log.error("异常：", e);
        }
//        finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
        return ResultMessage.success();
    }
}
