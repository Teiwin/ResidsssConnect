// ==UserScript==
// @name         Dev Auto connect
// @version      1.0
// @description  Connection automatique au réseau wifi de la cité U (l'authentification est terrible)
// @author       Rhadamanthe 76 KIN 220
// @match        file:///home/justin/Dev_n_Class/captiveportal/Firewall%20Authentication.html
// ==/UserScript==




(function() {
    const USERNAME = ""; // METTRE LE NOM D'UTILISATEUR ENTRE LES GUILLEMETS
    const PASSWORD = ""; // METTRE LE MOT DE PASSE ENTRE LES GUILLEMETS
    'use strict';

    function getLoginFields() {
        // we need to detect the couples of username and password present in the page in order to fill them.
        let couple = [];

        // loop on every input to search for password type input.
        const inputs = document.getElementsByTagName("input");
        let passwords_fields = [];
        for (let i = inputs.length-1; i>=0; i--) {
            if (inputs[i].type === 'password'){
                passwords_fields.push(inputs[i]);
            }
        }

        // get the form corresponding to each password input
        const parentForm = function (field) {
            while (field.parentNode) {
                if (field.parentNode.nodeName === "FORM") {
                    return field.parentNode;
                }
                field = field.parentNode;
            }
        }

        // get inputs of the form and select the username and password ones.
        for (let i = 0; i<passwords_fields.length; i++) {
            const form = parentForm(passwords_fields[i]);
            const form_inputs = form.getElementsByTagName("input");
            for (let input_index = 0; input_index< form_inputs.length; input_index++){
                if (form_inputs[input_index] !== passwords_fields[i] && form_inputs[input_index].type === 'text'){
                    // if the input field is type text and is not the current password input
                    couple.push([form_inputs[input_index], passwords_fields[i], form]);
                }
            }
        }

        return couple

    }

    let couple = getLoginFields();
    // if we match only one couple (as it should have only one login form)
    if (couple.length === 1) {
        // we log in
        couple = couple[0];
        couple[0].value = USERNAME;
        couple[1].value = PASSWORD;
        couple[2].submit();

    } else {
        // the login page is not good, could create a menu to ask which form to fill.
        couple[0][0].value = "ERROR: MULTIPLE FORMS";
    }

})();
