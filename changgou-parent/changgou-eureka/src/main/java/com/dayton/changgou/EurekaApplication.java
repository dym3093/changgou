package com.dayton.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Martin Deng
 * @since 2020-09-14 21:37
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	/**
	 * 加载启动类，以启动类为当前SpringBoot的配置标准
	 * @param args	参数
	 * @author Martin Deng
	 * @since 2020/9/14 22:10
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
