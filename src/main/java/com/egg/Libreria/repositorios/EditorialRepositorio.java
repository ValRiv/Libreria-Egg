
package com.egg.Libreria.repositorios;

import com.egg.Libreria.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    @Query("SELECT c FROM Editorial c")
    public List<Editorial> ListarEditoriales();
    
        
    @Query("SELECT c FROM Editorial c WHERE c.id = :id")
    public Editorial buscarPorId(@Param("id")String id);
    
}
