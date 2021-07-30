package com.besscroft.lfs.service;

import com.besscroft.lfs.entity.WebLog;

/**
 * 日志服务
 *
 * @Author Bess Croft
 * @Time 2021/7/24 12:29
 */
public interface LogService {

    /**
     * 保存web日志
     * @param webLog
     * @return
     */
    boolean saveWebLog(WebLog webLog);

}
