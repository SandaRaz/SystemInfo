package table;

import cnx.Connex;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanComptable extends BDObject{
    String id;
    String compte;
    String intitule;
    String idEntreprise;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    public PlanComptable(String id ,String compte, String intitule, String idEntreprise) {
        this.setId(id);
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
                newplan = (PlanComptable) new PlanComptable().createInstancefromDB(cnx, "compte", compte);

                if(newplan == null){
                    if(compte != null && !compte.equals("null") && !compte.equals("")){
                        newplan = new PlanComptable("default", compte, intitule, idEse);
                        newplan.InsertInto(cnx);
                    }
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

    public static void deleteRow(String id) throws SQLException {
        Connection cnx = Connex.PsqlConnect();

        String sql = "DELETE FROM planComptable WHERE id = '"+id+"'";
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(sql);

        stmt.close();
        cnx.close();
    }

    public static void updateCell(String id, String oldcompte, String colonne, Object value, String idEntreprise) throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int nbr = 0;
        if(colonne.equals("compte")){
            Statement stmt = cnx.createStatement();
            String sql = "SELECT count(*) FROM planComptable WHERE identreprise = '"+idEntreprise+"' AND compte = '"+value+"'";
            System.out.println("sql >>> "+sql);
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                nbr = res.getInt(1);
            }
        }

        System.out.print("Nombre "+nbr);
        if(nbr != 0){
            if(!oldcompte.equals(value.toString())){
                System.out.println("New value diff Oldcompte");
                throw new Exception("Ce numero de compte existe deja");
            }
        }else{
            String sql = "UPDATE planComptable SET "+colonne+" = '"+value+"' WHERE id = '"+id+"'";
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
        }
        cnx.close();
    }
}
