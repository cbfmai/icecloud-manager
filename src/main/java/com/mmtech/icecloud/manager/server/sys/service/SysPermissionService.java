package com.mmtech.icecloud.manager.server.sys.service;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import java.util.List;
import java.util.Map;

public interface SysPermissionService {
    SysPermission addSysPermission(SysPermission record);

    boolean deleteSysPermission(Long id);

    SysPermission modifySysPermission(SysPermission record);

    SysPermission getSysPermission(Long id);

    List<SysPermission> getSysPermissions(Map<String, Object> params);

    Pager<SysPermission> getPagerSysPermission(Map<String, Object> params);

    Pager<SysPermission> getSysPermissionByParentId(Long parentId);
}