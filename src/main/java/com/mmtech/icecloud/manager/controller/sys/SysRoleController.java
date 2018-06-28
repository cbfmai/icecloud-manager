package com.mmtech.icecloud.manager.controller.sys;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.AjaxResult;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/add")
    public String toAddPage(Model model) {
        return "sys/role/edit";
    }

    @RequestMapping("/permission/add")
    public String toPermissionAddPage(Model model, @RequestParam Long roleId) {
        model.addAttribute("roleId", roleId);
        return "sys/role/setPermission";
    }

    @RequestMapping("/permission/save")
    @ResponseBody
    public AjaxResult saveRolePermission(HttpServletRequest request, @RequestParam Long roleId,
                                         @RequestParam(required = false) String addIds,
                                         @RequestParam(required = false) String deletedIds) {
        return AjaxResult.ofSuccess(sysRoleService.updatePermissionListByRoleId(roleId, addIds, deletedIds));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveSysUser(HttpServletRequest request, @ModelAttribute SysRole sysRole) {
        return AjaxResult.ofSuccess(sysRoleService.modifySysRole(sysRole));
    }


    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveSysUser(HttpServletRequest request, @RequestParam Long roleId, @RequestParam Integer status) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setStatus(status);
        return AjaxResult.ofSuccess(sysRoleService.modifySysRole(sysRole));
    }

    @RequestMapping("/edit")
    public String toEditPage(Model model, @RequestParam Long id) {
        model.addAttribute("sysRole", sysRoleService.getSysRole(id));
        return "sys/role/edit";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String toUserPage(HttpServletRequest request) {
        return "sys/role/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Pager<SysRole> getSysUsers(HttpServletRequest request) {
        Map queryMap = WebUtils.getParametersStartingWith(request, null);
        return sysRoleService.getPagerSysRole(queryMap);
    }
}
