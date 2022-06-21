package com.disney.demo.repositories;

import com.disney.demo.entities.Pelicula;
import com.disney.demo.entities.Personaje;
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
public interface PersonajeRepository extends JpaRepository<Personaje, String> {

    @Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
    public Personaje buscarPersonajePorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Personaje p WHERE p.edad = :edad")
    public List<Personaje> buscarPorEdad(@Param("edad") Integer edad);

    @Query("SELECT p FROM Personaje p WHERE p.pelicula = :pelicula")
    public List<Personaje> buscarPorPelicula(@Param("pelicula") Pelicula pelicula);

    @Query("SELECT p FROM Personaje p WHERE p.peso = :peso")
    public Personaje buscarPorPeso(@Param("peso") Double peso);

    @Query("SELECT p FROM Personaje p WHERE p.alta = true")
    public List<Personaje> listarPersonaje();

}
