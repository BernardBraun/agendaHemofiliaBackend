package br.com.github.apihemofilia.apihemofilia.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@RestController
@RequestMapping("/api/hemocenter")
public class HemocenterController {
	
	@PersistenceContext
	EntityManager manager;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllHemocenters() {
		final TypedQuery<Hemocenter> query = manager.createQuery("select h from Hemocenter h", Hemocenter.class);
		return ResponseEntity.ok(query.getResultList());
	}
}
