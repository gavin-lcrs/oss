package com.gavin.oss.service;

import com.aliyun.oss.model.BucketInfo;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
public interface BucketService {

    BucketInfo getBucketInfo(String point);

    void createBucket(String bucketName);

    void getHnsStatus(String point);
}
