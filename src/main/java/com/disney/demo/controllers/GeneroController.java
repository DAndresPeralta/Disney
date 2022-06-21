package com.disney.demo.controllers;

import com.disney.demo.entities.Genero;
import com.disney.demo.services.GeneroService;
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
@RequestMapping("/genre")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping("/create")
    public String createGenre(ModelMap model, MultipartFile file, String genre, String idMovie) {

        try {
            generoService.createGenero(file, genre, idMovie);
            model.put("exito", "Created");

            return "";
        } catch (Exception e) {

            model.put("error", "Error");

            model.put("genre", genre);
            model.put("idMovie", idMovie);

            return "";
        }
    }

    @PostMapping("/modify")
    public String modifyGenre(ModelMap model, String idGenre, MultipartFile file, String genre, String idMovie) {

        try {

            generoService.modifyGenero(idGenre, file, genre, idMovie);
            model.put("exito", "Exito");

            return "";
        } catch (Exception e) {

            return "";
        }

    }

    @DeleteMapping("/delete")
    public String deleteGenre(ModelMap model, String idGenre) {

        try {

            generoService.deleteGenero(idGenre);
            model.put("exito", "Exito");

            return "";

        } catch (Exception e) {

            model.put("error", "Error");

            return "";
        }

    }

    @GetMapping("/disable")
    public String disableGenre(ModelMap model, String idGenre) {

        try {

            generoService.disableGenero(idGenre);
            model.put("exito", "Exito");

            return "";

        } catch (Exception e) {

            model.put("error", "Error");
            return "";
        }

    }

    @GetMapping("/search")
    public String listCharacter(ModelMap model) {

        List<Genero> genre = generoService.listActive();

        model.put("genre", genre);

        return "";
    }

}
