package com.mmtech.icecloud.manager.config;


import com.alibaba.fastjson.JSON;
import com.mmtech.icecloud.manager.common.PermissionUtils;
import com.mmtech.icecloud.manager.common.entity.enums.StatusEnum;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUser;
import com.mmtech.icecloud.manager.server.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    public static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();

        List<SysRole> roles = sysUser.getRoleList();
        if (roles != null && roles.size() > 0) {
            for (SysRole sysRole : roles) {
                authorizationInfo.addRole(sysRole.getName());
            }
        }

        List<SysPermission> permissions = sysUser.getPermissionList();
        if (permissions != null && permissions.size() > 0) {
            for (SysPermission permission : permissions) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }

        LOGGER.info("Auth info of user {} {}", sysUser.getId(), JSON.toJSON(authorizationInfo));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser sysUser = sysUserService.selectSysUserByUsername(username);
        if (sysUser == null) {
            return null;
        }

        // 判断帐号是否锁定
        if (!(StatusEnum.ON.getCode() == sysUser.getStatus())) {
            // 抛出 帐号锁定异常
            throw new LockedAccountException();
        }

        // 转化成tree
        List<SysPermission> sysPermissions = PermissionUtils.createTree(0L, sysUser.getPermissionList());
        sysUser.setPermissionList(sysPermissions);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser,
                sysUser.getPassword(),
                getName()
        );
        return authenticationInfo;
    }

}