package com.gavin.oss.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author jiwen.cao
 * @Date 2021/5/6
 * @Description
 */
public enum ObjectTypeEnums {

    /** 图片 */
    IMG("img","图片"),
    /** 文档 */
    DOC("doc","文档"),
    /** 对象文件 */
    OBJ("obj","对象文件"),
    ;

    public String code;
    public String name;

    ObjectTypeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ObjectTypeEnums getObjectType(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ObjectTypeEnums type : ObjectTypeEnums.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static String getTypeCodeByKey(String key){
        for (ObjectTypeEnums type : ObjectTypeEnums.values()) {
            if (type.code.equals(key)) {
                return type.code;
            }
        }
        return null;
    }


    public static boolean checkAvailable(String key){
        for (ObjectTypeEnums typeEnums: ObjectTypeEnums.values()) {
            if (typeEnums.code.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUnAvailable(String key){
        return !checkAvailable(key);
    }
}
