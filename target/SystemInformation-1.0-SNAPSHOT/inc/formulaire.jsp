<%@ page import="structure.StructFormulaire" %>
<%@ page import="table.Devise" %><%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 12/03/2023
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StructFormulaire structFormulaire = new StructFormulaire();
%>

<div id="content">
    <div id="menu">
        <h2> - Informations - </h2>
        <form action="newEntreprise.MyController" method="POST">
            <div id="form-content">
                <div class="champ-label">Nom de l'Entreprise</div>
                <div class="champ-case"><input id="nomSociete" type="text" name="nomSociete" placeholder="Entreprise" required></div>

                <div class="champ-label">Domaine</div>
                <div class="champ-case"><input type="text" name="domaine" placeholder="Domaine" required></div>

                <div class="champ-label">Siege</div>
                <div class="champ-case"><input type="text" name="siege" placeholder="Siege" required></div>

                <div class="champ-label">Pdg de l'entreprise</div>
                <div class="champ-case"><input type="text" name="boss" placeholder="PDG" required></div>

                <div class="champ-label">Numero d'identification Fiscale</div>
                <div class="champ-case">
                    <input type="number" name="nif" placeholder="NIF" required>
                    <!-- <input type="file" multiple formenctype="multipart/form-data" placeholder="fichier"> -->
                </div>

                <div class="champ-label">Numero ... Statistique</div>
                <div class="champ-case"><input type="number" name="numStat" placeholder="SIF" required></div>

                <div class="champ-label">Statut de l'Entreprise</div>
                <div class="champ-case"><input type="text" name="statut" placeholder="Statut" required></div>

                <div class="champ-label">Date debut d'exercice</div>
                <div class="champ-case"><input type="Date" name="debutExercice" placeholder="Date" required></div>

                <div class="champ-label">Devise utilis&eacute;e</div>
                <div class="champ-case">
                    <select name="Devise" id="devisechoisit" required>
                        <option value="" disabled selected>Devise</option>
                <%
                    for(Devise dev : structFormulaire.listDevise){
                %>
                        <option value="<%= dev.getId() %>"><%= dev.getNom() %></option>
                <%
                    }
                %>
                    </select>
                </div>

                <div class="champ-label">Devise d'&eacute;quivalence</div>
                <div class="champ-case">
                    <select name="DeviseEquiv" id="deviseequiv" required>
                        <option value="" disabled selected>devise d'&eacute;quivalence</option>
                <%
                    for(Devise dev : structFormulaire.listDevise){
                %>
                        <option value="<%= dev.getId() %>"><%= dev.getNom() %></option>
                <%
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
