package com.example.exsqlite;

import java.io.Serializable;

public class Produit implements Serializable {
    private int idProduit;
    private String libelle;
    private String famille;
    private Double prixAchat;
    private Double prixVente;

    public Produit() {
    }

    public Produit(int idProduit, String libelle, String famille, Double prixAchat, Double prixVente) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.famille = famille;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }
}
