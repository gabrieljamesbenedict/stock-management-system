package com.gabriel.ecomms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EcommerceMSApplication  {
	public static void main(String[] args)
	{
		SpringApplication.run(EcommerceMSApplication.class, args);
	}
}
