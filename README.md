# Residsss Connect
Developpé par Rhadamanthe 76 KIN 220

C'est un ensemble de codes permetant de se connecter de manière automatique (ou pseudo-automatique) au wifi de la res, dont le système d'authentification est minable.

J'ai actuelement réalisé 1 méthode de conection:

1. [Script Navigateur](Script Navigateur)
	1. Installer TamperMonkey
	2. Installer le script

## Script Navigateur
======================
C'est un script JS qui est exécuté dès que le navigateur ouvre la page de connexion au wifi, il recherche les champs d'authentification, les remplit et envoie le formulaire.

### Installer TamperMonkey
Il suffit d'aller sur le lien suivant: [TamperMonkey](https://www.tampermonkey.net)
Et de suivre les instructions pour installer l'extension navigateur

### Installer le script
Il faut télécharger le script ici: [release]()
Une fois l'extension installée, aller dans les préférences de l'extension, onglet utilitaire (en haut a droite), puis importer. Sélectionnez le fichier zip téléchargé et validez l'importation.

### Utilisation
Pour se connecter au wifi, il suffit d'ouvrir le navigateur et d'aller sur n'importe quelle page, vous serez redirigé (avec ou sans message) vers la page de connexion au wifi, et le script vous connectera.


