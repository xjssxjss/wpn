package com.sean.common;

import com.sean.util.PropertiesListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 */
@Component
public class ScheduledTask {
    Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    /**
     * 更新维修站财务变更审批之后的之后的状态
     *  @throws Exception
     */
    //@Scheduled(cron = "*/20 * * * * ?")
    public void updatePspDbByPsiBuyBackData() throws Exception{
        logger.info("所有集合对象>>>>>>>>>>>>>>>>"+PropertiesListenerConfig.getAllProperty());
    }
}
