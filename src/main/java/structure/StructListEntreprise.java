package structure;

import cnx.Connex;
import table.BDObject;
import table.Entreprise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StructListEntreprise {
    public List<Entreprise> listes;

    public StructListEntreprise() {
        Connection cnx = Connex.PsqlConnect();

        try {
            listes = new ArrayList<>();
            for(BDObject bdo : new Entreprise().Find(cnx)){
                listes.add((Entreprise) bdo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
