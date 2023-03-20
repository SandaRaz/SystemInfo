package controller;

import cnx.Connex;
import exception.DeviseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import module.Fonction;
import table.Entreprise;
import table.PlanComptable;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

@WebServlet(name = "MyController", value = "*.MyController")
public class MyController extends MereController{

    @CtrlAnnotation(name = "test")
    public void test() throws IOException {
        PrintWriter out = response.getWriter();
    }

    @CtrlAnnotation(name = "newEntreprise")
    public void InsertNewEntreprise() throws SQLException, ParseException, ServletException, IOException, DeviseException {
        String nomEse = request.getParameter("nomSociete");
        String domaine = request.getParameter("domaine");
        String siege = request.getParameter("siege");
        String pdg = request.getParameter("boss");
        String nif = request.getParameter("nif");
        String sif = request.getParameter("numStat");
        String status = request.getParameter("statut");
        String debutExercice = request.getParameter("debutExercice");
        String datefin = Fonction.addMonthToDate(debutExercice, 12);

        int iddevise = Integer.parseInt(request.getParameter("Devise"));
        int iddeviseUtilisee = Integer.parseInt(request.getParameter("DeviseEquiv"));

        if(iddevise == iddeviseUtilisee){
            throw new DeviseException("Veuillez choisir un autre devise d'equivalence");
        }

        Connection cnx = Connex.PsqlConnect();
        cnx.setAutoCommit(false);
        try {
            Entreprise ese = new Entreprise("default",nomEse,domaine,siege,pdg,nif,sif,status,debutExercice,datefin,iddevise,iddeviseUtilisee);
            ese.InsertInto(cnx);
        } catch (Exception e) {
            cnx.rollback();
            redirect("./index.jsp");
            e.printStackTrace();
        }finally {
            cnx.commit();
            cnx.close();
        }
        redirect("./index.jsp?includePage=formulaire");
    }

    @CtrlAnnotation(name = "ListeEntreprise")
    public void ListeEntreprise() throws ServletException, IOException {
        String idEse = request.getParameter("idEse");
        HttpSession session = request.getSession();
        session.setAttribute("idEntreprise", idEse);

        response.sendRedirect("./pages/entreprise.jsp?includePage=entrepriseHome");
    }

    @CtrlAnnotation(name = "planComptable")
    public void planComptable() throws IOException, SQLException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String numcompte = request.getParameter("numCompte");
        String intitule = request.getParameter("intitule");
        String idEse = (String) session.getAttribute("idEntreprise");

        boolean success = true;

        Connection cnx = Connex.PsqlConnect();
        try {
            cnx.setAutoCommit(false);

            PlanComptable newplan = null;
            newplan = (PlanComptable) new PlanComptable().createInstancefromDB(cnx, numcompte);

            if(newplan != null){
                throw new Exception("Ce compte numero "+numcompte+" existe deja");
            }else{
                newplan = new PlanComptable(numcompte, intitule, idEse);
                newplan.InsertInto(cnx);
            }
        } catch (Exception e) {
            cnx.rollback();
            success = false;

            e.printStackTrace();
        }finally {
            cnx.commit();
            cnx.close();
        }

        if(!success){
            redirect("./entreprise.jsp?includePage=planComptable");
        }else{
            redirect("./entreprise.jsp?includePage=entrepriseHome");
        }
    }

    @CtrlAnnotation(name = "deleteRow")
    public void deleteRow() throws SQLException {
        String action = request.getParameter("action");
        String compte = request.getParameter("compte");

        if(action.equals("deleteRow")){
            PlanComptable.deleteRow(compte);
        }
    }
}
