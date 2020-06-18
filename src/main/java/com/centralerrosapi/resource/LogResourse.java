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
import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.LogRepository;

@RestController
@RequestMapping("/logs")
public class LogResourse {
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Log> listar(){
		return logRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Log> log = logRepository.findById(id);
		return (log.isPresent()) ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Log> criar(@Valid @RequestBody Log log, HttpServletResponse response) {
		Log novoLog = logRepository.save(log);
		
		publisher.publishEvent(new EventResourseCreated(this, response, novoLog.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novoLog);
	}
}
