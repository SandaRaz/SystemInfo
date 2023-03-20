package table;

import cnx.Connex;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanComptable extends BDObject{
    String compte;
    String intitule;
    String idEntreprise;

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

    public String getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(String idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public PlanComptable() {
    }

    public PlanComptable(String compte, String intitule, String idEntreprise) {
        super.setId(compte);

        this.setCompte(compte);
        this.setIntitule(intitule);
        this.setIdEntreprise(idEntreprise);
    }

    public static void planComptableXLS(Connection cnx, InputStream fileContent, String idEse) throws SQLException, IOException {
        boolean closed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            cnx.setAutoCommit(false);
            closed = true;
        }
        Workbook workbook = WorkbookFactory.create(fileContent);
        Sheet sheet = workbook.getSheetAt(0);

        try{
            for (Row ligne : sheet) {
                if (ligne.getRowNum() == 0) {
                    continue;
                }
                String compte = String.valueOf(ligne.getCell(0));
                String intitule = String.valueOf(ligne.getCell(1));

                PlanComptable newplan = null;
                newplan = (PlanComptable) new PlanComptable().createInstancefromDB(cnx, compte);

                if(newplan == null){
                    newplan = new PlanComptable(compte, intitule, idEse);
                    newplan.InsertInto(cnx);
                }
            }
        } catch (Exception e){
            cnx.rollback();
            e.printStackTrace();
        }

        if(closed){
            cnx.close();
        }
    }

    public static void deleteRow(String numcompte) throws SQLException {
        Connection cnx = Connex.PsqlConnect();

        String sql = "DELETE FROM planComptable WHERE compte = '"+numcompte+"'";
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(sql);

        stmt.close();
        cnx.close();
    }
}
