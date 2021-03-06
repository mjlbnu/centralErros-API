package com.centralerrosapi.controller;

import com.centralerrosapi.event.EventResourseCreated;
import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.LogRepository;
import com.centralerrosapi.repository.filter.LogFilter;
import com.centralerrosapi.repository.projection.LogResume;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/logs")
public class LogController {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@ApiOperation("Pesquisa de Logs com Criteria e paginação")
	@GetMapping
	public Page<Log> pesquisar(LogFilter logFilter, Pageable pageable){
		return logRepository.filtrar(logFilter, pageable);
	}

	@ApiOperation("Resumo de Logs com Criteria e paginação")
	@GetMapping(params = "resume")
	public Page<LogResume> resumir(LogFilter logFilter, Pageable pageable){
		return logRepository.resume(logFilter, pageable);
	}

	@ApiOperation("Busca de Log por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Log> log = logRepository.findById(id);
		return (log.isPresent()) ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
	}

	@ApiOperation("Cria um novo Log")
	@PostMapping
	public ResponseEntity<Log> criar(@Valid @RequestBody Log log, HttpServletResponse response) {
		Log novoLog = logRepository.save(log);

		publisher.publishEvent(new EventResourseCreated(this, response, novoLog.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(novoLog);
	}
}
