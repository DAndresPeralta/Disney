package com.disney.demo.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author d.andresperalta
 */
@Entity
public class Pelicula {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Boolean alta;

    @OneToOne
    private Picture foto;

    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstreno;

    private Integer calificacion;

    @OneToMany
    private List<Personaje> personajesAsoc;

    @OneToMany
    private Genero genero;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the alta
     */
    public boolean isAlta() {
        return getAlta();
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(boolean alta) {
        this.setAlta((Boolean) alta);
    }

    /**
     * @return the foto
     */
    public Picture getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Picture foto) {
        this.foto = foto;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the fechaEstreno
     */
    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    /**
     * @param fechaEstreno the fechaEstreno to set
     */
    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the personajesAsoc
     */
    public List<Personaje> getPersonajesAsoc() {
        return personajesAsoc;
    }

    /**
     * @param personajesAsoc the personajesAsoc to set
     */
    public void setPersonajesAsoc(List<Personaje> personajesAsoc) {
        this.personajesAsoc = personajesAsoc;
    }

    /**
     * @return the genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * @return the alta
     */
    public Boolean getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
