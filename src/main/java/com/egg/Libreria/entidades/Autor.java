package com.egg.Libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author river
 */
@Entity
public class Autor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String Id;
    private String nombre;
    private String apellido;
    private boolean alta;

    public Autor() {
    }

    public Autor(String Id, String nombre, String apellido, boolean alta) {
        this.Id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alta = alta;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Autor{" + "Id=" + Id + ", nombre=" + nombre + ", apellido=" + apellido + ", alta=" + alta + '}';
    }

}
