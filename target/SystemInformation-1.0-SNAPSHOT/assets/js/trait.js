// ----------------- general -------------------
var formContent = document.getElementById('form-content')
var champs = formContent.getElementsByTagName('input')

var boutonValider = document.getElementById('boutonValider')
var submitbouton = document.getElementById('submit-case')
var submitlabel = document.getElementById('submit-label')

var valider = submitbouton.getElementsByTagName('input')
var valider2 = submitlabel.getElementsByTagName('input')

var nomsociete = document.getElementById('nomSociete')
nomsociete.oninput = function() {
    if(nomsociete.value.length >= 30){
        nomsociete.classList.add('char-non-valide')
    }else{
        nomsociete.classList.remove('char-non-valide')
    }
}

let devisevalide = true
var devisechoisit = document.getElementById('devisechoisit')
let deviseequiv = document.getElementById('deviseequiv')
if(devisechoisit.value == "" || deviseequiv.value == ""){
    devisevalide = false
}

deviseequiv.onclick = function () {
    if(deviseequiv.value === devisechoisit.value){
        devisevalide = false
        deviseequiv.style.color = 'red'
    }else{
        devisevalide = true
        deviseequiv.style.color = 'dimgrey'
    }
};

boutonValider.addEventListener( 'mouseenter' , function(){
    var complet = true
    for(let i = 0 ; i < champs.length ; i++){       // verifier si les champs sont tous bien remplis
        if(champs[i].value == ''){
            complet = false
            break
        }
    }

    if(!complet || !devisevalide){
        if(valider.length > 0){
            submitlabel.appendChild(valider[0])
        }else{
            submitbouton.appendChild(valider2[0])
        }
    }else{
        if(valider.length == 0){
            submitbouton.appendChild(valider2[0])
            console.log("val1 = "+valider.length+" val2 = "+valider2.length)
        }
    }
});

// ---------------------------------------------

