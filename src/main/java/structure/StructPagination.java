package structure;

import cnx.Connex;
import table.BDObject;

import java.sql.Connection;
import java.sql.SQLException;

public class StructPagination {
    public int ligneAffichee = 10;
    public int nombrePage;

    public StructPagination(String idEse, String nomtable) {
        Connection cnx = Connex.PsqlConnect();

        try {
            double nbLigne = BDObject.compteLigne(cnx, nomtable, "idEntreprise", idEse);
            nombrePage = (int) Math.ceil(nbLigne / ligneAffichee);
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
