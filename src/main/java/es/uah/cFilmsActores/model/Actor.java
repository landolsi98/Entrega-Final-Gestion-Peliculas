package es.uah.cFilmsActores.model;

import java.util.ArrayList;
import java.util.List;

public class Actor {

    private Integer idActor;

    private String nombre;
    private String paisDeNacimiento;

    private String fechaDeNacimiento;
    private List<Film> films;


    public Actor(String nombre, String paisDeNacimiento, String fechaDeNacimiento) {
        this.idActor = 0;
        this.nombre = nombre;
        this.paisDeNacimiento = paisDeNacimiento;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.films = new ArrayList<>();

    }
public Actor() {
    }
    public Integer getIdActor() {
        return idActor;
    }
    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getPaisDeNacimiento(){
        return paisDeNacimiento;
    }
    public void setPaisDeNacimiento(String paisDeNacimiento){
        this.paisDeNacimiento = paisDeNacimiento;
    }
    public String getFechaDeNacimiento(){
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(String fechaDeNacimiento){
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public List<Film> getFilms() {
        return films;
    }
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "idActor=" + idActor +
                ", nombre='" + nombre + '\'' +
                ", pais_de_nacimiento ='" + paisDeNacimiento + '\'' +
                ", fechaDeNacimiento ='" + fechaDeNacimiento + '\'' +

                ", films=" + films +
                '}';
    }
}

