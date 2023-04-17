package controller;

import cnx.Connex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import table.BDObject;
import table.CodeJournaux;
import table.PlanComptable;
import table.PlanTiers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Pagination", value = "*.Pagination")
public class Pagination extends MereController{

    @CtrlAnnotation(name = "planComptable")
    public void planComptable() throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("afficher"));

        int offset = limit * (page - 1);

        Connection cnx = Connex.PsqlConnect();

        List<PlanComptable> listes = new ArrayList<>();
        List<BDObject> tempList = null;
        try {
            tempList = new PlanComptable().FindOffsetLimit(cnx, "idEntreprise", idEse, offset, limit);
            for(BDObject bdo : tempList){
                listes.add((PlanComptable) bdo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", page);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichagePC");
        dispat.forward(request,response);
    }

    @CtrlAnnotation(name = "planTiers")
    public void planTiers() throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("afficher"));

        int offset = limit * (page - 1);

        Connection cnx = Connex.PsqlConnect();

        List<PlanTiers> listes = new ArrayList<>();
        List<BDObject> tempList = null;
        try {
            tempList = new PlanTiers().FindOffsetLimit(cnx, "idEntreprise", idEse, offset, limit);
            for(BDObject bdo : tempList){
                listes.add((PlanTiers) bdo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", page);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichagePT");
        dispat.forward(request,response);
    }

    @CtrlAnnotation(name = "codeJournaux")
    public void codeJournaux() throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        String idEse = (String) session.getAttribute("idEntreprise");

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("afficher"));

        int offset = limit * (page - 1);

        Connection cnx = Connex.PsqlConnect();

        List<CodeJournaux> listes = new ArrayList<>();
        List<BDObject> tempList = null;
        try {
            tempList = new CodeJournaux().FindOffsetLimit(cnx, "idEntreprise", idEse, offset, limit);
            for(BDObject bdo : tempList){
                listes.add((CodeJournaux) bdo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cnx.close();

        request.setAttribute("listesPC", listes);
        request.setAttribute("currentPage", page);
        RequestDispatcher dispat = request.getRequestDispatcher("./entreprise.jsp?includePage=affichageCJ");
        dispat.forward(request,response);
    }
}
