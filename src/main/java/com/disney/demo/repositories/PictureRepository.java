package com.disney.demo.repositories;

import com.disney.demo.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author d.andresperalta
 */

@Repository
public interface PictureRepository extends JpaRepository<Picture, String>{
    
}
