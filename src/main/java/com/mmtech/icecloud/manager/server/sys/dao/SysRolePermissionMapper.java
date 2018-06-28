package com.mmtech.icecloud.manager.server.sys.dao;

import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermissionCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {
    long countByCondition(SysRolePermissionCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    List<SysRolePermission> selectByCondition(SysRolePermissionCriteria example);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByConditionSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionCriteria example);

    int updateByCondition(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionCriteria example);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}