package com.egg.Libreria.controlador;

import com.egg.Libreria.entidades.Libro;
import com.egg.Libreria.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros", libros);
        return "listarlibro.html";
    }

    @GetMapping("/registro")
    public String formulario() {
        return "form-Libro";
    }

    @PostMapping("/registro")
    public String cargarLibro(ModelMap modelo, @RequestParam int isbn,@RequestParam String titulo, @RequestParam int anio, @RequestParam int ejemplares, @RequestParam int ejemplaresPrestados, @RequestParam String nombreAutor, @RequestParam String apellidoAutor, @RequestParam String nombreEditorial) {
        try {
            libroServicio.cargarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, nombreAutor, apellidoAutor, nombreEditorial);
            modelo.put("exito", "Registro exitoso!!");
            return "form-libro";
            
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato!!");
            return "form-libro";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        try {
            libroServicio.eliminarLibro(id);
            return "redirect:/libro/lista";
        } catch (Exception e) {
            return "listarlibro.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id) {
        Libro libro = libroServicio.listarLibro(id);
        modelo.addAttribute("libro", libro);
        return "modificarLibro";

    }

    @PostMapping("/confirmarModificacion/{id}")
    public String confirmarModificacion(ModelMap modelo, @PathVariable String id, @RequestParam int isbn, @RequestParam String titulo, @RequestParam int anio, @RequestParam int ejemplares, @RequestParam int ejemplaresPrestados) {
        try {
            libroServicio.modificarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, id);
            modelo.put("exitos", "Modificación exitosa");
            return "redirect:/libro/lista";
        } catch (Exception e) {
            modelo.put("errores", "Falto algun dato en la modificación");
            return "form-libro";
        }
    }

}
