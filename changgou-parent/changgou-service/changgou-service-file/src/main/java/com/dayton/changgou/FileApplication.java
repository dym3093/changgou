package com.dayton.changgou;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * FDFS文件管理 <br/>
 * exclude = DataSourceAutoConfiguration.class -> 排除数据库自动加载
 * @author Martin Deng
 * @since 2020-10-01 22:58
 */
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileApplication {

	public static void main(String[] args) {

	}

}
