<%@ page import="structure.StructPlanTiers" %>
<%@ page import="table.PlanTiers" %>
<%@ page import="structure.StructCodeJournaux" %>
<%@ page import="table.CodeJournaux" %><%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-08
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idEntreprise = (String) session.getAttribute("idEntreprise");
    StructCodeJournaux structCodeJournaux = new StructCodeJournaux(idEntreprise);
%>

<div id="content">
    <div id="menu">
        <div class="titlebar">
            <div style="display: flex; justify-content: center; align-items: center">
                <a href="./entreprise.jsp?includePage=codeJournaux">
                    <div class="precedent"><i class="fas fa-arrow-left"></i></div>
                </a>
            </div>
            <h2> - Code Journaux - </h2>
            <h2></h2>
        </div>

        <div class="">
            <table class="tableau">
                <tr>
                    <th>Code</th>
                    <th>Intitul&eacute;</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    for(CodeJournaux cj : structCodeJournaux.codeJournaux){
                %>
                <tr class="ligneCompte">
                    <td style="max-width: 60px">
                        <div><%= cj.getCode() %></div>
                    </td>
                    <td>
                        <div class="intitule"><%= cj.getIntitule() %></div>
                    </td>
                    <td class="modifsuppr modif-linkCJ" data-id="<%= cj.getId() %>" data-code="<%= cj.getCode() %>" data-intitule="<%= cj.getIntitule() %>"><a href="#">Modifier</a></td>
                    <td class="modifsuppr delete-linkCJ" data-id="<%= cj.getId() %>" data-code="<%= cj.getCode() %>"><a href="#">Supprimer</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</div>

<div id="suppconfirm">
    <p style="color: red; cursor: pointer">oui</p>
    <p style="color: white; cursor: pointer">non</p>
</div>
