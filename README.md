# Residsss Connect
Developpé par Rhadamanthe 76 KIN 220

C'est un ensemble de codes permetant de se connecter de manière automatique (ou pseudo-automatique) au wifi de la res, dont le système d'authentification est minable.

J'ai actuelement réalisé 2 méthodes de conection:

1. Application Android
	1. Fonctionnement général
	2. Fonctionnement sur Samsung
2. Script Navigateur
	1. Installer TamperMonkey
	2. Installer le script
	3. Rentrer les identifiants
	4. Utilisation

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
Il faut télécharger le fichier `Auto_onnect.user.js` ici: [release](https://github.com/Teiwin/ResidsssConnect/releases)  
Une fois l'extension installée :
(aller dans les paramètres de l'extension > onglet utilitaire > importer > selectionner le fichier `Auto_onnect.user.js`)  
Puis valider l'instalation du script.

### Rentrer les identifiants
Il faut ensuite cliquer sur modifier le script pour y rentrer les identifiants:  
(paramètres de l'extension > onglet Usersctipts installés > icone modifier le script)  
Renter le nom d'utilisateur et le mot de passe (respectivement **ligne 13 et 14**) entre les guillemets.

### Utilisation
Pour se connecter au wifi, il suffit d'ouvrir le navigateur et d'aller sur n'importe quelle page, vous serez redirigé (avec ou sans message) vers la page de connexion au wifi, et le script vous connectera.


