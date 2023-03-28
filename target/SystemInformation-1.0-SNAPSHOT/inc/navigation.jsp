<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 12/03/2023
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="navigationbar">
    <div></div>
    <div id="navigationguide">

    </div>
    <div id="naviconsbar">
        <a href="./index.jsp?includePage=home"><i class="fas fa-home navicone"> <!-- Accueil --></i></a>
        <a href="./index.jsp?includePage=formulaire"><i class="fas fa-plus navicone"> <!-- Ajouter une Entreprise --></i></a>
        <a href="./index.jsp?includePage=listes"><i class="far fa-list-alt navicone"> <!-- Liste des Entreprises --></i></a>
        <i class="fas fa-sun navicone navicone2" id="themes"> <!-- Switch Theme --></i>
    </div>

    <script src="./assets/js/navtools.js" rel="script" type="text/javascript"></script>
</div>