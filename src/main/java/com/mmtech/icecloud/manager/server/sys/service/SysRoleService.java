package com.mmtech.icecloud.manager.server.sys.service;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    SysRole addSysRole(SysRole record);

    boolean deleteSysRole(Long id);

    SysRole modifySysRole(SysRole record);

    SysRole getSysRole(Long id);

    List<SysRole> getSysRoles(Map<String, Object> params);

    Pager<SysRole> getPagerSysRole(Map<String, Object> params);

    List<SysPermission> getSysPermissionListByRoleId(Long roleId);

    boolean updatePermissionListByRoleId(Long roleId, String addIds, String deletedIds);
}