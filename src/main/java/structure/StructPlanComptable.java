package structure;

import cnx.Connex;
import table.BDObject;
import table.PlanComptable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StructPlanComptable {
    public List<PlanComptable> planComptables;

    public StructPlanComptable(String idEse) {
        Connection cnx = Connex.PsqlConnect();

        try {
            planComptables = new ArrayList<>();
            List<BDObject> list = new PlanComptable().Find(cnx, "identreprise", idEse);
            for(BDObject bdo : list){
                this.planComptables.add((PlanComptable) bdo);
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
