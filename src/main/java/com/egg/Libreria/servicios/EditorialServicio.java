package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.errores.ErrorServicio;
import com.egg.Libreria.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author river
 */
@Service
public class EditorialServicio {

    @Autowired

    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void cargarEditorial(String nombre) throws ErrorServicio {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);

    }

    @Transactional
    public void modificarEditorial(String nombre, String id) throws ErrorServicio {
        Editorial editorial = editorialRepositorio.buscarPorId(id);
        validar(nombre);
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }

    @Transactional
    public void eliminarEditorial(String id) throws ErrorServicio {
        Editorial editorial = editorialRepositorio.buscarPorId(id);
        editorialRepositorio.delete(editorial);
    }
    @Transactional
    public Editorial alta(String id){
        Editorial editorial = editorialRepositorio.getOne(id);
        editorial.setAlta(true);
        return editorialRepositorio.save(editorial);
    }
    
    @Transactional
    public Editorial baja(String id){
        Editorial editorial= editorialRepositorio.getOne(id);
        editorial.setAlta(false);
        return editorialRepositorio.save(editorial);
    }
    

    public void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacio");

        }

    }

    public List<Editorial> listarEditoriales() {
        List<Editorial>editoriales= editorialRepositorio.ListarEditoriales();
        return editoriales;

    }
    public Editorial listarEditorial(String id){
        Editorial editorial= editorialRepositorio.buscarPorId(id);
        return editorial;
    }

}
