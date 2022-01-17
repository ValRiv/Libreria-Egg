package com.egg.Libreria.controlador;

import com.egg.Libreria.entidades.Usuario;
import com.egg.Libreria.errores.ErrorServicio;
import com.egg.Libreria.repositorios.UsuarioRepositorio;
import com.egg.Libreria.servicios.AutorServicio;
import com.egg.Libreria.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class portalcontrolador {
    @Autowired
    private AutorServicio autorSevicio;

//////    @GetMapping("/")
//////    public String index() {
//////        return "Iniciolibreria.html";
//////    }
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "Iniciolibreria.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "login.html";
    }

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        modelo.put("usuarios", usuarios);
        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave) {

        try {
            usuarioServicio.registrar(nombre, apellido, mail, clave);
        } catch (ErrorServicio ex) {
            List<Usuario> usuarios = usuarioRepositorio.findAll();
            modelo.put("usuario", usuarios);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave", clave);
            
            return "registro.html";
        }
        modelo.put("titulo", "Bienvenido a Libreria Web Vale");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    }

}
//    @GetMapping("/")
//    public String index() {
//        return "Libro.html";
//    }


