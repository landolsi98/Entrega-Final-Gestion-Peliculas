package es.uah.FilmsActores.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actores", schema = "films_actores_db")
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idActor")
    private Integer idActor;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fecha_de_nacimiento")
    private Date fechaDeNacimiento;
    @Basic
    @Column(name = "pais_de_nacimiento")
    private String paisDeNacimiento;

    @ManyToMany
    /*
    @JoinTable(name = "films_has_actores",
            joinColumns = @JoinColumn(name = "idFilm"),
            inverseJoinColumns = @JoinColumn(name = "idActor"))
            */
    @JoinTable(name = "films_has_actores", joinColumns = {
            @JoinColumn(name = "Actores_idActor", referencedColumnName = "idActor")}, inverseJoinColumns = {
            @JoinColumn(name = "Films_idFilm", referencedColumnName = "idFilm")})

    @JsonIgnoreProperties("actores")

    private List<Film> films ;


    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getPaisDeNacimiento() {
        return paisDeNacimiento;
    }

    public void setPaisDeNacimiento(String paisDeNacimiento) {
        this.paisDeNacimiento = paisDeNacimiento;
    }
    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
    public void addFilm(Film film) {
        if (film != null) {
            getFilms().add(film);
            film.addActor(this);
        }
    }

    public void removeFilm(Film film) {
        if (film != null) {
            film.removeActor(this);
            getFilms().remove(film);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(idActor, actor.idActor) && Objects.equals(nombre, actor.nombre) && Objects.equals(fechaDeNacimiento, actor.fechaDeNacimiento) && Objects.equals(paisDeNacimiento, actor.paisDeNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActor);
    }
}
