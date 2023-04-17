<%@ page import="structure.StructEntreprise" %><%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-22
  Time: 12:58
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
        </h2>

        <div id="sub-menu2">

        </div>
    </div>
</div>
