
package com.egg.Libreria.controlador;

import com.egg.Libreria.entidades.Usuario;
import com.egg.Libreria.errores.ErrorServicio;
import com.egg.Libreria.repositorios.UsuarioRepositorio;
import com.egg.Libreria.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author river
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
@Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-perfil")
    public String editarPerfil(HttpSession session, @RequestParam String id, ModelMap model) {

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "perfil.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-perfil")
    public String registrar(ModelMap modelo, HttpSession session, MultipartFile archivo, @RequestParam String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave) {

        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            usuarioServicio.modificar(id, nombre, apellido, mail,clave);
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            List<Usuario> usuarios= usuarioRepositorio.findAll();
            modelo.put("usuarios", usuarios);
            modelo.put("error", ex.getMessage());
            modelo.put("perfil", usuario);

            return "perfil.html";
        }

    }

}

