package structure;

import cnx.Connex;
import table.BDObject;
import table.Devise;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StructFormulaire {
    public List<Devise> listDevise;

    public StructFormulaire() {
        Connection cnx = Connex.PsqlConnect();

        try {
            listDevise = new ArrayList<>();
            for(BDObject bdo : new Devise().Find(cnx)){
                 listDevise.add((Devise) bdo);
            }

            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
