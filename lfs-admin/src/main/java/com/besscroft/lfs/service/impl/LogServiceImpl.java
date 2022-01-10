package com.besscroft.lfs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.besscroft.lfs.entity.WebLog;
import com.besscroft.lfs.repository.LogRepository;
import com.besscroft.lfs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Bess Croft
 * @Time 2021/7/24 12:30
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional
    public boolean saveWebLog(WebLog webLog) {
        WebLog log = logRepository.save(webLog);
        return ObjectUtil.isNotEmpty(log);
    }
}
