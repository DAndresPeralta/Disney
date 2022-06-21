package com.disney.demo.services;

import com.disney.demo.entities.Genero;
import com.disney.demo.entities.Picture;
import com.disney.demo.repositories.GeneroRepository;
import com.disney.demo.repositories.PeliculaRepository;
import java.util.ArrayList;
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
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PictureService pictureService;

//    @Autowired
//    private PeliculaServicio peliculaRepository; FALTA!!!
    @Transactional
    public void createGenero(MultipartFile file, String genre, String idMovie) {

        validate(genre);

        Genero g = new Genero();

        g.setGenero(genre);
        g.setAlta(true);

        Picture pic = pictureService.guardar(file);
        g.setFoto(pic);

//        g.setPeliculas(FALTA HACER PELICULA SERVICIO);
        generoRepository.save(g);

    }

    @Transactional
    public void modifyGenero(String idGenero, MultipartFile file, String genre, String idMovie) {

        String idPic = null;

        validate(genre);

        Optional<Genero> opc = generoRepository.findById(idGenero);

        if (opc.isPresent()) {

            Genero g = opc.get();

            g.setGenero(genre);
            g.setAlta(true);

            if (g.getFoto() != null) {

                idPic = g.getFoto().getId();

            }

            Picture pic = pictureService.actualizar(idPic, file);
            g.setFoto(pic);

            generoRepository.save(g);

        }

    }

    @Transactional
    public void enableGenero(String idGenero) {

        Optional<Genero> opt = generoRepository.findById(idGenero);

        if (opt.isPresent()) {

            Genero p = opt.get();

            p.setAlta(true);

            generoRepository.save(p);

        } else {

            throw new Error("El género no existe.");
        }

    }

    @Transactional
    public void disableGenero(String idGenero) {

        Optional<Genero> opt = generoRepository.findById(idGenero);

        if (opt.isPresent()) {

            Genero p = opt.get();

            p.setAlta(false);

            generoRepository.save(p);

        } else {

            throw new Error("El personaje no existe.");
        }

    }

    @Transactional
    public void deleteGenero(String idGenero) {

        Optional<Genero> opt = generoRepository.findById(idGenero);

        if (opt.isPresent()) {

            Genero p = opt.get();

            generoRepository.delete(p);

        } else {

            throw new Error("El personaje no existe.");
        }

    }

    
    public List<Genero> listAll() {

        try {

            return generoRepository.findAll();
        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    public List<Genero> listActive() {

        try {

            List<Genero> all = generoRepository.findAll();
            List<Genero> active = new ArrayList<>();

            for (Genero genero : all) {

                if (genero.getAlta()) {

                    active.add(genero);

                }

            }

            return active;

        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    @Transactional
    public Genero buscarPorId(String id) {

        Optional<Genero> opt = generoRepository.findById(id);

        if (opt.isPresent()) {

            return opt.get();
        } else {

            throw new Error("No se ha encontrado el genero solicitado.");
        }

    }

    public void validate(String genre) throws Error {

        if (genre.isEmpty() || genre == null) {

            throw new Error("Dato nulo.");
        }

    }

}
