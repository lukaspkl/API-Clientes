package com.example.teste3.model.repository;

import com.example.teste3.mode.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    public default Cliente buscarClienteID(Integer id){
        return findById(id).orElse(null);
    }

    public  default Cliente atualizarCliente(Cliente novocliente){
        return save(novocliente);
    }

}
