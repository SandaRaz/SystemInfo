package table;

import exception.CharLimiteException;

public class Entreprise extends BDObject{
    String id;
    String nom;
    String domaine;
    String siege;
    String pdg;
    String nif;
    String numstat;
    String statut;
    String datedebut;
    String datefin;
    int idDevise;
    int idDeviseEquiv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws CharLimiteException {
        if(nom.length() > 30){
            throw new CharLimiteException();
        }
        this.nom = nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) throws CharLimiteException {
        if(domaine.length() > 30){
            throw new CharLimiteException();
        }
        this.domaine = domaine;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) throws CharLimiteException {
        if(siege.length() > 30){
            throw new CharLimiteException();
        }
        this.siege = siege;
    }

    public String getPdg() {
        return pdg;
    }

    public void setPdg(String pdg) throws CharLimiteException {
        if(pdg.length() > 30){
            throw new CharLimiteException();
        }
        this.pdg = pdg;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNumstat() {
        return numstat;
    }

    public void setNumstat(String numstat) {
        this.numstat = numstat;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) throws CharLimiteException {
        if(statut.length() > 30){
            throw new CharLimiteException();
        }
        this.statut = statut;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(int idDevise) {
        this.idDevise = idDevise;
    }

    public int getIdDeviseEquiv() {
        return idDeviseEquiv;
    }

    public void setIdDeviseEquiv(int idDeviseEquiv) {
        this.idDeviseEquiv = idDeviseEquiv;
    }

    public Entreprise() {

    }

    public Entreprise(String id, String nom, String domaine, String siege, String pdg, String nif, String numstat, String statut, String datedebut, String datefin, int idDevise, int idDeviseEquiv) throws CharLimiteException {
        this.setId(id);
        this.setNom(nom);
        this.setDomaine(domaine);
        this.setSiege(siege);
        this.setPdg(pdg);
        this.setNif(nif);
        this.setNumstat(numstat);
        this.setStatut(statut);
        this.setDatedebut(datedebut);
        this.setDatefin(datefin);
        this.setIdDevise(idDevise);
        this.setIdDeviseEquiv(idDeviseEquiv);
    }
}
