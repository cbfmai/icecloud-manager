package com.mmtech.icecloud.manager.server.sys.dao;

import com.mmtech.icecloud.manager.server.sys.entity.SysUser;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    long countByCondition(SysUserCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByCondition(SysUserCriteria example);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectSysUserByUsername(String username);

}