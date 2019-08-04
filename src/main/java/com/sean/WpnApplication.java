package com.sean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WpnApplication {

	private static Logger logger = LoggerFactory.getLogger(WpnApplication.class);

	public static void main(String[] args) {
		logger.info("wpn启动开始>>>>>>>>>>>>>>>>>>>>>>");
		SpringApplication.run(WpnApplication.class, args);
		logger.info("wpn启动结束>>>>>>>>>>>>>>>>>>>>>>");
	}

}
