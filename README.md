# Sujet
Système de gestion de la maintenance automobile et prototype d'un système d'aide au diagnostic automobile. [infos](https://github.com/rocdane/loga-project)

## Description
La maintenance automobile est un domaine du secteur de l’automobile qui a pour but de gérer les services d’entretien et de réparation des véhicules automobile. Les garagistes, acteurs de ce domaine, font face à certaines difficultés liés à la gestion des maintenances.

Tout garagiste souhaite la bonne gestion de ses affaires incluant la résolution des pannes qu’il a l’opportunité de rencontrer. Toutefois, la maintenance automobile requiert un haut niveau d’intelligence, parfois au dessus des capacités humaines pour résoudre ces derniers.

Dans le cadre du travail de fin d'étude pour l'obtention du diplôme de Master en informatique, nous apportons une solution dédiée à la bonne gestion des maintenances automobile. Le résultat que nous obtiendrons est un système d'informations de prise de décision capable d'assister les garagistes dans leur métier.

![SOLUTION](media/solution.png)

## Fonctionnalités
![USE_CASE](media/usecase.png)
### Releases
1. Gestion informatisée des informations clients
2. Gestion informatisée des informations de maintenance
3. Service intelligent d'aide au diagnostic
4. Monitoring et reporting des informations de gestion

### Perspectives
5. Constitution d'un stock virtuel de pièces de rechanges
6. Service intelligent d'aide à la commande des pièces de rechanges
7. Gestion informatisée de l'approvisionnement en pièce de rechanges
8. Gestion informatisée des ressources humaines
9. Gestion informatisée des ressources matérielles
10. Gestion informatisée du flux financier
11. Elaboration du bilan comptable


## Conceptions architecturales
### Diagramme de séquence du service intelligent
![INTELLIGENCE_SEQUENCE](media/comportement/intelligence_sequence_diagram.png)
### Diagramme de séquence du service de diagnostic
![DIAGNOSIS_SEQUENCE](media/comportement/diagnosis_sequence_diagram.png)
### Diagramme de séquence du service de réparation
![REPAIR_SEQUENCE](media/comportement/repair_sequence_diagram.png)
### Diagramme de classe du service intelligent
![INTELLIGENCE_CLASS](media/structure/intelligent_service_class_diagram.png)
### Diagramme de classe du service client
![CUSTOMER_CLASS](media/structure/customer_service_class_diagram.png)
### Diagramme de classe du service de diagnostic
![DIAGNOSIS_CLASS](media/structure/diagnosis_service_class_diagram.png)
### Diagramme de classe du service de réparation
![REPAIR_CLASS](media/structure/repair_service_class_diagram.png)
### Diagramme de composant
![COMPONENT](media/structure/component_diagram.png)
### Diagramme de déploiement
![DEPLOYMENT](media/structure/deployment_diagram.png)

## Programmation
```json
{
  "environment":{
  	"OS":"Ubuntu 22.10",
  	"IDE":"Intellij IDEA Community",
  	"JDK":"openjdk-19"
  },
  "code":{
  	"Langage":"Java",
  	"Framework":{
  		"client":"openjfx-18",
  		"server":[
  			"spring-boot-3.0.6", 
  			"spring-cloud-2022.0.2"
  		]
  	}
  },
  "build":{
  	"maven":"3.6.3",
  	"git":"2.41.0"
  },
  "test":{
  	"jenkins":"2.401.2"
  },
  "deployment":{
  	"docker":"24.0.2"
  }
}
```
## Résultats
### Ontologie d'application
#### Graphe de l'ontologie de diagnostic automobile
![ONTOLOGIE](media/ontologie/automaintontology.png)
#### Individus de la classe Dysfonctionnement de l'ontologie
![DYSFUNCTION](media/ontologie/indiv_dysfonctionnement.png)
#### Individus de la classe Maintenance de l'ontologie
![MAINTENANCE](media/ontologie/indiv_maintenance.png)
### Interfaces
#### Menu principal
![IHM ACCUEIL](media/ihm/ihm-accueil.png)
#### Menu Atelier
![IHM ACCUEIL ATELIER](media/ihm/ihm-accueil-atelier.png)
#### Tableau de bord
![IHM DASHBOARD](media/ihm/ihm-dashboard.png)
#### Interface de gestion des automobiles
![IHM AUTOMOBILE](media/ihm/ihm-gestion-automobile.png)
#### Interface de gestion des clients
![IHM CLIENT](media/ihm/ihm-gestion-client.png)
#### Assistance de saisie des dysfonctionnements
![IHM RECHERCHE DYSFUNCTION](media/ihm/recherche_dysfonctionnement.png)
#### Assistance de saisie des maintenances
![IHM RECHERCHE MAINTENANCE](media/ihm/recherche_maintenance.png)
#### Succès d'enregistrement du diagnostic
![IHM DIAGNOSTIC](media/ihm/succes_diagnostic.png)
### Exemple de rapport de diagnostic
![RAPPORT](media/rapport_diagnostic.png)

## Datas
### Datamarts de données clients
![RAPPORT](media/data/datamarts_dossier.png)
### Datamarts des données de diagnostic
![RAPPORT](media/data/datamarts_diagnosis.png)
### Datamarts des données de réparation
![RAPPORT](media/data/datamarts_repair.png)
