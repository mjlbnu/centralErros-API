package com.centralerrosapi.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LogFilter {
	
	private String title;
	
	private String detail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAtDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAtAte;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreatedAtDe() {
		return createdAtDe;
	}

	public void setCreatedAtDe(Date createdAtDe) {
		this.createdAtDe = createdAtDe;
	}

	public Date getCreatedAtAte() {
		return createdAtAte;
	}

	public void setCreatedAtAte(Date createdAtAte) {
		this.createdAtAte = createdAtAte;
	}	
	
}
