package com.box2boxcr.demo.dao;

import com.box2boxcr.demo.domain.Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Long> {
    List<Pagina> findByTipo(String tipo);
}
