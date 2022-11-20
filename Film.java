package es.uah.FilmsActores.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films", schema = "films_actores_db")
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idFilm")
    private Integer idFilm;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "ano")
    private Integer ano;
    @Basic
    @Column(name = "duracion")
    private String duracion;
    @Basic
    @Column(name = "pais")
    private String pais;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "reparto")
    private String reparto;
    @Basic
    @Column(name = "idioma")
    private String idioma;
    @Basic
    @Column(name = "compania")
    private String compania;
    @Basic
    @Column(name = "sinopsis")
    private String sinopsis;

    @Basic
    @Column(name = "imagen")
    private String imagen;

    @ManyToMany(mappedBy = "films")
    @JsonIgnoreProperties("films")
    private List<Actor> actores;

    public Film() {
    }

    public Film(Integer idFilm, String titulo, Integer ano, String duracion, String pais, String genero, String direccion, String reparto, String idioma, String compania, String sinopsis, String imagen) {
        this.idFilm = idFilm;
        this.titulo = titulo;
        this.ano = ano;
        this.duracion = duracion;
        this.pais = pais;
        this.genero = genero;
        this.direccion = direccion;
        this.reparto = reparto;
        this.idioma = idioma;
        this.compania = compania;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = this.direccion;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void addActor(Actor actor) {
        if (actor != null) {
            getActores().add(actor);
        }
    }

    public void removeActor(Actor actor) {
        if (actor != null) {
            getActores().remove(actor);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(idFilm, film.idFilm) && Objects.equals(titulo, film.titulo) && Objects.equals(ano, film.ano) && Objects.equals(duracion, film.duracion) && Objects.equals(pais, film.pais) && Objects.equals(genero, film.genero) && Objects.equals(direccion, film.direccion) && Objects.equals(reparto, film.reparto) && Objects.equals(idioma, film.idioma) && Objects.equals(compania, film.compania) && Objects.equals(sinopsis, film.sinopsis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm);
    }
}
