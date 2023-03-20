$(document).ready(function () {
   $('.delete-link').click(function(event){
       event.preventDefault();

       // var td = $(this).closest('td');
       // var suppconfirm = document.getElementById('suppconfirm');
       // suppconfirm.style.opacity = '100%';
       // let childs = td.children();
       // for(let i = 0 ; i < childs.length ; i++){
       //     childs.remove();
       // }
       // td.append(suppconfirm)
       //
       // var sc = document.getElementById('suppconfirm');
       // let choices = sc.children;
       // for(let i = 0 ; i < childs.length ; i++){
       //     let choice = choices[i]
       //     choice.addEventListener('mouseclick', function () {
       //         console.log(choice.innerHTML)
       //     });
       // }

       var compte = $(this).data('compte');
       var row = $(this).closest('tr');

       $.ajax({
           url: '../deleteRow.MyController',
           type: 'POST',
           data: {  // variable envoyer en Parameter
               action: 'deleteRow',
               compte: compte
           },
           success: function () {
               row.remove();
           },
           error: function () {
               alert("Une erreur est survenue lors de la suppression de la ligne.")
           }
       });
   });
});

function suppressionConfirmer() {
    console.log("Oui")
}

function suppressionConfirmer() {
    console.log("Oui")
}