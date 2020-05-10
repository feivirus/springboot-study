package com.feivirus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 *  1.一些有技术深度的场景
 *  2.一些比较好的spring/springboot，或者web相关的写法,代码规范
 */

@SpringBootApplication
@ComponentScan(basePackages = { "com.feivirus"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = false)
@MapperScan(basePackages = { "com.feivirus.demo.dao", "com.feivirus.redpacket.dao",
		"com.feivirus.orderpay.dao","com.feivirus.transaction.dao"})
public class SpringbootStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyApplication.class, args);
	}

}
