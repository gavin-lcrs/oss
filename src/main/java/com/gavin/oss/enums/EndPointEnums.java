package com.gavin.oss.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Gavin
 * @Date 2021/4/19 0:34
 */
public enum EndPointEnums {
    /** 香港节点 */
    HK("hk", "lcrs-bucket", "香港节点"),
    ;

    public String code;
    public String bucket;
    public String desc;

    EndPointEnums(String code, String bucket, String desc) {
        this.code = code;
        this.bucket = bucket;
        this.desc = desc;
    }

    public static String getBucketNameByPoint(String point){
        if (StringUtils.isNotBlank(point)) {
            for (EndPointEnums pointEnums : EndPointEnums.values()) {
                if (pointEnums.code.equals(point)) {
                    return pointEnums.bucket;
                }
            }
        }
        return null;
    }

    public static boolean checkAvailable(String point){
        for (EndPointEnums pointEnums : EndPointEnums.values()) {
            if (pointEnums.code.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUnAvailable(String point){
        return !checkAvailable(point);
    }
}
