
package com.egg.Libreria.repositorios;

import com.egg.Libreria.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author river
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
   @Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
    

}
