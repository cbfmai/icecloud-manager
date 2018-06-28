package com.mmtech.icecloud.manager.server.sys.service;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserRoleService {
    SysUserRole addSysUserRole(SysUserRole record);

    int saveUserRole(Long userId, Long roleId);

    boolean deleteSysUserRole(Long id);

    SysUserRole modifySysUserRole(SysUserRole record);

    SysUserRole getSysUserRole(Long id);

    List<SysUserRole> getSysUserRoleByUserId(Long userId);

    List<SysUserRole> getSysUserRoles(Map<String, Object> params);

    Pager<SysUserRole> getPagerSysUserRole(Map<String, Object> params);
}