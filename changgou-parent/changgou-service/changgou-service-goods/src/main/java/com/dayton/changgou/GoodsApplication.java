package com.dayton.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Martin Deng
 * @since 2020-09-16 21:40
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.dayton.changgou.dao")
public class GoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}

}
