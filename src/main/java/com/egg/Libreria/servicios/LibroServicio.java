package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.entidades.Libro;
import com.egg.Libreria.errores.ErrorServicio;
import com.egg.Libreria.repositorios.LibroRepositorio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired // la variable la inicializa el servidor de aplicaciones
    private LibroRepositorio libroRepositorio;

    @Transactional //Siel método se ejecuta sinlargar excepciones,entonces se hace un comit a la base de datos

    public void cargarLibro(int isbn, String titulo, int anio, int ejemplares, int ejemplaresPrestados, String nombreAutor, String apellidoAutor, String nombreEditorial) throws ErrorServicio {
        
        validarLibro(isbn,titulo,anio,ejemplares,nombreAutor,apellidoAutor,nombreEditorial);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        Autor autor = new Autor();
        autor.setNombre(nombreAutor);
        autor.setApellido(apellidoAutor);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombreEditorial);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }
    @Transactional
    

    public void modificarLibro(int isbn, String titulo, int anio, int ejemplares, int ejemplaresPrestados, String Id) throws ErrorServicio {
        
        Libro libro = libroRepositorio.buscarporId(Id);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libroRepositorio.save(libro);

    }

    @Transactional
    public Libro alta(String id) {
        Libro entidad = libroRepositorio.getOne(id);
        entidad.setAlta(true);
        return libroRepositorio.save(entidad);
    }

    @Transactional
    public Libro baja(String id) {
        Libro entidad = libroRepositorio.getOne(id);
        entidad.setAlta(false);
        return libroRepositorio.save(entidad);
    }

    @Transactional
    public void eliminarLibro(String id) {
        Libro libro = libroRepositorio.buscarporId(id);
        libroRepositorio.delete(libro);
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = libroRepositorio.ListarLibros();
        return libros;
    }

    public Libro listarLibro(String id) {
        Libro libro = libroRepositorio.buscarporId(id);
        return libro;
    }

    @Transactional
    public void validarLibro(int isbn,String titulo, int anio,int ejemplares,String nombreAutor, String apellidoAutor, String nombreEditorial) throws ErrorServicio{
        
        if (isbn<=0){
            throw new ErrorServicio("El ISBN no puede ser menor o igual de cero");
        }
        if(anio<=0){
            throw new ErrorServicio("El año no puede ser menor o igual a cero");
        }
        if(ejemplares<=0){
            throw new ErrorServicio("El numero de ejemplares no puede ser menor de cero");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo");
        }
        
        if (nombreAutor == null || nombreAutor.isEmpty()) {
            throw new ErrorServicio("El nombre del autor no puede ser nulo");
        }
        if (apellidoAutor==null || apellidoAutor.isEmpty()) {
            throw new ErrorServicio("El apellido del autor no puede ser nulo");
            
        }
        if (nombreEditorial ==null || nombreEditorial.isEmpty()) {
            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
            
        }
    }

}
