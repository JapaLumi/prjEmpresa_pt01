package com.lumi.PrjEmpresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.lumi.PrjEmpresa.entities.Funcionario;
import com.lumi.PrjEmpresa.services.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;

public class FuncionarioController {

	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}
	
	private final FuncionarioService funcionarioService;
	
	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza funcionario por ID")
	public ResponseEntity<Funcionario> getFuncionario(@PathVariable Long funcodigo) {
		Funcionario funcionario = funcionarioService.getFuncionarioById(funcodigo);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um funcionario")
	public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioService.savefuncionario(funcionario);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um funcionario")
	public void deleteFuncionario(@PathVariable Long funcodigo) {
		funcionarioService.deleteFuncionario(funcodigo);
	}

	// Utilizando o ResponseEntity e RequestEntity
	@GetMapping
	@Operation(summary = "Apresenta todos os funcionarios")
	public ResponseEntity<List<Funcionario>> getAllFuncionarios(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Funcionario> funcionarios = funcionarioService.getAllFuncionarios();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(funcionarios);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um funcionario")
	public Funcionario updateFuncionario(@PathVariable Long funcodigo, @RequestBody Funcionario funcionario) {
		return funcionarioService.updateFuncionario(funcodigo, funcionario);
	}


}
