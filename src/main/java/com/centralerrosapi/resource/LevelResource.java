package com.centralerrosapi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.centralerrosapi.model.Level;
import com.centralerrosapi.repository.LevelRepository;


@RestController
@RequestMapping("/levels")
public class LevelResource {
	
	@Autowired
	private LevelRepository levelRepository;
	
	@GetMapping
	public List<Level> listar() {
		return levelRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Level> criar(@RequestBody Level level) {
		Level novoLevel = levelRepository.save(level);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(novoLevel.getId()).toUri();
		
		return ResponseEntity.created(uri).body(novoLevel);
	}
	
	@GetMapping("/{id}")
	public Level buscar(@PathVariable Long id) {
		return levelRepository.findById(id).get();
	}

}
