package com.au.credpro.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.au.credpro.report.confguration.DatabaseProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseProperties.class)
public class CredProReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CredProReportApplication.class, args);
	}

}
