package com.box2boxcr.demo.service.impl;

import com.box2boxcr.demo.dao.PaginaRepository;
import com.box2boxcr.demo.domain.Pagina;
import com.box2boxcr.demo.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginaServiceImpl implements PaginaService {

    @Autowired
    private PaginaRepository paginaRepository;

    @Override
    public List<Pagina> getPaginasByTipo(String tipo) {
        return paginaRepository.findByTipo(tipo);
    }
}



