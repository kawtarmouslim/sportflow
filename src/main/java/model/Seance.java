package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Seance {
    private int idSeance;
   private String dateTime;
    private int idMembre;
    private int idEntraineur;


    public Seance(String dateTime, int idMembre, int idEntraineur) {
        this.dateTime = dateTime;
        this.idMembre = idMembre;
        this.idEntraineur = idEntraineur;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdEntraineur() {
        return idEntraineur;
    }

    public void setIdEntraineur(int idEntraineur) {
        this.idEntraineur = idEntraineur;
    }
}
