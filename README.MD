# Projet LOCALIB

## Description

Ce projet est la partie Backend d'une application de gestion de location de vehicules appelée Localib.

## Prérequis

* Java 11+
* [docker](https://www.docker.com/products/docker-desktop)
* [docker-compose](https://docs.docker.com/compose/install/)
* Un IDE Java ([Eclipse](https://www.eclipse.org/downloads/), [IntelliJ](https://www.jetbrains.com/fr-fr/idea/))

## Installation

### Installation de la base de données

Dans MySQL Workbench, créer une database appelée Localib.
Dans application.yml, mettre les bons nom d'utilisateur et mots de passe

### Installation du projet

Dans votre IDE, chargez le projet Maven `pom.xml`.

## Utilisation

### Lancement de l'application sans IDE

Pour lancer l'application, il faut se placer dans le dossier 'localib' et lancer la commande :

```bash
./mvnw bootRun
```

### Lancement de l'application avec un IDE

Dans votre IDE, lancez la classe `fr.olivier.formationcda.ecf4.localib.LocalibApplication`.

### Utilisation de l'application

L'application est accessible à l'adresse suivante: [http://localhost:8080]