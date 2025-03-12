package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Seance {
    private int idSeance;
    private Date date;
    private Time horaire;
    private int idMembre;
    private int idEntraineur;

    public Seance(int idSeance, Date date, Time horaire, int idMembre, int idEntraineur) {
        this.idSeance = idSeance;
        this.date = date;
        this.horaire = horaire;
        this.idMembre = idMembre;
        this.idEntraineur = idEntraineur;
    }

    public Seance(Date date, Time horaire, int idMembre, int idEntraineur) {
        this.date = date;
        this.horaire = horaire;
        this.idMembre = idMembre;
        this.idEntraineur = idEntraineur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public Time getHoraire() {
        return horaire;
    }

    public void setHoraire(Time horaire) {
        this.horaire = horaire;
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
