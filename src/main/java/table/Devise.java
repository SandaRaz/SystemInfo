package table;

public class Devise extends BDObject{
    int id;
    String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Devise() {
    }

    public Devise(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
