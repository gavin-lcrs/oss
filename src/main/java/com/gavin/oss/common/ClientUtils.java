package com.gavin.oss.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gavin.oss.enums.EndPointEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Gavin
 * @Date 2021/4/19 0:32
 */
@Component
public class ClientUtils {
    private static String hkEndpoint;
    private static String accessKeyId;
    private static String accessKeySecret;

    @Value("${oss.endpoint.hk}")
    public void setHkEndpoint(String hk){
        hkEndpoint = hk;
    }

    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String akId) {
        accessKeyId = akId;
    }

    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String akS) {
        accessKeySecret = akS;
    }

    public static OSS getOssClient(String point){
        return new OSSClientBuilder().build(String.format("http://%s", getEndPoint(point)), accessKeyId, accessKeySecret);
    }

    public static String getEndPoint(String point){
        String endPoint = null;
        if (EndPointEnums.HK.code.equals(point)) {
            endPoint = hkEndpoint;
        }
        return endPoint;
    }




}
