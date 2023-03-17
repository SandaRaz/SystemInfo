var navigationguide = document.getElementById("navigationguide")
var navicons = document.getElementsByClassName("navicone")

function getCommentaire(icone) {
    var comment = ""
    for(var i = 0 ; i < icone.childNodes.length ; i++){
        if(icone.childNodes[i].nodeType == Node.COMMENT_NODE){
            comment = icone.childNodes[i].nodeValue;
        }
    }
    return comment
}

for(let i = 0; i < navicons.length ; i++){
    let navicon = navicons[i]
    navicon.addEventListener('mouseenter', function () {
        navigationguide.innerHTML = getCommentaire(navicon)
        console.log(getCommentaire(navicon))
    });
}

for(let i = 0; i < navicons.length ; i++){
    let navicon2 = navicons[i]
    navicon2.addEventListener( 'mouseleave', function () {
        navigationguide.innerHTML = ""
    });
}
