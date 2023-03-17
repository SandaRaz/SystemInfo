<%--
  Created by IntelliJ IDEA.
  User: sanda
  Date: 2023-03-08
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="content">
    <div id="menu">
        <h2> - Plan Comptable - </h2>
        <form action="" method="POST">
            <div id="form-content">
                <div class="champ-label">Compte</div>
                <div class="champ-case"><input id="numCompte" type="text" name="numCompte" placeholder="num&eacute;ro" required></div>

                <div class="champ-label">Intitul&eacute;</div>
                <div class="champ-case"><input id="intitule" type="text" name="intitule" placeholder="Intitul&eacute;" required></div>

                <div id="submit-label"></div>
                <div id="submit-case"><input id="boutonValider" type="submit" value="Ajouter"></div>
            </div>
        </form>

        <form action="" method="POST" enctype="multipart/form-data">
            <h4>Importation &agrave; partir d'un fichier</h4>
            <div id="form-content2">
                <div class="champ-label">Fichier xls</div>
                <div class="champ-case"><input id="xls" type="file" name="xlsFile" placeholder="num&eacute;ro"></div>

                <div class="champ-label">Fichier csv</div>
                <div class="champ-case"><input id="csv" type="file" name="csvFile" placeholder="Intitul&eacute;"></div>

                <div id="submit-label"></div>
                <div id="submit-case"><input id="boutonValider" type="submit" value="Ajouter"></div>
            </div>
        </form>
    </div>
</div>