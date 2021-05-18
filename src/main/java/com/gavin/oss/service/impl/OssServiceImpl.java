package com.gavin.oss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsV2Request;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.gavin.oss.common.ClientUtils;
import com.gavin.oss.common.OssUtils;
import com.gavin.oss.common.ResultMessage;
import com.gavin.oss.enums.EndPointEnums;
import com.gavin.oss.enums.ObjectTypeEnums;
import com.gavin.oss.enums.ResultCode;
import com.gavin.oss.model.ObjectInfoVo;
import com.gavin.oss.model.ObjectListVo;
import com.gavin.oss.service.OssService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Gavin
 * @Date 2021/4/19 1:23
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Value("${oss.maxKeys}")
    private Integer maxKeys;

    @Override
    public List<String> getObjUrlList(String point, String directory) {

        OSS ossClient = ClientUtils.getOssClient(point);
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        String endPoint = ClientUtils.getEndPoint(point);

        String prefix = ObjectTypeEnums.getTypeCodeByKey(directory);

        log.info("简单列举文件");
//        String KeyPrefix = "img";
        ObjectListing objectListing = ossClient.listObjects(bucketName, prefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> list = Lists.newArrayList();
        sums.forEach(s -> {
            log.info("{}", s.getKey());
            list.add(String.format("http://%s.%s/%s", bucketName, endPoint, s.getKey()));
        });
        return list;
    }

    @Override
    public ResultMessage getObjUrlNextList(String point, String prefix, String next, String after) {
        if (EndPointEnums.checkUnAvailable(point)) {
            return ResultMessage.fail(ResultCode.POINT_ERROR);
        }
        if (StringUtils.isNotBlank(prefix) && ObjectTypeEnums.checkUnAvailable(prefix)) {
            return ResultMessage.fail(ResultCode.PREFIX_ERROR);
        }

        OSS ossClient = ClientUtils.getOssClient(point);
        String bucketName = EndPointEnums.getBucketNameByPoint(point);
        String endPoint = ClientUtils.getEndPoint(point);
        ListObjectsV2Request request = new ListObjectsV2Request (bucketName);
        request.setMaxKeys(maxKeys);
        if (StringUtils.isNotBlank(after)) {
            request.setStartAfter(after);
        } else if (StringUtils.isNotBlank(next)) {
            request.setContinuationToken(next);
        }
        String directory = ObjectTypeEnums.getTypeCodeByKey(prefix);
        if (StringUtils.isNotBlank(directory)) {
            request.setPrefix(directory);
        }

        ObjectListVo vo = new ObjectListVo();
        try {
            ListObjectsV2Result result = ossClient.listObjectsV2(request);
            log.info("请求结果：{}", JSONObject.toJSONString(result));
            List<OSSObjectSummary> summaryList = result.getObjectSummaries();
            Integer total = result.getKeyCount();
            String startAfter = result.getStartAfter();
            String nextContinuationToken = result.getNextContinuationToken();
            String continuationToken = request.getContinuationToken();

            vo.setTotal(total);
            vo.setPageSize(maxKeys);
            vo.setStartAfter(startAfter);
            vo.setContinuationToken(continuationToken);
            vo.setNextContinuationToken(nextContinuationToken);
            List<ObjectInfoVo> list = Lists.newArrayList();
            summaryList.forEach(obj -> {
                ObjectInfoVo infoVo = new ObjectInfoVo();
                String key = obj.getKey();
                infoVo.setKey(key);
                infoVo.setObjName(key.substring(key.lastIndexOf("/") + 1));
                infoVo.setUrl(String.format("http://%s.%s/%s", bucketName, endPoint, key));
                log.info("{}", key);
                list.add(infoVo);
            });
            vo.setList(list);
        } catch (Exception e) {
            log.error("查询oss列表异常", e);
            return ResultMessage.fail("查询oss列表异常");
        } finally {
            // 关闭连接
            ossClient.shutdown();
        }
        return ResultMessage.success(vo);
    }

    @Override
    public void deleteObject(String point, String objName) {
        OssUtils.deleteObject(point, objName, null);
    }

    @Override
    public void deleteObject(String point, List<String> objNameList) {
        OssUtils.deleteObject(point, null, objNameList);
    }
}
