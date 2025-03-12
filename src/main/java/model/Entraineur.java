package model;

public class Entraineur extends User {
    private String idEntraineur;
    private  String specialite;

    public Entraineur(String idEntraineur, String specialite) {
        this.idEntraineur = idEntraineur;
        this.specialite = specialite;
    }

    public String getIdEntraineur() {
        return idEntraineur;
    }

    public void setIdEntraineur(String idEntraineur) {
        this.idEntraineur = idEntraineur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
