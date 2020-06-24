package com.centralerrosapi;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.centralerrosapi.config.property.CentralErroApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CentralErroApiProperty.class)
public class CentralerrosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralerrosApiApplication.class, args);
	}
	
	
	@Bean
	public Filter getCharacterEncodingFilter() {

	    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

	    encodingFilter.setEncoding("UTF-8");
	    encodingFilter.setForceEncoding(true);

	    return encodingFilter;

	}

}
