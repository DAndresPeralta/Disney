package com.disney.demo.repositories;

import com.disney.demo.entities.Genero;
import com.disney.demo.entities.Pelicula;
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
public interface PeliculaRepository extends JpaRepository<Pelicula, String> {

    @Query("SELECT p FROM pelicula p WHERE p.titulo = :titulo")
    public Pelicula buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT p FROM pelicula p WHERE p.genero = :genero")
    public Pelicula buscarPorGenero(@Param("genero") Genero genero);

    @Query("SELECT p FROM Pelicula p WHERE p.alta = true")
    public List<Pelicula> listarPelicula();
}
