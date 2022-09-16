// ==UserScript==
// @name         Auto connect
// @version      1.0
// @description  Connection automatique au réseau wifi de la cité U (l'authentification est terrible)
// @author       Rhadamanthe 76 KIN 220
// @match        http://10.254.0.254:1000/fgtauth?*
// ==/UserScript==




(function() {
    const USERNAME = ""; // METTRE LE NOM D'UTILISATEUR ENTRE LES GUILLEMETS
    const PASSWORD = ""; // METTRE LE MOT DE PASSE ENTRE LES GUILLEMETS
    'use strict';
    var field_username = window.document.getElementById("ft_un");
    var field_password = window.document.getElementById("ft_pd");

    field_username.value = USERNAME;
    field_password.value = PASSWORD;

    window.document.forms[0].submit()

    // Your code here...
})();
