package com.gavin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsV2Request;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.gavin.oss.common.ClientUtils;
import com.gavin.oss.common.OssUtils;
import com.gavin.oss.common.ResultMessage;
import com.gavin.oss.enums.EndPointEnums;
import com.gavin.oss.enums.ResultCode;
import com.gavin.oss.service.DirectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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

        OssUtils.uploadObject(point, directoryName, null, null, null, null);

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
}
