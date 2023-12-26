package edu.cibertec.curso.dao.repository;

import edu.cibertec.curso.dao.entity.CursoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
     @Query(nativeQuery = true, value = "call Curso_Por_Nombre(:cadena)")
    public List<CursoEntity> consultarPorNombre(@Param("cadena") String cadena);
}
