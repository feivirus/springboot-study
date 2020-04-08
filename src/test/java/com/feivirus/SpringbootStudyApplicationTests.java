package com.feivirus;

import com.feivirus.demo.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStudyApplication.class)
class SpringbootStudyApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HelloWorldService helloWorldService;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {

	}

	@Test
	void testGetBean() {
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}

	@Test
	void testHelloWorldService() {
		helloWorldService.helloWorld();
	}

	/**
	 * 需求先启动SpringbootStudyApplication主程序，再测试
	 */
	@Test
	void testHelloWorldController() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:8080/helloWorld",
				String.class);
		System.out.println(response.getBody());
	}



}
