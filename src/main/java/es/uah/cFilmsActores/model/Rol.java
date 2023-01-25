package es.uah.cFilmsActores.model;

import java.util.List;

public class Rol {
    private Integer idRol;


    private List<User> users;

    private String rol;

  public Rol () {

  }

    public Rol (String idRolAndName) {
    if(idRolAndName != null && idRolAndName.length() > 0){
        String[] fieldPositions = idRolAndName.split("-");
        this.idRol = Integer.parseInt(fieldPositions[0]);
        this.rol = fieldPositions[1];
    }
}
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return ""+idRol+"-"+this.rol;
    }

}





