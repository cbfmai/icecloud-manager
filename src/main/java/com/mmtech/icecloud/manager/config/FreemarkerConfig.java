package com.mmtech.icecloud.manager.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * @author Adam DENG
 * @Date 2018/6/19 16:27
 */
@Component
public class FreemarkerConfig {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
        freeMarkerConfigurer.getConfiguration().setNumberFormat("0.##");
        freeMarkerConfigurer.getConfiguration().setDateTimeFormat("yyy-MM-dd HH:mm:ss");
        freeMarkerConfigurer.getConfiguration().setLocale(Locale.SIMPLIFIED_CHINESE);
        freeMarkerConfigurer.getConfiguration().setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.getConfiguration().setURLEscapingCharset("UTF-8");
    }

}
