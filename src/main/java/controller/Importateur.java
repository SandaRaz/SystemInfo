package controller;

import cnx.Connex;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import table.CodeJournaux;
import table.PlanComptable;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import table.PlanTiers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Importateur", value = "*.Importateur")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class Importateur extends MereController{

    @CtrlAnnotation(name = "importXLSPC")
    public void importXLSPC() throws ServletException, IOException, SQLException{
        System.out.println(" ... Traitement Multipart");
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        Connection cnx = Connex.PsqlConnect();
        cnx.setAutoCommit(false);

        //if(ServletFileUpload.isMultipartContent((RequestContext) request)){
            try{
                Part filepart = request.getPart("xlsFile");
                String filename = filepart.getSubmittedFileName();
                String contenttype = filepart.getContentType();
                System.out.println("Filename: "+filename+" Contenttype: "+contenttype);
                InputStream inputStream = filepart.getInputStream();

                PlanComptable.planComptableXLS(cnx, inputStream, idEse);
            }catch(Exception e){
                System.out.println("Impossible de traiter le requete multipartie");
                e.printStackTrace();
            }finally {
                cnx.commit();
            }
        //}
        cnx.close();

        response.sendRedirect("./entreprise.jsp?includePage=planComptable");
    }

    @CtrlAnnotation(name = "importXLSPT")
    public void importXLSPT() throws ServletException, IOException, SQLException{
        System.out.println(" ... Traitement Multipart");
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        Connection cnx = Connex.PsqlConnect();
        cnx.setAutoCommit(false);

        try{
            Part filepart = request.getPart("xlsFile");
            String filename = filepart.getSubmittedFileName();
            String contenttype = filepart.getContentType();
            System.out.println("Filename: "+filename+" Contenttype: "+contenttype);
            InputStream inputStream = filepart.getInputStream();

            PlanTiers.planTiersXLS(cnx, inputStream, idEse);
        }catch(Exception e){
            System.out.println("Impossible de traiter le requete multipartie");
            e.printStackTrace();
        }finally {
            cnx.commit();
        }
        cnx.close();

        response.sendRedirect("./entreprise.jsp?includePage=planTiers");
    }

    @CtrlAnnotation(name = "importXLSCJ")
    public void importXLSCJ() throws ServletException, IOException, SQLException{
        System.out.println(" ... Traitement Multipart");
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        Connection cnx = Connex.PsqlConnect();
        cnx.setAutoCommit(false);

        try{
            Part filepart = request.getPart("xlsFile");
            String filename = filepart.getSubmittedFileName();
            String contenttype = filepart.getContentType();
            System.out.println("Filename: "+filename+" Contenttype: "+contenttype);
            InputStream inputStream = filepart.getInputStream();

            CodeJournaux.codeJournauxXLS(cnx, inputStream, idEse);
        }catch(Exception e){
            System.out.println("Impossible de traiter le requete multipartie");
            e.printStackTrace();
        }finally {
            cnx.commit();
        }
        cnx.close();

        response.sendRedirect("./entreprise.jsp?includePage=codeJournaux");
    }
}
