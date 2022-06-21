package com.disney.demo.repositories;

import com.disney.demo.entities.Genero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author d.andresperalta
 */
@Repository
public interface GeneroRepository extends JpaRepository<Genero, String> {

    @Query("SELECT g FROM genero g WHERE g.genero = :genero")
    public Genero buscarGenero(@Param("genero") String genero);

    @Query("SELECT g FROM Genero g WHERE g.alta = true")
    public List<Genero> listarGenero();

}
