<%@ page import="structure.StructListEntreprise" %>
<%@ page import="table.Entreprise" %><%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 12/03/2023
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StructListEntreprise structListEntreprise = new StructListEntreprise();
%>
<div id="content">
    <div id="menu">
        <h2> - Liste d'entreprise - </h2>
        <div id="sub-menu">
            <%
                for(Entreprise ese : structListEntreprise.listes){
            %>
            <a href="./pages/entreprise.jsp?idEse=<%= ese.getId() %>">
                <div class="unEntreprise">
                    <%= ese.getNom() %>
                </div>
            </a>
            <%
                }
            %>
        </div>
    </div>
</div>