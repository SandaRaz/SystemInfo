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

public class PlanTiers extends BDObject{
    String id;
    String numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public PlanTiers() {
    }

    public PlanTiers(String id, String numero, String intitule, String idEntreprise) {
        this.id = id;
        this.numero = numero;
        this.intitule = intitule;
        this.idEntreprise = idEntreprise;
    }

    public static void planTiersXLS(Connection cnx, InputStream fileContent, String idEse) throws SQLException, IOException {
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
                String numero = String.valueOf(ligne.getCell(0));
                String intitule = String.valueOf(ligne.getCell(1));

                PlanTiers newplan = null;
                newplan = (PlanTiers) new PlanTiers().createInstancefromDB(cnx, "numero", numero);

                if(newplan == null ){
                    if(numero != null && !numero.equals("null") && !numero.equals("")){
                        newplan = new PlanTiers("default", numero, intitule, idEse);
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

        String sql = "DELETE FROM planTiers WHERE id = '"+id+"'";
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(sql);

        stmt.close();
        cnx.close();
    }

    public static void updateCell(String id, String oldnumero, String colonne, Object value, String idEntreprise) throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int nbr = 0;
        if(colonne.equals("numero")){
            Statement stmt = cnx.createStatement();
            String sql = "SELECT count(*) FROM planTiers WHERE identreprise = '"+idEntreprise+"' AND numero = '"+value+"'";
            System.out.println("sql >>> "+sql);
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                nbr = res.getInt(1);
            }
        }

        System.out.print("Nombre "+nbr);
        if(nbr != 0){
            if(!oldnumero.equals(value.toString())){
                System.out.println("New value diff Oldcompte");
                throw new Exception("Ce numero de compte existe deja");
            }
        }else{
            String sql = "UPDATE planTiers SET "+colonne+" = '"+value+"' WHERE id = '"+id+"'";
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
        }
        cnx.close();
    }
}
