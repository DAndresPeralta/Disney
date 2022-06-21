package com.disney.demo.services;

import com.disney.demo.entities.Pelicula;
import com.disney.demo.entities.Personaje;
import com.disney.demo.entities.Picture;
import com.disney.demo.repositories.PersonajeRepository;
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
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PictureService pictureService;

    @Transactional
    public void crearPersonaje(MultipartFile file, String name, Integer age, Double weight, String history, String idPelicula) {

        verificarDatos(name, age, weight);

        Personaje personaje = personajeRepository.buscarPersonajePorNombre(name);

        if (personaje == null) {

            Personaje p = new Personaje();

            p.setAlta(true);
            p.setNombre(name);
            p.setEdad(age);
            p.setPeso(weight);
            p.setHistoria(history);

//            Pelicula movie = falta setear la/las peliculas que corresponden al personaje
            Picture foto = pictureService.guardar(file);
            p.setFoto(foto);

            personajeRepository.save(p);

        }
    }

    @Transactional
    public void modificarPersonaje(String idPersonaje, MultipartFile file, String name, Integer age, Double weight, String history) {

        String idFoto = null;

        verificarDatos(name, age, weight);

        Optional<Personaje> respuesta = personajeRepository.findById(idPersonaje);

        if (respuesta.isPresent()) {

            Personaje p = respuesta.get();

            p.setNombre(name);
            p.setEdad(age);
            p.setPeso(weight);
            p.setHistoria(history);

            if (p.getFoto() != null) {

                idFoto = p.getFoto().getId();

            }

            Picture foto = pictureService.actualizar(idFoto, file);
            p.setFoto(foto);

            personajeRepository.save(p);

        }

    }

    @Transactional
    public void habilitarPersonaje(String idPersonaje) {

        Optional<Personaje> opt = personajeRepository.findById(idPersonaje);

        if (opt.isPresent()) {

            Personaje p = opt.get();

            p.setAlta(true);

            personajeRepository.save(p);

        } else {

            throw new Error("El personaje no existe.");
        }

    }

    @Transactional
    public void inhabilitarPersonaje(String idPersonaje) {

        Optional<Personaje> opt = personajeRepository.findById(idPersonaje);

        if (opt.isPresent()) {

            Personaje p = opt.get();

            p.setAlta(false);

            personajeRepository.save(p);

        } else {

            throw new Error("El personaje no existe.");
        }

    }

    @Transactional
    public void deletePersonaje(String idPersonaje) {

        Optional<Personaje> opt = personajeRepository.findById(idPersonaje);

        if (opt.isPresent()) {

            Personaje p = opt.get();

            personajeRepository.delete(p);

        } else {

            throw new Error("El personaje no existe.");
        }

    }

    @Transactional
    public List<Personaje> listarTodos() {

        try {

            return personajeRepository.findAll();
        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    @Transactional
    public List<Personaje> listarActivos() {

        try {

            List<Personaje> all = personajeRepository.findAll();
            List<Personaje> active = new ArrayList<>();

            for (Personaje personaje : all) {

                if (personaje.getAlta()) {

                    active.add(personaje);

                }

            }

            return active;

        } catch (Exception e) {

            throw new Error("Hubo un problema.");
        }

    }

    @Transactional
    public Personaje listarPorNombre(String name) {

        Personaje byName = personajeRepository.buscarPersonajePorNombre(name);

        return byName;

    }

    @Transactional
    public List<Personaje> listarPorEdad(Integer edad) {

        List<Personaje> all = personajeRepository.buscarPorEdad(edad);

        return all;

    }

    public List<Personaje> listarPorPelicula(Pelicula movie) {

        List<Personaje> all = personajeRepository.buscarPorPelicula(movie);

        return all;
    }

    @Transactional
    public Personaje buscarPorId(String id) {

        Optional<Personaje> opt = personajeRepository.findById(id);

        if (opt.isPresent()) {

            return opt.get();
        } else {

            throw new Error("No se ha encontrado el autor solicitado.");
        }

    }

    public void verificarDatos(String name, Integer age, Double weight) throws Error {

        if (name.isEmpty() || name == null) {

            throw new Error("El nombre no puede estar vacío.");
        }

        if (age < 0) {

            throw new Error("La edad no puede ser menor a cero.");
        }

        if (weight < 0) {

            throw new Error("El peso no puede ser menor a cero.");
        }

    }

}
