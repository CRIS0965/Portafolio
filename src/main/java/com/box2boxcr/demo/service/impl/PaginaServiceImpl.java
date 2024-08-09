package com.box2boxcr.demo.service.impl;

import com.box2boxcr.demo.dao.PaginaRepository;
import com.box2boxcr.demo.dao.UserRepository;
import com.box2boxcr.demo.domain.Pagina;
import com.box2boxcr.demo.domain.User;
import com.box2boxcr.demo.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginaServiceImpl implements PaginaService {

    @Autowired
    private PaginaRepository paginaRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Pagina> getPaginasByTipo(String tipo) {
        return paginaRepository.findByTipo(tipo);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        // Implementa la lógica de autenticación
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password); // Comparar contraseñas en texto claro
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user); // Guarda el usuario en la base de datos
    }
}