<%@ page import="structure.StructPlanTiers" %>
<%@ page import="table.PlanTiers" %><%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-08
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idEntreprise = (String) session.getAttribute("idEntreprise");
    StructPlanTiers structPlanTiers = new StructPlanTiers(idEntreprise);
%>

<div id="content">
    <div id="menu">
        <div class="titlebar">
            <div style="display: flex; justify-content: center; align-items: center">
                <a href="./entreprise.jsp?includePage=planTiers">
                    <div class="precedent"><i class="fas fa-arrow-left"></i></div>
                </a>
            </div>
            <h2> - Plan Tiers - </h2>
            <h2></h2>
        </div>

        <div class="">
            <table class="tableau">
                <tr>
                    <th>Num&eacute;ro</th>
                    <th>Intitul&eacute; du compte</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    for(PlanTiers pc : structPlanTiers.planTiers){
                %>
                <tr class="ligneCompte">
                    <td style="max-width: 60px">
                        <div><%= pc.getNumero() %></div>
                    </td>
                    <td>
                        <div class="intitule"><%= pc.getIntitule() %></div>
                    </td>
                    <td class="modifsuppr modif-linkPT" data-id="<%= pc.getId() %>" data-compte="<%= pc.getNumero() %>" data-intitule="<%= pc.getIntitule() %>"><a href="#">Modifier</a></td>
                    <td class="modifsuppr delete-linkPT" data-id="<%= pc.getId() %>" data-compte="<%= pc.getNumero() %>"><a href="#">Supprimer</a></td>
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
