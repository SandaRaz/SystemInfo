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
        <h2><%= structEntreprise.entreprise.getNom() %></h2>
        <div id="sub-menu">
                <ul>
                    <a href="./entreprise.jsp?includePage=planComptable"><li>Plan Comptable</li></a>
                </ul>
        </div>
    </div>
</div>
