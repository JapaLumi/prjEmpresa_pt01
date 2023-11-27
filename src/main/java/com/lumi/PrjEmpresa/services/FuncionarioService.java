package com.lumi.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;
import com.lumi.PrjEmpresa.entities.Funcionario;
import com.lumi.PrjEmpresa.repositories.FuncionarioRepository;


public class FuncionarioService {

private final FuncionarioRepository funcionarioRepository;
	
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	//inserir
	public Funcionario savefuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	//listar por id
	public Funcionario getFuncionarioById(Long funcodigo) {
		return funcionarioRepository.findById(funcodigo).orElse(null);
	}
	
	//listar todos
	public List<Funcionario> getAllFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	// apagar
	public void deleteFuncionario(Long funcodigo) {
		funcionarioRepository.deleteById(funcodigo);
	}
	
	//update com optional 
	public Funcionario updateFuncionario(Long funcodigo, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcodigo);
		if (funcionarioOptional.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptional.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFuncodigo(novoFuncionario.getFuncodigo());
			funcionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());
			funcionarioExistente.setFunsalario(novoFuncionario.getFunsalario());
			  return funcionarioRepository.save(funcionarioExistente); 
        } else {
            return null; 
		}
	}
}
