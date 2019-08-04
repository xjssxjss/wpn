package com.sean.util;

import com.sean.common.BusinessException;
import com.sean.common.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesListenerConfig {

    private static Logger logger = LoggerFactory.getLogger(PropertiesListenerConfig.class);

    private static Properties properties = null;
    private static String [] fileNames = null;

    public static Map<String,String> propertiesMap = new HashMap<>();

    private static synchronized void processProperties(Properties props) throws BeansException {
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
                try {
                    if(propertiesMap.containsKey(keyStr)){
                        logger.info(keyStr + "集合中已经存在>>>>>>>>>");
                        throw new BusinessException(keyStr + "集合中已经存在>>>>>>>>>");
                    }
                    // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                    propertiesMap.put(keyStr, new String(props.getProperty(keyStr)));
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }
    }

    /**
     * 获取配置文件中的键值对信息
     * @param propertyFileName
     */
    public static void loadAllProperties(String propertyFileName){
        try {
            if(!StringUtil.isEmpty(propertyFileName) && propertyFileName.contains(",")){
                fileNames = propertyFileName.split(",");
                for (int i = 0 ; i < fileNames.length;i++ ){
                    properties = PropertiesLoaderUtils.loadAllProperties(fileNames[i] + GlobalConstant.PROP_SUFFIX);
                    processProperties(properties);
                }
            } else {
                properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName + GlobalConstant.PROP_SUFFIX);
                processProperties(properties);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过map键获取值信息
     * @param name
     * @return
     */
    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }

    /**
     * 获取所有map中键值对信息
     * @return
     */
    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }

}
