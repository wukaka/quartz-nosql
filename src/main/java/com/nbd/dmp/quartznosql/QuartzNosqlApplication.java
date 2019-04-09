package com.nbd.dmp.quartznosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class QuartzNosqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzNosqlApplication.class, args);
	}

}
