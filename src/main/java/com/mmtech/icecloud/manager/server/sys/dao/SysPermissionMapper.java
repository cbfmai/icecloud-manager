package com.mmtech.icecloud.manager.server.sys.dao;

import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermissionCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    long countByCondition(SysPermissionCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByCondition(SysPermissionCriteria example);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}