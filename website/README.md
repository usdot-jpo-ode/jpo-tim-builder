# TIM Builder Web App

## Configuration: 

There are environment config files located in website/src/environments

### Fields in environment files:

* production - Will run the Angular2 app in production mode (not tested)
* odeUrl - The URL where the ODE is running e.g. http://localhost:8080
* dbUrl - The URL where the REST Service is running. When using the Docker configuration this will always be http://localhost:7777
* mapCenterPoint - The longitude/latitude point for centering the map
* zoom - The zoom level for the map. See Esri documentation for more details

To choose which environment files to use: 
* Change what "environment" is set to on the line "start": "ng serve --environment=dev -H 0.0.0.0" in website/package.json 
* "environment=dev" will use environment.ts
* "environment=test" will use environment.test.ts
* "environment=prod" will use environment.prod.ts
* These settings can be changed in website/.angular-cli.json

Rebuild the web app Docker container for changed to take effect