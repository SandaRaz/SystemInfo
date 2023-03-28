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

public class CodeJournaux extends BDObject{
    String id;
    String code;
    String intitule;
    String idEntreprise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public CodeJournaux() {
    }

    public CodeJournaux(String id, String code, String intitule, String idEntreprise) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.idEntreprise = idEntreprise;
    }

    public static void codeJournauxXLS(Connection cnx, InputStream fileContent, String idEse) throws SQLException, IOException {
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
                String code = String.valueOf(ligne.getCell(0));
                String intitule = String.valueOf(ligne.getCell(1));

                CodeJournaux codeJournaux = null;
                codeJournaux = (CodeJournaux) new CodeJournaux().createInstancefromDB(cnx, "code", code);

                if(codeJournaux == null ){
                    if(code != null && !code.equals("null") && !code.equals("")){
                        codeJournaux = new CodeJournaux("default", code, intitule, idEse);
                        codeJournaux.InsertInto(cnx);
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

        String sql = "DELETE FROM codeJournaux WHERE id = '"+id+"'";
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(sql);

        stmt.close();
        cnx.close();
    }

    public static void updateCell(String id, String oldcode, String colonne, Object value, String idEntreprise) throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int nbr = 0;
        if(colonne.equals("code")){
            Statement stmt = cnx.createStatement();
            String sql = "SELECT count(*) FROM codeJournaux WHERE identreprise = '"+idEntreprise+"' AND code = '"+value+"'";
            System.out.println("sql >>> "+sql);
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                nbr = res.getInt(1);
            }
        }

        System.out.print("Nombre "+nbr);
        if(nbr != 0){
            if(!oldcode.equals(value.toString())){
                System.out.println("New value diff Oldcode");
                throw new Exception("Ce code journaux existe deja");
            }
        }else{
            String sql = "UPDATE codeJournaux SET "+colonne+" = '"+value+"' WHERE id = '"+id+"'";
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
        }
        cnx.close();
    }
}