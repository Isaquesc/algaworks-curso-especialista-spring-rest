package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.service.AtivacaoClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;

        System.out.println("Contrutor MeuPrimeiroController: " + ativacaoClienteService);
    }

    @GetMapping("/hello")
    public Response hello(){
        return ativacaoClienteService.ativar(new Cliente("Isaque", "isaquedeco@icloud.com", "11958788416"));
    }
}
