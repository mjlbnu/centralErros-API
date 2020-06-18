package com.centralerrosapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralerrosapi.event.EventResourseCreated;
import com.centralerrosapi.model.System;
import com.centralerrosapi.repository.SystemRepository;

@RestController
@RequestMapping("/systems")
public class SystemResourse {
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<System> listar(){
		return systemRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<System> system = systemRepository.findById(id);
		return (system.isPresent()) ? ResponseEntity.ok(system) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<System> criar(@Valid @RequestBody System system, HttpServletResponse response) {
		System novoSystem = systemRepository.save(system);
		
		publisher.publishEvent(new EventResourseCreated(this, response, novoSystem.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novoSystem);
	}
	

}
