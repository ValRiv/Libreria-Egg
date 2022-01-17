package com.egg.Libreria.controlador;

import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author river
 */
@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "listarEditorial.html";
    }

    @GetMapping("/registro")
    public String formulario() {
        return "form-editorial";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String nombre) {
        try {
            editorialServicio.cargarEditorial(nombre);
            modelo.put("exito", "Registro exitoso");
            return "form-editorial";
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "form-editorial";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        try {
            editorialServicio.eliminarEditorial(id);
            return "redirect:/editorial/lista";
        } catch (Exception e) {
            return "listarEditorial.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id) {
        Editorial editorial = editorialServicio.listarEditorial(id);
        modelo.addAttribute("editorial", editorial);
        return "modificarEditorial";

    }

    @PostMapping("/confirmarModificacion/{id}")
    public String confirmarModificacion(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) {
        try {
            editorialServicio.modificarEditorial(nombre, id);
            modelo.put("exito", "Registro exitoso");
            return "redirect:/editorial/lista";
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "form-editorial";
        }
    }

}
