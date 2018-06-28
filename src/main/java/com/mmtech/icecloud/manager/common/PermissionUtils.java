package com.mmtech.icecloud.manager.common;

import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam DENG
 *
 */
public class PermissionUtils {


    public static List<SysPermission> createTree(Long parentId, List<SysPermission> permissions) {

        if (parentId == null || permissions == null || permissions.size() == 0) {
            return null;
        }

        List<SysPermission> permissionList = new ArrayList<>();

        for (SysPermission current : permissions) {
            if (current.getParentId().compareTo(parentId) == 0) {
                current.setChildren(createTree(current.getId(), permissions));
                permissionList.add(current);
            }
        }
        return permissionList;

    }

}
