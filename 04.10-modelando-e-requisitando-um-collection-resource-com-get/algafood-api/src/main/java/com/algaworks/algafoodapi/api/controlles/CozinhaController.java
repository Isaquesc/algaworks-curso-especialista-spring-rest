package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.interfaces.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @RequestMapping(method = GET)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

}
