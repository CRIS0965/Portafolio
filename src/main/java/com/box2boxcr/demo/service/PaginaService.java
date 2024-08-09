package com.box2boxcr.demo.service;


import com.box2boxcr.demo.domain.Pagina;
import com.box2boxcr.demo.domain.User;

import java.util.List;

public interface PaginaService {
    List<Pagina> getPaginasByTipo(String tipo);
    
    boolean authenticateUser(String username, String password); // Método para autenticar usuarios
    
    User getUserByUsername(String username); // Método para obtener un usuario por nombre de usuario

    void saveUser(User user); // Método para guardar un usuario
}