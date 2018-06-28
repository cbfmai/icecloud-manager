package com.mmtech.icecloud.manager.server.sys.dao;

import com.mmtech.icecloud.manager.server.sys.entity.SysUserRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRoleCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    long countByCondition(SysUserRoleCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByCondition(SysUserRoleCriteria example);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}