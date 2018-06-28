package com.mmtech.icecloud.manager.server.sys.service;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermission;
import java.util.List;
import java.util.Map;

public interface SysRolePermissionService {
    SysRolePermission addSysRolePermission(SysRolePermission record);

    boolean deleteSysRolePermission(Long id);

    SysRolePermission modifySysRolePermission(SysRolePermission record);

    SysRolePermission getSysRolePermission(Long id);

    List<SysRolePermission> getSysRolePermissions(Map<String, Object> params);

    Pager<SysRolePermission> getPagerSysRolePermission(Map<String, Object> params);
}