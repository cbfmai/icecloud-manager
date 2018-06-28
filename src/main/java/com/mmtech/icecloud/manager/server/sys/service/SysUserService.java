package com.mmtech.icecloud.manager.server.sys.service;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUser;

import java.util.List;
import java.util.Map;


public interface SysUserService {

    SysUser addSysUser(SysUser record);

    boolean deleteSysUser(Long id);

    SysUser modifySysUser(SysUser record, boolean modifyRole);

    SysUser getSysUser(Long id);

    SysUser resetPassword(Long userId, String password);

    List<SysUser> getSysUsers(Map<String, Object> params);

    Pager<SysUser> getPagerSysUser(Map<String, Object> params);

    List<SysRole> getRoleListById(Long id);

    SysUser findByUsername(String username);

    SysUser selectSysUserByUsername(String username);
}