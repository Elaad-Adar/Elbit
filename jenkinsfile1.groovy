pipeline {
    agent any
     parameters {
        string(name: 'repoURL', defaultValue: 'https://github.com/Elaad-Adar/Elbit', description: 'Url of the GitHub repository')
        string(name: 'DockerHub_User', defaultValue: 'elaad5', description: 'your docker hub username')
        string(name: 'DockerHub_pwd', defaultValue: '', description: 'your docker hub password')
    }
    stages {
        stage("Clone Repo") {
            steps {
                git url: '${params.repoURL}'
            }
        }
        stage("Build Docker Image") {
            steps {
                sh '''
                docker build -t ${params.DockerHub_User}/flask-docker-app:latest .
                '''
            }
        }
        stage("Push Docker Image") {
            steps {
                sh '''
                docker login --username ${params.DockerHub_User} --password ${params.DockerHub_pwd}
                docker push ${params.DockerHub_User}/flask-docker-app:latest
                '''
            }
        }
    }
}