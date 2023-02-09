package com.example.teste3.rest;

import com.example.teste3.mode.entity.Cliente;
import com.example.teste3.model.repository.ClienteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController

@RequestMapping("/api/clientes")
@Data
@RequiredArgsConstructor


public class ClienteController {

   private final ClienteRepository banco;




    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar (@RequestBody  @Valid Cliente cliente ){
        if (banco.findBycpf(cliente.getCpf())!=null){
            throw new ResponseStatusException(CONFLICT,"Cliente ja existe com esse CPF");
        }
        return banco.save(cliente);
    }


    @GetMapping("{id}")
    public Cliente obterClienteID (@PathVariable Integer id){
        Cliente cliente = banco.buscarClienteID(id);
            if (cliente == null){
                throw new ResponseStatusException(NOT_FOUND, "Cliente não encontrado");
            }

        return cliente;
    }

    @GetMapping
    public List<Cliente> obtertodosClientes(){

        return banco.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarCliente (@PathVariable Integer id){
        Cliente cliente = banco.buscarClienteID(id);
        if (cliente == null){
            throw  new ResponseStatusException(NOT_FOUND,"Cliente nao encontrado");
        }

        banco.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Cliente atualizarCliente (@PathVariable Integer id, @RequestBody @Valid Cliente novoCliente) {
        Cliente cliente = banco.buscarClienteID(id);
        if (cliente == null) {

            throw new ResponseStatusException(NOT_FOUND,"Cliente nao encontrado");

        }
        cliente.setNome(novoCliente.getNome());

        return banco.atualizarCliente(cliente);


    }

}

