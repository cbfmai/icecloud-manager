package com.mmtech.icecloud.manager.controller;

import com.mmtech.icecloud.manager.server.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Adam DENG
 * @Date 1/6/2018 21:35
 */

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = {"/", "/index"})
    public String index(Map map) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        map.put("realname", sysUser.getRealname());
        return "index";
    }

    @GetMapping("/login")
    public String toLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) boolean remember, Map<String, Object> map) {

        UsernamePasswordToken upt = new UsernamePasswordToken(username, password, remember);
        Subject subject = SecurityUtils.getSubject();
        String errorMsg = null;
        try {
            subject.login(upt);
        } catch (LockedAccountException e) {
            errorMsg = "账号已被锁定，请联系管理员";
        } catch (AuthenticationException e) {
            LOGGER.error("Failed to login of username " + username + " with exception ", e);
            errorMsg = "用户名或者密码错误";
        }
        if (errorMsg != null) {
            map.put("errorMsg", errorMsg);
            return "login";
        }
        return "redirect:/index";
    }

}
