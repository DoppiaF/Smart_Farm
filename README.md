# Smart_Farm
Progetto per ingegneria del software avanzata relativo ad una fattoria digitalizzata che sfrutta sensori IoT per elaborare dati e adottare scelte in modo strategico

#############################################

*Presentazione* inserita all'interno di questo repository: https://github.com/DoppiaF/Smart_Farm/blob/main/presentazione/SMART%20FARM.pdf


*Requisiti* per istallare l'applicazione in locale:
> importare lo schema del database presente in questo repository https://github.com/DoppiaF/Smart_Farm/tree/main/database

> installare maven

Per *lanciare* l'applicazione in locale utilizzare il comando maven:
>$ mvn clean compile

>$ mvn javafx:run


**********************************************

*Requisiti* per utilizzare Docker:
> scaricare Docker
> attivare docker desktop
> scaricare vcxsrv
> attivare vcxsrv

Per lanciare l'applicazione containerizzata spostarsi nella cartella contenente il file docker compose:
>$ docker-compose build

>$ docker-compose up
