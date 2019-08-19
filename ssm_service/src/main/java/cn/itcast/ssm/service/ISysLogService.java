package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.SysLog;

import java.util.List;


public interface ISysLogService {

    public void save(SysLog sysLog);
    public List<SysLog> findAll();
}
