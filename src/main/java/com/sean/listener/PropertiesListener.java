package com.sean.listener;

import com.sean.common.GlobalConstant;
import com.sean.util.PropertiesListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(PropertiesListener.class);

    private String propertyFileName;

    public PropertiesListener() {
        this.propertyFileName = GlobalConstant.BASE_FILE_NAMES;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        //把所有指定文件中的key，value放入map缓存中
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}
