package com.mmtech.icecloud.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Adam DENG
 * @Date 2018/6/6 11:38
 */
@Controller
public class ExceptionController {


    @GetMapping("/403")
    public String notAuth() {
        return "403";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/500")
    public String serverException() {
        return "500";
    }

}
