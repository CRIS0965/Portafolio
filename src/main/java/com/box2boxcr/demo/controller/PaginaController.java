package com.box2boxcr.demo.controller;

import com.box2boxcr.demo.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @Autowired
    private PaginaService paginaService;

    @GetMapping({"/", "/inicio"})
    public String inicio(Model model) {
        model.addAttribute("paginas", paginaService.getPaginasByTipo("inicio"));
        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("paginas", paginaService.getPaginasByTipo("nosotros"));
        return "nosotros";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("paginas", paginaService.getPaginasByTipo("servicios"));
        return "servicios";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
