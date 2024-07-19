package com.box2boxcr.demo.service;

import com.box2boxcr.demo.domain.Pagina;
import java.util.List;

public interface PaginaService {
    List<Pagina> getPaginasByTipo(String tipo);
}

