package table;

public class PlanComptable extends BDObject{
    String compte;
    String intitule;
    int idEntreprise;

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public PlanComptable() {
    }

    public PlanComptable(String compte, String intitule, int idEntreprise) {
        this.setCompte(compte);
        this.setIntitule(intitule);
        this.setIdEntreprise(idEntreprise);
    }
}
