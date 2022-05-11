package org.generation.LojaGames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.LojaGames.model.Usuario;
import org.generation.LojaGames.model.UsuarioLogin;
import org.generation.LojaGames.repository.UsuarioRepository;
import org.generation.LojaGames.service.UsuarioService;
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
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario> getId(@PathVariable Long id){
		
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody @Valid Optional<UsuarioLogin> usuarioLogin){
		
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody @Valid Usuario usuario){
		
		return usuarioService.atualizarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
}
