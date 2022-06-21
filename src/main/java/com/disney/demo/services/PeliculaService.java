package com.disney.demo.services;

import com.disney.demo.entities.Genero;
import com.disney.demo.entities.Pelicula;
import com.disney.demo.entities.Personaje;
import com.disney.demo.entities.Picture;
import com.disney.demo.repositories.PeliculaRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author d.andresperalta
 */
@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeService personajeService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private PictureService pictureService;

    @Transactional
    public void createMovie(MultipartFile file, String title, Date releaseDate, Integer rating, String idCharacter, String idGenre) {

        validate(title, rating);

        Pelicula p = new Pelicula();

        p.setAlta(true);

        p.setTitulo(title);
        p.setFechaEstreno(releaseDate);
        p.setCalificacion(rating);

        Picture img = pictureService.guardar(file);
        p.setFoto(img);

        Personaje character = personajeService.buscarPorId(idCharacter);
        List<Personaje> ch = new ArrayList<>();
        ch.add(character);
        p.setPersonajesAsoc(ch);

        Genero genre = generoService.buscarPorId(idGenre);
        p.setGenero(genre);

        peliculaRepository.save(p);

    }

    @Transactional
    public void modifyMovie(String idMovie, MultipartFile file, String title, Date releaseDate, Integer rating, String idCharacter, String idGenre) {

        String idImg = null;

        validate(title, rating);

        Optional<Pelicula> opt = peliculaRepository.findById(idMovie);

        if (opt.isPresent()) {

            Pelicula movie = opt.get();

            movie.setAlta(true);

            movie.setTitulo(title);
            movie.setFechaEstreno(releaseDate);
            movie.setCalificacion(rating);

            if (movie.getFoto() != null) {

                idImg = movie.getFoto().getId();

            }

            Picture pic = pictureService.actualizar(idImg, file);
            movie.setFoto(pic);

            Personaje character = personajeService.buscarPorId(idCharacter);
            List<Personaje> ch = new ArrayList<>();
            ch.add(character);
            movie.setPersonajesAsoc(ch);

            movie.setGenero(generoService.buscarPorId(idGenre));

            peliculaRepository.save(movie);

        }

    }

    @Transactional
    public void enablePelicula(String idMovie) {

        Optional<Pelicula> opt = peliculaRepository.findById(idMovie);

        if (opt.isPresent()) {

            Pelicula p = opt.get();

            p.setAlta(true);

            peliculaRepository.save(p);

        } else {

            throw new Error("La película no existe.");
        }

    }

    @Transactional
    public void deletePelicula(String idMovie) {

        Optional<Pelicula> opt = peliculaRepository.findById(idMovie);

        if (opt.isPresent()) {

            Pelicula p = opt.get();

            peliculaRepository.delete(p);

        } else {

            throw new Error("La película no existe.");
        }

    }

    @Transactional
    public void disableGenero(String idMovie) {

        Optional<Pelicula> opt = peliculaRepository.findById(idMovie);

        if (opt.isPresent()) {

            Pelicula p = opt.get();

            p.setAlta(false);

            peliculaRepository.save(p);

        } else {

            throw new Error("La película no existe.");
        }

    }

    @Transactional
    public List<Pelicula> listarTodos() {

        try {

            return peliculaRepository.findAll();
        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    @Transactional
    public List<Pelicula> listarActivos() {

        try {

            List<Pelicula> all = peliculaRepository.findAll();
            List<Pelicula> active = new ArrayList<>();

            for (Pelicula pelicula : all) {

                if (pelicula.getAlta()) {

                    active.add(pelicula);

                }

            }

            return active;

        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    @Transactional
    public Pelicula buscarPorId(String id) {

        Optional<Pelicula> opt = peliculaRepository.findById(id);

        if (opt.isPresent()) {

            return opt.get();
        } else {

            throw new Error("No se ha encontrado el autor solicitado.");
        }

    }

    public void validate(String title, Integer rating) throws Error {

        if (title.isEmpty() || title == null) {

            throw new Error("El título esta vacío.");
        }

        if (rating < 0 || rating > 5) {

            throw new Error("El rango es entre 0 estrellas a 5 estrellas.");
        }

    }

}
