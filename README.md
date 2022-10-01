# Residsss Connect
Developpé par Rhadamanthe 76 KIN 220

C'est un ensemble de codes permetant de se connecter de manière automatique (ou pseudo-automatique) au wifi de la res, dont le système d'authentification est minable.

J'ai actuelement réalisé 2 méthodes de conection:

- [Residsss Connect](#residsss-connect)
  - [Application Android](#application-android)
    - [Fonctionnement général](#fonctionnement-général)
    - [Fonctionnement sur Samsung](#fonctionnement-sur-samsung)
  - [Script Navigateur](#script-navigateur)
    - [Installer TamperMonkey](#installer-tampermonkey)
    - [Installer le script](#installer-le-script)
    - [Utilisation](#utilisation)
- [RESET SCRIPT NAVIGATEUR](#reset-script-navigateur)

## Application Android
L'application est disponible ici: [release](https://github.com/Teiwin/ResidsssConnect/releases)  

### Fonctionnement général
Rentrer l'identifiant et le mot de passe dans l'application.  
Lors de la connexion au wifi, l'application est proposée comme méthode disponible. **Selectionner une fois seulement** sinon, l'appli vas envoyer vos identifiants a tous les réseaux wifi nécéssitant une connexion!  
**si l'appli n'est pas proposée**, suivre le Fonctionnement sur Samsung

### Fonctionnement sur Samsung
L'appli n'est pas proposée dans les méthodes d'authentification. Il faut alors l'ouvrir et appuyer sur le bouton "connect", **puis** déclancher la notification de connexion, qui détectera que le téléphone est déjà connecté.

## Script Navigateur

C'est un script JS qui est exécuté dès que le navigateur ouvre la page de connexion au wifi, il recherche les champs d'authentification, les remplit et envoie le formulaire.

### Installer TamperMonkey
Il suffit d'aller sur le lien suivant: [TamperMonkey](https://www.tampermonkey.net)  
Et de suivre les instructions pour installer l'extension navigateur

### Installer le script
(il semblerait que cette étape soit automatique sur firefox)  
Il faut télécharger le fichier `Auto_connect.user.js` ici: [release](https://github.com/Teiwin/ResidsssConnect/releases)  
Une fois l'extension installée :
(aller dans les paramètres de l'extension > onglet utilitaire > importer > selectionner le fichier `Auto_connect.user.js`)  
Puis valider l'instalation du script.

### Utilisation
Pour se connecter au wifi, il suffit d'ouvrir le navigateur et d'aller sur n'importe quelle page, vous serez redirigé (avec ou sans message) vers la page de connexion au wifi.  
La première fois que vous vous connecterez, il vous sera nécessaire de rentrer les identifiants. Ensuite, ils seront automatiquement enregistrés et le script vous connectera.

# RESET SCRIPT NAVIGATEUR
Installer de la même manière le script `Auto_Connect_RESET.user.js` disponible ici: [release](https://github.com/Teiwin/ResidsssConnect/releases)  
Aller dans les parramètres de l'extension > onglet Userscripts Installés > désactiver ResidsssConnect et activer Reset ResidsssConnect.  
Suivre les instructions [Utilisation](#utilisation) pour se connecter une fois.  
**faire la marche précédente pour réactiver `ResidsssConnect` et désactiver `Reset ResidsssConnect`**


