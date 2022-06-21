package com.disney.demo.controllers;

import com.disney.demo.entities.Pelicula;
import com.disney.demo.entities.Personaje;
import com.disney.demo.repositories.GeneroRepository;
import com.disney.demo.repositories.PeliculaRepository;
import com.disney.demo.repositories.PersonajeRepository;
import com.disney.demo.services.GeneroService;
import com.disney.demo.services.PeliculaService;
import com.disney.demo.services.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author d.andresperalta
 */
@Controller
@RequestMapping("/character")
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeService personajeService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private GeneroService generoService;

    @PostMapping("/create")
    public String createCharacter(ModelMap model, MultipartFile file, @RequestParam String name, @RequestParam Integer age, @RequestParam Double weight, @RequestParam String history, @RequestParam String idPelicula) {
        try {

            personajeService.crearPersonaje(file, name, age, weight, history, idPelicula);
            model.put("exito", "Exito");

        } catch (Exception e) {

            model.put("name", name);
            model.put("age", age);
            model.put("weight", weight);
            model.put("history", history);
            model.put("idPelicula", idPelicula);

            model.put("error", "Error");

            return "";
        }

        return "";
    }

    @PostMapping("/modify")
    public String modifyCharacter(ModelMap model, String idCharacter, MultipartFile file, @RequestParam String name, @RequestParam Integer age, @RequestParam Double weight, @RequestParam String history, @RequestParam String idPelicula) {

        try {

            personajeService.modificarPersonaje(idPelicula, file, name, age, weight, history);
            model.put("exito", "Exito");

            return "";
        } catch (Exception e) {

            return "";
        }

    }

    @DeleteMapping("/delete/{idCharacter}")
    public String deleteCharacter(ModelMap model, String idCharacter) {

        try {

            personajeService.deletePersonaje(idCharacter);
            model.put("exito", "Exito");

            return "";

        } catch (Exception e) {

            model.put("error", "Error");
            return "";

        }

    }

    @GetMapping("/disable/{idCharacter}")
    public String disableCharacter(ModelMap model, String idCharacter) {

        try {

            personajeService.inhabilitarPersonaje(idCharacter);
            model.put("exito", "Exito");

            return "";
        } catch (Exception e) {

            model.put("error", "Error");
            return "";

        }

    }

    @GetMapping("/search")
    public String listCharacter(ModelMap model) {

        List<Personaje> character = personajeService.listarActivos();

        model.put("character", character);

        return "";
    }

    @GetMapping("/search/{name}")
    public String listCharacterByName(ModelMap model, String name) {

        Personaje character = personajeService.listarPorNombre(name);

        model.put("character", character);

        return "";
    }

    @GetMapping("/search/{age}")
    public String listCharacterByAge(ModelMap model, Integer age) {

        List<Personaje> character = personajeService.listarPorEdad(age);

        model.put("character", character);

        return "";

    }

    @GetMapping("/search/{movie}")
    public String listCharacterByMovie(ModelMap model, Pelicula movie) {

        List<Personaje> character = personajeService.listarPorPelicula(movie);

        model.put("character", character);

        return "";

    }
}
