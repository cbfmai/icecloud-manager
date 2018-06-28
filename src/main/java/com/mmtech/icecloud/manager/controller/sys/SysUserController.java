package com.mmtech.icecloud.manager.controller.sys;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.AjaxResult;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUser;
import com.mmtech.icecloud.manager.server.sys.service.SysRoleService;
import com.mmtech.icecloud.manager.server.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/add")
    public String toAddPage(Model model) {
        List<SysRole> roleList = sysRoleService.getSysRoles(new HashMap<>());
        model.addAttribute("sysRoleList", roleList);
        return "sys/user/edit";
    }

    @RequestMapping("/edit")
    public String toEditPage(Model model, @RequestParam Long id) {
        model.addAttribute("sysRoleList", sysRoleService.getSysRoles(new HashMap<>()));
        model.addAttribute("sysUser", sysUserService.getSysUser(id));
        return "sys/user/edit";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult resetPassword(@RequestParam Long userId, @RequestParam String password) {
        return AjaxResult.ofSuccess(sysUserService.resetPassword(userId, password));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveSysUser(HttpServletRequest request, @ModelAttribute @Valid SysUser sysUser, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }
            return AjaxResult.ofFail(sb.toString());
        }
        return AjaxResult.ofSuccess(sysUserService.modifySysUser(sysUser, true));
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult changeSysUserStatus(@RequestParam Long userId, @RequestParam Integer status) {
        SysUser sysUser = sysUserService.getSysUser(userId);
        sysUser.setStatus(status);
        return AjaxResult.ofSuccess(sysUserService.modifySysUser(sysUser, false));
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String toUserPage(HttpServletRequest request) {
        return "sys/user/index";
    }

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getUserMenu() {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        return AjaxResult.ofSuccess(sysUser.getPermissionList());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Pager<SysUser> getSysUsers(HttpServletRequest request) {
        Map queryMap = WebUtils.getParametersStartingWith(request, null);
        return sysUserService.getPagerSysUser(queryMap);
    }
}
