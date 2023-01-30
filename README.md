# Elbit Home Assignment

## Background
I deployed Jenkins as container `jenkins/blueocean` In order to give Jenkins access to the Docker daemon running on the host machine (Windows), we will use Docker-in-Docker(dind). <br>
This will allow Jenkins to run docker commands inside the container, the dind will listen on port 2375 (skipped TLS for ease of use).<br>
I used the blueocean image as a base for this assignment using docker-in-docker method. I used the following commands to build the image:
<br>
once up and running i've configured the cloud ("Manage nodes and clouds") and set the ducker URI to tcp://docker:2375 (docker is the name of the container running dind).<br>
in docker desktop i've checked the "expose daemon on tcp://localhost:2375 without TLS" option.<br>
there is also a docker-compose file that will run the jenkins container and the dind container.<br>

Assumptions:
* I've used parameters to get the docker image name and tag from the user and also the repo to clone.
* one can also use saved credentials for the user and password (I've created and ==docker access token== instead of password), shown in jenkinsfile1 in commented out code.
* 


## Jenkinsfiles

### Jenkinsfile1
This jenkins file clones the repo and builds the docker image using the dockerfile in the repo. It then pushes the image to docker hub.<br>
parameters - all but `DockerHub_pwd` parameter have default values to what I used in the assignment.<br>

### Jenkinsfile2
