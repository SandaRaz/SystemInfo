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
        <div class="titlebar">
            <div style="display: flex; justify-content: center; align-items: center">
                <a href="./entreprise.jsp?includePage=entrepriseHome">
                    <div class="precedent"><i class="fas fa-arrow-left"></i></div>
                </a>
            </div>
            <h2> - Plan Tiers - </h2>
            <h2></h2>
        </div>

        <h4>Importer manuellement</h4>

        <form action="planTiers.MyController" method="POST">
            <div class="form-content">
                <div class="champ-label">Num&eacute;ro/Nom</div>
                <div class="champ-case"><input id="numCompte" type="text" name="numCompte" placeholder="num&eacute;ro" required></div>

                <div class="champ-label">Intitul&eacute;</div>
                <div class="champ-case"><input id="intitule" type="text" name="intitule" placeholder="Intitul&eacute;" required></div>

                <div></div>
                <div><input class="boutonValider" type="submit" value="Ajouter"></div>
            </div>
        </form>

        <h4>Importation &agrave; partir d'un fichier</h4>

        <form action="importXLSPT.Importateur" method="POST" enctype="multipart/form-data">
            <div class="form-content">
                <div class="champ-label">Fichier xls</div>
                <div class="champ-case inputfile"><input id="xls" type="file" name="xlsFile" multiple></div>

                <div></div>
                <div><input class="boutonValider" type="submit" value="Ajouter"></div>
            </div>
        </form>

        <a href="./entreprise.jsp?includePage=affichagePT"><h4>Affichage plan tiers</h4></a>
    </div>
</div>

<script src="../assets/input-file-master/input-file.min.js" rel="script" type="text/javascript"></script>
<script>
    new InputFile({
        buttonText: 'Choisir un fichier',
        hint: 'glisser ici'
    });
</script>