package com.disney.demo.services;

import com.disney.demo.entities.Picture;
import com.disney.demo.repositories.PictureRepository;
import java.io.IOException;
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
public class PictureService {

    @Autowired
    PictureRepository pr;

    @Transactional
    public Picture guardar(MultipartFile archivo) {

        if (archivo != null) {

            try {

                Picture foto = new Picture();
                foto.setMime(archivo.getContentType()); //Esto devuelve el tipo mime del archivo adjunto.
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return pr.save(foto);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Transactional
    public Picture actualizar(String idFoto, MultipartFile archivo) {

        if (archivo != null) {

            try {

                Picture foto = new Picture();

                if (idFoto != null) {

                    Optional<Picture> respuesta = pr.findById(idFoto);

                    if (respuesta.isPresent()) {

                        foto = respuesta.get();
                    }
                }

                foto.setMime(archivo.getContentType()); //Esto devuelve el tipo mime del archivo adjunto.
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return pr.save(foto);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return null;

    }

}
