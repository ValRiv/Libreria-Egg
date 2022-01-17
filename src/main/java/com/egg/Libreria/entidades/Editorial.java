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
public class Editorial {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String Id;
    private String nombre;
    private boolean alta;

    public Editorial() {
    }

    public Editorial(String Id, String nombre, boolean alta) {
        this.Id = Id;
        this.nombre = nombre;
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

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Editorial{" + "Id=" + Id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }

}