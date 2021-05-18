package com.gavin.oss.service;

import com.gavin.oss.common.ResultMessage;

/**
 * @Author jiwen.cao
 * @Date 2021/5/17
 * @Description
 */
public interface DirectService {

    ResultMessage getObjDirect(String point);

    ResultMessage createDirectory(String point, String directoryName);
}
