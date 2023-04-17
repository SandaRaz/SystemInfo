package controller;

import cnx.Connex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import table.BDObject;
import table.CodeJournaux;
import table.PlanComptable;
import table.PlanTiers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Recherche", value = "*.Recherche")
public class Recherche extends MereController{

    @CtrlAnnotation(name = "rechercherPC")
    public void rechercherPC() throws SQLException, IOException, ServletException {
        PrintWriter out = response.getWriter();

        String idEse = (String) request.getSession().getAttribute("idEntreprise");

        String compte = request.getParameter("compte");
        String intitule = request.getParameter("intitule");

        if(!compte.equals("")){
            compte += "%";
        }
        if(!intitule.equals("")){
            intitule = "%"+intitule+"%";
        }

        Connection cnx = Connex.PsqlConnect();

        List<PlanComptable> listes = new ArrayList<>();
        try{
            for(BDObject bdo : new PlanComptable().recherchePlanV1(cnx, "idEntreprise", idEse,"compte", compte, intitule.toLowerCase())){
                listes.add((PlanComptable) bdo);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", 1);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichagePC");
        dispat.forward(request,response);
    }

    @CtrlAnnotation(name = "rechercherPT")
    public void rechercherPT() throws SQLException, IOException, ServletException {
        PrintWriter out = response.getWriter();

        String idEse = (String) request.getSession().getAttribute("idEntreprise");

        String compte = request.getParameter("compte");
        String intitule = request.getParameter("intitule");

        if(!compte.equals("")){
            compte += "%";
        }
        if(!intitule.equals("")){
            intitule = "%"+intitule+"%";
        }

        Connection cnx = Connex.PsqlConnect();

        List<PlanTiers> listes = new ArrayList<>();
        try{
            for(BDObject bdo : new PlanTiers().recherchePlanV1(cnx, "idEntreprise", idEse,"numero", compte, intitule.toLowerCase())){
                listes.add((PlanTiers) bdo);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", 1);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichagePT");
        dispat.forward(request,response);
    }

    @CtrlAnnotation(name = "rechercherCJ")
    public void rechercherCJ() throws SQLException, IOException, ServletException {
        PrintWriter out = response.getWriter();

        String idEse = (String) request.getSession().getAttribute("idEntreprise");

        String compte = request.getParameter("compte");
        String intitule = request.getParameter("intitule");

        if(!compte.equals("")){
            compte += "%";
        }
        if(!intitule.equals("")){
            intitule = "%"+intitule+"%";
        }

        Connection cnx = Connex.PsqlConnect();

        List<CodeJournaux> listes = new ArrayList<>();
        try{
            for(BDObject bdo : new CodeJournaux().recherchePlanV1(cnx, "idEntreprise", idEse,"code", compte, intitule.toLowerCase())){
                listes.add((CodeJournaux) bdo);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", 1);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichageCJ");
        dispat.forward(request,response);
    }
}
