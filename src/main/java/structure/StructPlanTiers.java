package structure;

import cnx.Connex;
import table.BDObject;
import table.PlanTiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StructPlanTiers {
    public List<PlanTiers> planTiers;

    public StructPlanTiers(String idEse) {
        Connection cnx = Connex.PsqlConnect();

        try {
            planTiers = new ArrayList<>();
            List<BDObject> list = new PlanTiers().Find(cnx, "identreprise", idEse);
            for(BDObject bdo : list){
                this.planTiers.add((PlanTiers) bdo);
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
