package com.mmtech.icecloud.manager.controller.sys;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.AjaxResult;
import com.mmtech.icecloud.manager.common.PermissionUtils;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import com.mmtech.icecloud.manager.server.sys.service.SysPermissionService;
import com.mmtech.icecloud.manager.server.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/add")
    public String toAddPage(Model model) {
        List<SysPermission> sysPermissionList = sysPermissionService.getSysPermissions(new HashMap<>());
        model.addAttribute("menuTree", PermissionUtils.createTree(0L, sysPermissionList));
        return "sys/menu/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveSysUser(HttpServletRequest request, @ModelAttribute SysPermission sysPermission) {
        return AjaxResult.ofSuccess(sysPermissionService.modifySysPermission(sysPermission));
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String toUserPage(HttpServletRequest request) {
        return "sys/menu/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Pager<SysPermission> getSysUsers(HttpServletRequest request) {
        Map queryMap = WebUtils.getParametersStartingWith(request, null);
        return sysPermissionService.getPagerSysPermission(queryMap);
    }

    @RequestMapping(value = "/getAllPermissions", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getAllPermissions(HttpServletRequest request, @RequestParam Long roleId) {
        Map queryMap = WebUtils.getParametersStartingWith(request, null);
        List<SysPermission> rolePermissionList = sysRoleService.getSysPermissionListByRoleId(roleId);
        List<SysPermission> allPermissionList = sysPermissionService.getSysPermissions(queryMap);

        for (SysPermission sysPermission : allPermissionList) {
            if (rolePermissionList.contains(sysPermission)) {
                sysPermission.setChecked(true);
            }
        }

        return AjaxResult.ofSuccess(allPermissionList);
    }

}
