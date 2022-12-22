package es.uah.cFilmsActores.model;

import es.uah.cFilmsActores.model.User;
import java.sql.Date;
import java.util.Objects;

public class Critica {

    private Integer idCritica;

    private Integer idFilm;

    private String valoracion;

    private Integer nota;

    private Date fecha;


    private User user;


    public Critica(Integer idFilm, String valoracion, Integer nota, Date fecha, User user) {
        this.idFilm = idFilm;
        this.valoracion = valoracion;
        this.nota = nota;
        this.fecha = fecha;
        this.user = user;
    }


    public Critica() {


    }

    public Integer getIdCritica() {
        return idCritica;
    }

    public void setIdCritica(Integer idCritica) {
        this.idCritica = idCritica;
    }

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Critica{" +
                "idCritica=" + idCritica +
                ", idFilm=" + idFilm +
                ", valoracion='" + valoracion + '\'' +
                ", nota=" + nota +
                ", fecha=" + fecha +
                ", user=" + user +
                '}';
    }
}
