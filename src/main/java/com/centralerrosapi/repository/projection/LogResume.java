package com.centralerrosapi.repository.projection;

import java.util.Date;

import com.centralerrosapi.model.Category;
import com.centralerrosapi.model.Level;

public class LogResume {

	private Long id;
	private String title;
	private Date createdAt;
	private Category category;
	private Level level;
	private String system;

	public LogResume(Long id, String title, Date createdAt, Category category, Level level, String system) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.title = title;
		this.category = category;
		this.level = level;
		this.system = system;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
