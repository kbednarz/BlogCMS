package com.github.kbednarz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource({"classpath:WEB-INF/dataSource.xml","classpath:WEB-INF/dataSourceTest.xml"})
public class BlogCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogCmsApplication.class, args);
	}

}

