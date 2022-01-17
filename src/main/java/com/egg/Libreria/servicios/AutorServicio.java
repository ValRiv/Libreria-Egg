package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.errores.ErrorServicio;
import com.egg.Libreria.repositorios.AutorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author river
 */
@Service
public class AutorServicio {

    @Autowired

    private AutorRepositorio autorRepositorio;

    @Transactional
    public void cargarAutor(String nombre, String apellido) throws ErrorServicio {
        validar(nombre, apellido);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autorRepositorio.save(autor);

    }

    @Transactional
    public void modificarAutor(String nombre, String apellido, String id) throws ErrorServicio {
        Autor autor = autorRepositorio.buscarPorId(id);
        validar(nombre, apellido);
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autorRepositorio.save(autor);

    }

    @Transactional
    public void eliminarAutor(String id) {
        Autor autor = autorRepositorio.buscarPorId(id);
        autorRepositorio.delete(autor);
    }

////    @Transactional
////    public Autor alta(String id) {
////        Autor autor = autorRepositorio.getOne(id);
////        autor.setAlta(true);
////        return autorRepositorio.save(autor);
////    }
////
////    @Transactional
////    public Autor baja(String id) {
////        Autor autor = autorRepositorio.getOne(id);
////        autor.setAlta(false);
////        return autorRepositorio.save(autor);
////    }

    public void validar(String nombre, String apellido) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede esta vacio");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede estar vacio");

        }

    }

    public List<Autor> listarAutores() {
        List<Autor> autores = autorRepositorio.ListarAutores();
        return autores;

    }

    public Autor listarAutor(String id) {
        Autor autor = autorRepositorio.buscarPorId(id);
        return autor;

    }
}
