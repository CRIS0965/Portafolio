package com.box2boxcr.demo.controller;

import com.box2boxcr.demo.domain.TipoCuenta;
import com.box2boxcr.demo.domain.User;
import com.box2boxcr.demo.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

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

    @GetMapping("/ingresar")
    public String ingresar(Model model) {
        return "ingresar";
    }

    @PostMapping("/ingresar")
    public String ingresar(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes) {

        boolean isAuthenticated = paginaService.authenticateUser(username, password);

        if (isAuthenticated) {
            User user = paginaService.getUserByUsername(username);
            model.addAttribute("usuario", user);
            return "redirect:/mi-cuenta";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/ingresar";
        }
    }

    @GetMapping("/mi-cuenta")
    public String miCuenta(Model model) {
        return "mi-cuenta";
    }

    @GetMapping("/crear-casillero")
    public String crearCasillero(Model model) {
        return "crear-casillero";
    }

    @PostMapping("/crear-casillero")
    public String procesarCrearCasillero(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String email2,
            @RequestParam String phone1,
            @RequestParam String phone2,
            @RequestParam String direccion1,
            @RequestParam(required = false) String direccion2,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_nacimiento,
            @RequestParam String tipo_cuenta,
            @RequestParam(required = false) String razonSocial,
            @RequestParam String identificacionId,
            @RequestParam String cedula,
            @RequestParam String password, // Añadir el campo de contraseña
            Model model) {

        // Convertir el tipo_cuenta a TipoCuenta enum
        TipoCuenta tipoCuentaEnum = TipoCuenta.valueOf(tipo_cuenta.toUpperCase());

        // Crear un nuevo usuario
        User nuevoUsuario = new User();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setTelefono1(phone1);
        nuevoUsuario.setTelefono2(phone2);
        nuevoUsuario.setDireccion1(direccion1);
        nuevoUsuario.setDireccion2(direccion2);
        nuevoUsuario.setFechaNacimiento(fecha_nacimiento);
        nuevoUsuario.setTipoCuenta(tipoCuentaEnum); // Establecer el enum convertido
        nuevoUsuario.setPassword(password); // Establecer la contraseña

        // Guardar el usuario en la base de datos
        paginaService.saveUser(nuevoUsuario);

        // Agregar el usuario al modelo para la página de Mi Cuenta
        model.addAttribute("usuario", nuevoUsuario);

        // Redirigir a la página de Mi Cuenta
        return "redirect:/mi-cuenta";
    }

    @PostMapping("/recuperacion")
    public String recoverPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        // Lógica para enviar el correo de recuperación de contraseña
        redirectAttributes.addFlashAttribute("message", "Si la dirección de correo electrónico está registrada, se enviará un enlace para restablecer la contraseña.");
        return "redirect:/ingresar";
    }

    @GetMapping("/tarifas")
    public String tarifas(Model model) {
        return "tarifas";
    }

    @PostMapping("/cerrar-sesion")
    public String cerrarSesion(RedirectAttributes redirectAttributes) {
        // Aquí puedes manejar el cierre de sesión si es necesario, por ejemplo, invalidando el token o sesión.
        redirectAttributes.addFlashAttribute("message", "Has cerrado sesión con éxito.");
        return "redirect:/";
    }

    @GetMapping("/mi-cuenta/mi-perfil")
    public String perfil(Model model) {
        // Agrega atributos al modelo si es necesario
        return "mi-perfil"; // Nombre del archivo HTML en src/main/resources/templates
    }
    @GetMapping("/mi-perfil")
public String miPerfil(Model model) {
    // Agrega atributos al modelo si es necesario
    return "mi-perfil"; // Nombre del archivo HTML en src/main/resources/templates
}

@GetMapping("/test")
public String test() {
    return "test"; // Nombre del archivo HTML en src/main/resources/templates
}

@GetMapping("/mi-cuenta/contrasena")
public String cambiarContrasena(Model model) {
    return "mi-cuenta/contrasena"; // Asegúrate de que este nombre coincida con tu archivo HTML
}
       
        

        @PostMapping("/contrasena")
        public String procesarCambioContrasena(@RequestParam String old_password,
                @RequestParam String password1,
                @RequestParam String password2,
                RedirectAttributes redirectAttributes) {
            // Lógica para procesar el cambio de contraseña
            // Asegúrate de validar y actualizar la contraseña
            // Redirige a la página de éxito o muestra un mensaje de error
            return "redirect:/mi-cuenta"; // Redirige a la página de "Mi Cuenta" después del cambio
        }

}
