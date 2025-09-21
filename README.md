# 2025 Derryfield ISP (mlswebsite)

This API will be used to access and update the data about the teams and players for the website.

## Running this project
### Prerequisites
- Install Maven to the command line
  - MACOSX: `brew install maven`
- Install Docker to the command line
  - MACOSX: `brew install --cask docker` (If docker is already installed
  uninstall it using the commands below first)
  ```bash
    brew uninstall --cask docker --force
    brew uninstall --formula docker --force
  ```
  
### Building the API
#### Local Database
To run this project locally you will need to start up a docker container that
contains both the API and the database. This database will be empty and you will
need to fill it.

1. Clone the repo
2. Create a file called `.env` with the following values and place it in the same directory as the project
   ```vi
     DB_USERNAME=<your usernam>
     DB_PASSWORD=<your password>
   ```
   For the purpose of running the database locally these values can be anything.
3. Build the project `mvn clean package`
4. Start the docker container `docker compose -f docker-compose.yml -f docker-compose.local.yml up --build`. This will create the database and open the server on port 8080
5. Confirm the server is running by sending a `GET` request to https://localhost:8080/ping (you should see "pong" as the response)

#### Remote Database
To run this project with a persistent database you need to change your environment config to use the database url
1. Clone the repo
2. Create a file called `.env` with the following values and place it in the same directory as the project
   ```vi
     DB_URL=<remote database url>
     DB_USERNAME=<remote database usernam>
     DB_PASSWORD=<remote database password>
   ```
3. Build the project `mvn clean package`
4. Start the docker container `docker compose -f docker-compose.yml -f docker-compose.prod.yml up --build`. This will connect to the remote database and open the server on port 8080
5. Confirm the server is running by sending a `GET` request to https://localhost:8080/ping (you should see "pong" as the response)

## Troubleshooting
### I Can't Connect to the local Database
If you have changed the password to your local database then you need to completely delete the docker container for that database
1. Shut down all docker containers `docker compose down -v`
2. Force remove all docker containers `docker rm -f \$(docker ps -aq)`
3. Remove the database db volume `docker volume rm api1_db-data`
4. Restart your container