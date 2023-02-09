package com.example.teste3.model.repository;

import com.example.teste3.mode.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ServicoRepository  extends JpaRepository<Servico,Integer> {
}
