package com.disney.demo.controllers;

import com.disney.demo.entities.Pelicula;
import com.disney.demo.entities.Personaje;
import com.disney.demo.repositories.PeliculaRepository;
import com.disney.demo.services.PeliculaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author d.andresperalta
 */
@Controller
@RequestMapping("/movie")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @PostMapping("/create")
    public String createMovie(ModelMap model, MultipartFile file, String title, Date releaseDate, Integer rating, String idCharacter, String idGenre) {

        try {

            peliculaService.createMovie(file, title, releaseDate, rating, idCharacter, idGenre);
            model.put("exito", "Exito");

            return "";

        } catch (Exception e) {

            model.put("title", title);
            model.put("releaseDate", releaseDate);
            model.put("rating", rating);
            model.put("idCharacter", idCharacter);
            model.put("idGenre", idGenre);

            model.put("error", "Error");

            return "";
        }

    }

    @PostMapping("/modify")
    public String modifyMovie(ModelMap model, String idMovie, MultipartFile file, String title, Date releaseDate, Integer rating, String idCharacter, String idGenre) {

        try {

            peliculaService.modifyMovie(idMovie, file, title, releaseDate, rating, idCharacter, idGenre);
            model.put("exito", "Exito");

            return "";
        } catch (Exception e) {

            return "";
        }
    }

    @DeleteMapping("/delete")
    public String deleteMovie(ModelMap model, String idMovie) {

        try {

            peliculaService.deletePelicula(idMovie);
            model.put("exito", "Deleted");

            return "";
        } catch (Exception e) {

            model.put("error", "Error");
            return "";
        }

    }

    @GetMapping("/disable")
    public String disableMovie(ModelMap model, String idMovie) {

        try {

            peliculaService.disableGenero(idMovie);
            model.put("exito", "Disable it");

            return "";

        } catch (Exception e) {

            model.put("error", "Error");
            return "";
        }

    }

    @GetMapping("/search")
    public String listCharacter(ModelMap model) {

        List<Pelicula> movie = peliculaService.listarActivos();

        model.put("movie", movie);

        return "";
    }
}
