package es.uah.cFilmsActores.model;



import java.util.List;
import java.util.Objects;

public class Film {
    private  Integer idFilm;
    private String titulo;
    private Integer ano;
    private String duracion;
    private String pais;
    private String genero;

    private String reparto;
    private String idioma;
    private String compania;
    private String sinopsis;
    private String direcion;
    private String imagen;

    private List<Actor> actores;


    public Film(Integer idFilm, String titulo, Integer ano, String duracion, String pais, String genero, String reparto, String idioma, String compania, String sinopsis, String direcion, String imagen){
        this.idFilm = idFilm;
        this.titulo = titulo;
        this.ano = ano;
        this.duracion = duracion;
        this.pais = pais;
        this.genero = genero;
        this.reparto = reparto;
        this.idioma = idioma;
        this.compania = compania;
        this.sinopsis = sinopsis;
        this.direcion = direcion;
        this.imagen = imagen;



    }
    public Film () {

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

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(idFilm, film.idFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm);
    }
}



