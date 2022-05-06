package org.generation.LojaGames.model.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.LojaGames.model.ProdutoModel;
import org.generation.LojaGames.repository.CategoriaRepository;
import org.generation.LojaGames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nomeProduto}")
	public ResponseEntity<List<ProdutoModel>> getByNome(@PathVariable String nomeProduto){
		return ResponseEntity.ok(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> postProduto(@Valid @RequestBody ProdutoModel produto){
		if(categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> putProduto(@Valid @RequestBody ProdutoModel produto){
		if(produtoRepository.existsById(produto.getId())) {
			if(categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
