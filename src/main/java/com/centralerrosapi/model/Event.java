package com.centralerrosapi.model;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

//@Entity
//@Table(name = "event")
public class Event {
	
	private Long id;

	private String description;
	
	@Embedded
	private System system;
	
	private String level;
	
    @CreatedDate
    private LocalDateTime createdAt;
}
