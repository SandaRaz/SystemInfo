<%@ page import="structure.StructPagination" %>
<%@ page import="table.CodeJournaux" %>
<%@ page import="org.apache.poi.ss.formula.functions.Code" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-08
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idEntreprise = (String) session.getAttribute("idEntreprise");
    StructPagination structPagination = new StructPagination(idEntreprise, "codeJournaux");
    List<CodeJournaux> codeJournaux = (List<CodeJournaux>) request.getAttribute("listesPC");
    int currentPage = (int) request.getAttribute("currentPage");
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
            <h2><%= currentPage %></h2>
        </div>

        <div class="btn-group search-button">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuClickable" data-bs-toggle="dropdown" data-bs-auto-close="false" aria-expanded="false">
                Search
            </button>
            <form class="dropdown-menu p-4" action="rechercherCJ.Recherche">
                <div class="mb-3">
                    <label for="exampleDropdownFormEmail2" class="form-label"><b>Compte</b></label>
                    <input type="text" class="form-control" id="exampleDropdownFormEmail2" placeholder="numéro du compte" name="compte">
                </div>
                <div class="mb-3">
                    <label for="exampleDropdownFormPassword2" class="form-label"><b>Intitulé</b></label>
                    <input type="text" class="form-control" id="exampleDropdownFormPassword2" placeholder="intitulé du compte" name="intitule">
                </div>
                <button type="submit" class="btn btn-primary search-submit">Chercher</button>
            </form>
        </div>

        <div class="">
            <table class="tableau">
                <tr>
                    <th>Code</th>
                    <th>Intitul&eacute;</th>
                    <th id="saving" style="text-align: center"></th>
                    <th></th>
                </tr>
                <%
                    for(CodeJournaux cj : codeJournaux){
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

        <%-------------------------------  PAGINATION ---------------------------------%>

        <nav aria-label="Page navigation example">
            <ul class="pagination" id="mon_pagination">
                <li class="page-item nextprevPage">
                    <a class="page-link" href="codeJournaux.Pagination?page=<% int i2 = (currentPage-1 == 0)?1:(currentPage-1);%><%= i2 %>&afficher=<%= structPagination.ligneAffichee %>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <%
                    for(int i = 1; i <= structPagination.nombrePage; i++){
                %>
                <li class="page-item numPage"><a class="page-link" href="codeJournaux.Pagination?page=<%= i %>&afficher=<%= structPagination.ligneAffichee %>"><%= i %></a></li>
                <%
                    }
                %>

                <li class="page-item nextprevPage">
                    <a class="page-link" href="codeJournaux.Pagination?page=<% int i3 = (currentPage+1 == (structPagination.nombrePage+1))?(structPagination.nombrePage):(currentPage+1);%><%= i3 %>&afficher=<%= structPagination.ligneAffichee %>" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>

        <%------------------------------------------------------------------------------%>
    </div>
</div>

<div id="suppconfirm">
    <p style="color: red; cursor: pointer">oui</p>
    <p style="color: white; cursor: pointer">non</p>
</div>
