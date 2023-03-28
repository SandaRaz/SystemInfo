$(document).ready(function () {
    $.getJSON('loadTheme.Assets', function (theme) {
        // ----------- Body -------------
        $('body').css('background', theme.couleurBody);
        // ----------- menu -----------
        $('#menu').css('background', theme.couleurMenu);
        $('#menu').css('border-color', theme.couleurMenu);
        // ----------- formContent -----------
        $('#form-content').css('background', theme.couleurFormContent);
        $('#form-content').css('border-color', theme.couleurFormContent);

        $('.form-content').css('background', theme.couleurFormContent);
        $('.form-content').css('border-color', theme.couleurFormContent);
        // ----------- NavigationBar -----------
        $('#navigationbar').css('background', theme.couleurNavigationbar);
        $('#navigationbar').css('border-color', theme.couleurNavigationbar);
        // ----------- Footer ------------
        $('#footer').css('background', theme.couleurFooter);
        $('#footer').css('border-color', theme.couleurFooter);
        // ----------- SubMenu -----------
        $('#sub-menu').css('background', theme.couleurSubMenu);
        $('#sub-menu').css('border-color', theme.couleurSubMenu);
        // ----------- SubMenu2 -----------
        $('#sub-menu2').css('background', theme.couleurSubMenu2);
        $('#sub-menu2').css('border-color', theme.couleurSubMenu2);


        // ------------------ POLICE/FONT ----------------------
        $('body').css('color', theme.couleurPolice);
        $('#footer').css('color', theme.couleurPolice2);
        $('#navigationguide').css('color', theme.couleurPolice2);
        $('#menu h1,h2,h3').css('color', theme.couleurPolice2);
        $('.champ-label').css('color', theme.couleurPolice);
        $('.unEntreprise').css('color', theme.couleurPolice);
        $('.affichage-st').css('color', theme.couleurPolice);
        $('#menu h4').css('color', theme.couleurPolice);
        $('.tableau').css('color', theme.couleurPoliceTableau);
        $('.tableau').css('border-color', theme.couleurPoliceTableau);
        $('.tableau tr th').css('border-color', theme.couleurPoliceTableau);
        $('.tableau tr td').css('border-color', theme.couleurPoliceTableau);
        $('.modifPCinput').css('border-color', theme.couleurPoliceTableau);
        $('.modifPCinput').css('color', theme.couleurPoliceTableau);
    });

    $('#themes').click(function () {
        $.getJSON('changeTheme.Assets', function (theme) {
            // ----------- Body -------------
            $('body').css('background', theme.couleurBody);
            // ----------- menu -----------
            $('#menu').css('background', theme.couleurMenu);
            $('#menu').css('border-color', theme.couleurMenu);
            // ----------- formContent -----------
            $('#form-content').css('background', theme.couleurFormContent);
            $('#form-content').css('border-color', theme.couleurFormContent);

            $('.form-content').css('background', theme.couleurFormContent);
            $('.form-content').css('border-color', theme.couleurFormContent);
            // ----------- NavigationBar -----------
            $('#navigationbar').css('background', theme.couleurNavigationbar);
            $('#navigationbar').css('border-color', theme.couleurNavigationbar);
            // ----------- Footer ------------
            $('#footer').css('background', theme.couleurFooter);
            $('#footer').css('border-color', theme.couleurFooter);
            // ----------- SubMenu -----------
            $('#sub-menu').css('background', theme.couleurSubMenu);
            $('#sub-menu').css('border-color', theme.couleurSubMenu);
            // ----------- SubMenu2 -----------
            $('#sub-menu2').css('background', theme.couleurSubMenu2);
            $('#sub-menu2').css('border-color', theme.couleurSubMenu2);


            // ------------------ POLICE/FONT ----------------------
            $('body').css('color', theme.couleurPolice);
            $('#footer').css('color', theme.couleurPolice2);
            $('#navigationguide').css('color', theme.couleurPolice2);
            $('#menu h1,h2,h3').css('color', theme.couleurPolice2);
            $('.champ-label').css('color', theme.couleurPolice);
            $('.unEntreprise').css('color', theme.couleurPolice);
            $('.affichage-st').css('color', theme.couleurPolice);
            $('#menu h4').css('color', theme.couleurPolice);
            $('.tableau').css('color', theme.couleurPoliceTableau);
            $('.tableau').css('border-color', theme.couleurPoliceTableau);
            $('.tableau tr th').css('border-color', theme.couleurPoliceTableau);
            $('.tableau tr td').css('border-color', theme.couleurPoliceTableau);
        });
    });
});