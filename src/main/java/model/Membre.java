package model;

public class Membre extends User{
    private int idMembre;
    private String sportPratique;

    public Membre(int idMembre, String sportPratique) {
        this.idMembre = idMembre;
        this.sportPratique = sportPratique;
    }

    public int getIdMembre() {

        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public String getSportPratique() {
        return sportPratique;
    }

    public void setSportPratique(String sportPratique) {
        this.sportPratique = sportPratique;
    }
}
