<%@ page import="structure.StructEntreprise" %>
<%@ page import="table.Entreprise" %>
<%@ page import="structure.StructFormulaire" %>
<%@ page import="table.Devise" %>
<%--
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
    StructFormulaire structFormulaire = new StructFormulaire();

    Entreprise ese = structEntreprise.entreprise;
%>

<div id="content">
    <div id="menu">
        <div class="titlebar">
            <div style="display: flex; justify-content: center; align-items: center">
                <a href="./entreprise.jsp?includePage=entrepriseHome">
                    <div class="precedent"><i class="fas fa-arrow-left"></i></div>
                </a>
            </div>
            <h2> - Modification <%= ese.getNom() %> - </h2>
            <h2></h2>
        </div>
        <h2></h2>

        <form action="" method="POST">
            <div id="form-content">
                <input type="hidden" name="idEntreprise" value="<%= ese.getId() %>">

                <div class="champ-label">Nom de l'Entreprise</div>
                <div class="champ-case"><input id="nomSociete" type="text" name="nomSociete" placeholder="Entreprise" value="<%=ese.getNom()  %>" required></div>

                <div class="champ-label">Domaine</div>
                <div class="champ-case"><input type="text" name="domaine" placeholder="Domaine" value="<%= ese.getDomaine() %>" required></div>

                <div class="champ-label">Siege</div>
                <div class="champ-case"><input type="text" name="siege" placeholder="Siege" value="<%= ese.getSiege() %>" required></div>

                <div class="champ-label">Pdg de l'entreprise</div>
                <div class="champ-case"><input type="text" name="boss" placeholder="PDG" value="<%= ese.getPdg() %>" required></div>

                <div class="champ-label">Numero d'identification Fiscale</div>
                <div class="champ-case">
                    <input type="number" name="nif" placeholder="NIF" value="<%= ese.getNif() %>" required>
                    <!-- <input type="file" multiple formenctype="multipart/form-data" placeholder="fichier"> -->
                </div>

                <div class="champ-label">Numero ... Statistique</div>
                <div class="champ-case"><input type="number" name="numStat" placeholder="SIF" value="<%= ese.getNumstat() %>" required></div>

                <div class="champ-label">Statut de l'Entreprise</div>
                <div class="champ-case"><input type="text" name="statut" placeholder="Statut" value="<%= ese.getStatut() %>" required></div>

                <div class="champ-label">Date debut d'exercice</div>
                <div class="champ-case"><input type="Date" name="debutExercice" placeholder="Date" value="<%= ese.getDatedebut() %>" required></div>

                <div class="champ-label">Devise utilis&eacute;e</div>
                <div class="champ-case">
                    <select name="Devise" id="devisechoisit" required>
                        <option value="" disabled>Devise</option>
                        <%
                            for(Devise dev : structFormulaire.listDevise){
                                if(dev.getId().equals(ese.getIdDevise()+"")){
                        %>
                                    <option value="<%= dev.getId() %>" selected><%= dev.getNom() %></option>
                        <%
                                }else{
                        %>
                        <option value="<%= dev.getId() %>"><%= dev.getNom() %></option>
                        <%

                                }
                            }
                        %>
                    </select>
                </div>

                <div class="champ-label">Devise d'&eacute;quivalence</div>
                <div class="champ-case">
                    <select name="DeviseEquiv" id="deviseequiv" required>
                        <option value="" disabled>devise d'&eacute;quivalence</option>
                        <%
                            for(Devise dev : structFormulaire.listDevise){
                                if(dev.getId().equals(ese.getIdDeviseEquiv()+"")){
                        %>
                                    <option value="<%= dev.getId() %>" selected><%= dev.getNom() %></option>
                        <%
                                }else{
                        %>
                                    <option value="<%= dev.getId() %>"><%= dev.getNom() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div id="submit-label"></div>
                <div id="submit-case"><input id="boutonValider" type="submit" value="Valider"></div>
            </div>
        </form>
    </div>
</div>
