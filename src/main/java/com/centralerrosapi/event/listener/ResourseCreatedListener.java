package com.centralerrosapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.centralerrosapi.event.EventResourseCreated;

@Component
public class ResourseCreatedListener implements ApplicationListener<EventResourseCreated> {

	@Override
	public void onApplicationEvent(EventResourseCreated event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
