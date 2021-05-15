package com.gavin.oss.enums;

/**
 * @Author Gavin
 * @Date 2021/4/19 0:34
 */
public enum EndPointEnums {
    /** 上海节点 */
    SH("sh", "gavinimg",  "上海节点")
    /** 香港节点 */,
    HK("hk", "gavinhkimg", "香港节点"),
    ;

    public String code;
    public String bucket;
    public String name;

    EndPointEnums(String code, String bucket, String name) {
        this.code = code;
        this.bucket = bucket;
        this.name = name;
    }

    public static String getBucketNameByPoint(String point){
        if (SH.code.equals(point)) {
            return SH.bucket;
        } else if (HK.code.equals(point)) {
            return HK.bucket;
        } else {
            return null;
        }
    }
}
