$(document).ready(function () {
   $('.modif-link').click(function (event) {
       event.preventDefault();

       var id = $(this).data('id');
       var compte = $(this).data('compte');
       var intitule = $(this).data('intitule');

       var previousValue = [];
       previousValue.push(compte);
       previousValue.push(intitule);

       var row = $(this).closest('tr');
       let cellules = row.children();

       for (let i = 0; i < 2; i++) {        //--------------------- 2 premiers cellule td
           cellules[i].innerHTML = ""
           var input = document.createElement("input");
           input.type = "text";
           input.value = previousValue[i];
           input.classList.add("modifPCinput");
           cellules[i].appendChild(input);

           input.addEventListener("blur", function () {
              var newvalue = this.value.trim();
              cellules[i].removeChild(this);
              var div = document.createElement("div");
              if(i === 0){
                  $.ajax({
                      url: '../updateRow.MyController',
                      type: 'POST',
                      data: {  // variable envoyer en Parameter
                          action: 'updateRowPC',
                          idPC: id,
                          oldcompte: compte,
                          colonne: 'compte',
                          value: newvalue
                      },
                      success: function () {
                          div.textContent = newvalue;
                          cellules[i].appendChild(div);
                      },
                      error: function () {
                          alert("Ce numero de compte existe deja")
                      }
                  });
              }
              if(i === 1){
                  div.classList.add("intitule");
                  $.ajax({
                      url: '../updateRow.MyController',
                      type: 'POST',
                      data: {  // variable envoyer en Parameter
                          action: 'updateRowPC',
                          idPC: id,
                          oldcompte: compte,
                          colonne: 'intitule',
                          value: newvalue
                      },
                      success: function () {
                          div.textContent = newvalue;
                          cellules[i].appendChild(div);
                      },
                      error: function () {
                          alert("Une exception est survenue(Inspectez : console/Reponse)");
                      }

                  });
              }
           });
       }

       var save = document.getElementById("saving");
       var saveIco = document.createElement('i');
       if(save.children.length === 0){
           saveIco.classList.add('fas');
           saveIco.classList.add('fa-check');
           saveIco.classList.add('saveButton');
           save.appendChild(saveIco);
       }else{
           saveIco = save.firstChild;
       }
       saveIco.addEventListener('click', function () {
           location.reload();
       });
   })


    $('.modif-linkPT').click(function (event) {
        event.preventDefault();

        var id = $(this).data('id');
        var compte = $(this).data('compte');
        var intitule = $(this).data('intitule');

        var previousValue = [];
        previousValue.push(compte);
        previousValue.push(intitule);

        var row = $(this).closest('tr');
        let cellules = row.children();
        for (let i = 0; i < 2; i++) {        //--------------------- 2 premiers cellule td
            cellules[i].innerHTML = ""
            var input = document.createElement("input");
            input.type = "text";
            input.value = previousValue[i];
            input.classList.add("modifPCinput");
            cellules[i].appendChild(input);

            input.addEventListener("blur", function () {
                var newvalue = this.value.trim();
                cellules[i].removeChild(this);
                var div = document.createElement("div");
                if(i === 0){
                    $.ajax({
                        url: '../updateRow.MyController',
                        type: 'POST',
                        data: {  // variable envoyer en Parameter
                            action: 'updateRowPT',
                            idPC: id,
                            oldcompte: compte,
                            colonne: 'numero',
                            value: newvalue
                        },
                        success: function () {
                            div.textContent = newvalue;
                            cellules[i].appendChild(div);
                        },
                        error: function () {
                            alert("Ce numero de compte existe deja")
                        }
                    });
                }
                if(i === 1){
                    div.classList.add("intitule");
                    $.ajax({
                        url: '../updateRow.MyController',
                        type: 'POST',
                        data: {  // variable envoyer en Parameter
                            action: 'updateRowPT',
                            idPC: id,
                            oldcompte: compte,
                            colonne: 'intitule',
                            value: newvalue
                        },
                        success: function () {
                            div.textContent = newvalue;
                            cellules[i].appendChild(div);
                        },
                        error: function () {
                            alert("Une exception est survenue(Inspectez : console/Reponse)");
                        }

                    });
                }


            });
        }

        var save = document.getElementById("saving");
        var saveIco = document.createElement('i');
        if(save.children.length === 0){
            saveIco.classList.add('fas');
            saveIco.classList.add('fa-check');
            saveIco.classList.add('saveButton');
            save.appendChild(saveIco);
        }else{
            saveIco = save.firstChild;
        }
        saveIco.addEventListener('click', function () {
            location.reload();
        });
    })

    $('.modif-linkCJ').click(function (event) {
        event.preventDefault();

        var id = $(this).data('id');
        var code = $(this).data('code');
        var intitule = $(this).data('intitule');

        var previousValue = [];
        previousValue.push(code);
        previousValue.push(intitule);

        var row = $(this).closest('tr');
        let cellules = row.children();
        for (let i = 0; i < 2; i++) {        //--------------------- 2 premiers cellule td
            cellules[i].innerHTML = ""
            var input = document.createElement("input");
            input.type = "text";
            input.value = previousValue[i];
            input.classList.add("modifPCinput");
            cellules[i].appendChild(input);

            input.addEventListener("blur", function () {
                var newvalue = this.value.trim();
                cellules[i].removeChild(this);
                var div = document.createElement("div");
                if(i === 0){
                    $.ajax({
                        url: '../updateRow.MyController',
                        type: 'POST',
                        data: {  // variable envoyer en Parameter
                            action: 'updateRowCJ',
                            idPC: id,
                            oldcompte: code,
                            colonne: 'code',
                            value: newvalue
                        },
                        success: function () {
                            div.textContent = newvalue;
                            cellules[i].appendChild(div);
                        },
                        error: function () {
                            alert("Ce numero de compte existe deja")
                        }
                    });
                }
                if(i === 1){
                    div.classList.add("intitule");
                    $.ajax({
                        url: '../updateRow.MyController',
                        type: 'POST',
                        data: {  // variable envoyer en Parameter
                            action: 'updateRowCJ',
                            idPC: id,
                            oldcompte: code,
                            colonne: 'intitule',
                            value: newvalue
                        },
                        success: function () {
                            div.textContent = newvalue;
                            cellules[i].appendChild(div);
                        },
                        error: function () {
                            alert("Une exception est survenue(Inspectez : console/Reponse)");
                        }

                    });
                }


            });
        }

        var save = document.getElementById("saving");
        var saveIco = document.createElement('i');
        if(save.children.length === 0){
            saveIco.classList.add('fas');
            saveIco.classList.add('fa-check');
            saveIco.classList.add('saveButton');
            save.appendChild(saveIco);
        }else{
            saveIco = save.firstChild;
        }
        saveIco.addEventListener('click', function () {
            location.reload();
        });
    })


});