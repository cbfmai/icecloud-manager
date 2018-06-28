package com.mmtech.icecloud.manager.server.sys.dao;

import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysRoleCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    long countByCondition(SysRoleCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByCondition(SysRoleCriteria example);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}