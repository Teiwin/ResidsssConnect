# Residsss Connect
Developpé par Rhadamanthe 76 KIN 220

C'est un petit code python qui run en arrière plan afin de se connecter au wifi de la résidence et de palier au captive portal déplorable.

Au premier lancement, il faut rentrer les 3 informations suivantes:
- Username
- Password
- Connectivity check period
		C'est la fréquence (en sec) à laquelle le programme va vérifier votre connexion au wifi et vous connecter si vous ne l'êtes plus.

Ces informations sont enregistrées dans `C:\Users\<you>\AppData\Local\Acme\SuperApp` et ne vous seront plus demandés.
