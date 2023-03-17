package structure;

import cnx.Connex;
import table.Entreprise;

import java.sql.Connection;
import java.sql.SQLException;

public class StructEntreprise {
    public Entreprise entreprise;

    public StructEntreprise(String idEse) {
        Connection cnx = Connex.PsqlConnect();

        try {
            this.entreprise = (Entreprise) new Entreprise().createInstancefromDB(cnx, idEse);
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
