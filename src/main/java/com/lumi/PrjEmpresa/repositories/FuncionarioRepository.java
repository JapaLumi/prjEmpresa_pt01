package com.lumi.PrjEmpresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lumi.PrjEmpresa.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario , Long> {

}
