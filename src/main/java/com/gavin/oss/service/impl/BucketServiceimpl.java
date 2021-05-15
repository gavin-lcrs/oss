package com.gavin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.BucketInfo;
import com.gavin.oss.comment.ClientUtils;
import com.gavin.oss.enums.EndPointEnums;
import com.gavin.oss.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
@Slf4j
@Service
public class BucketServiceimpl implements BucketService {

    @Override
    public BucketInfo getBucketInfo(String point) {
        OSS ossClient = ClientUtils.getOssClient(point);
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        BucketInfo info = ossClient.getBucketInfo(bucketName);
        ossClient.shutdown();
        return info;
    }

    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void getHnsStatus(String point) {
        OSS ossClient = ClientUtils.getOssClient(point);
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        BucketInfo info = ossClient.getBucketInfo(bucketName);
        log.info("Hnstatus:" , info.getBucket().getServerCRC());
        ossClient.shutdown();
    }
}
