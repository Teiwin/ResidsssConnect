// ==UserScript==
// @name         ResidsssConnect
// @version      1.3
// @description  Connection automatique au réseau wifi de la cité U (l'authentification est terrible)
// @author       Rhadamanthe 76 KIN 220
// @include      /http:\/\/\d+\.\d+\.\d+\.\d+:?\d*\/fgtauth\?[a-z0-9]+/
// @updateURL    https://github.com/Teiwin/ResidsssConnect/raw/main/Auto_connect.user.js
// @downloadURL  https://github.com/Teiwin/ResidsssConnect/raw/main/Auto_connect.user.js
// ==/UserScript==



(function() {
    // set the title
    const title = document.getElementsByClassName("logo")[0];
    title.innerHTML = "ResidsssConnect";
    // add credits after the title
    const credits = document.createElement("h2");
    credits.innerHTML = "by Rhadamanthe 76";
    title.after(credits);
    // get the element after credits (please connect) and insert explications.
    const doc = credits.nextElementSibling;
    doc.innerHTML = "Please enter your username and password to continue.\nThe script will save the credentials for you and you will not have to connect again";
    'use strict';

    // credentials fields
    var field_username = window.document.getElementById("ft_un");
    var field_password = window.document.getElementById("ft_pd");

    // handle credentials saving
    const handleSubmit = (e) => {
        if (!localStorage.getItem("ResidsssConnect_ft_un")) {
            localStorage.setItem("ResidsssConnect_ft_un", field_username.value)
            console.log("username saved");
        }
        if (!localStorage.getItem("ResidsssConnect_ft_pd")) {
            localStorage.setItem("ResidsssConnect_ft_pd", field_password.value);
            console.log("password saved");
        }
        console.log("submited");
    };

    // bind submit function to the form
    const form = document.forms[0];
    form.addEventListener("submit", handleSubmit);

    // replace the submit button
    const submit = form.lastElementChild.children[0];
    submit.value = "Connect with ResidsssConnect";

    // fill the values if we can and if they aren't already filled
    if (!field_username.value){
        field_username.value = localStorage.getItem("ResidsssConnect_ft_un");
    }
    if (!field_password.value){
        field_password.value = localStorage.getItem("ResidsssConnect_ft_pd");
    }

    if (field_username.value && field_password.value) {
        form.submit();
    }
})();
