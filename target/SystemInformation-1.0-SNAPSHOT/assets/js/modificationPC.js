$(document).ready(function () {
   $('.modif-link').click(function (event) {
       event.preventDefault();

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
              if(i === 1){
                  div.classList.add("intitule");
              }
              div.textContent = newvalue;
              cellules[i].appendChild(div);
           });
       }

       console.log(compte+" "+intitule);

   }) 
});