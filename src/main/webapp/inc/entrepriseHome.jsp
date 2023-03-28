<%@ page import="structure.StructEntreprise" %><%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-17
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idEntreprise = "";
    if(session.getAttribute("idEntreprise") != null){
        idEntreprise = (String) session.getAttribute("idEntreprise");
    }
    StructEntreprise structEntreprise = new StructEntreprise(idEntreprise);
%>

<div id="content">
    <div id="menu">
        <h2>
            <%= structEntreprise.entreprise.getNom() %>
            <a href="./entreprise.jsp?includePage=modifEse"><i class="fas fa-pen navicone2"></i></a>
            <a href="./entreprise.jsp?includePage=recherche"><i class="fas fa-search navicone2"></i></a>
        </h2>

        <div id="sub-menu2">
            <a href="./entreprise.jsp?includePage=planComptable">
                <div class="affichage">
                    <p class="menu-nav">PC</p>
                </div>
            </a>
            <a href="./entreprise.jsp?includePage=planTiers">
                <div class="affichage">
                    <p class="menu-nav">PT</p>
                </div>
            </a>
            <a href="./entreprise.jsp?includePage=codeJournaux">
                <div class="affichage">
                    <p class="menu-nav">CJ</p>
                </div>
            </a>

            <div class="affichage-st">Plan Comptable</div>
            <div class="affichage-st">Plan Tiers</div>
            <div class="affichage-st">Code Journaux</div>

        </div>
    </div>
</div>
