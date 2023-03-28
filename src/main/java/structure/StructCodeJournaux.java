package structure;

import cnx.Connex;
import table.BDObject;
import table.CodeJournaux;
import table.PlanTiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StructCodeJournaux {
    public List<CodeJournaux> codeJournaux;

    public StructCodeJournaux(String idEse) {
        Connection cnx = Connex.PsqlConnect();

        try {
            codeJournaux = new ArrayList<>();
            List<BDObject> list = new CodeJournaux().Find(cnx, "identreprise", idEse);
            for(BDObject bdo : list){
                this.codeJournaux.add((CodeJournaux) bdo);
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
