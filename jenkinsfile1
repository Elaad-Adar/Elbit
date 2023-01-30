pipeline {
    agent any
     parameters {
        string(name: 'repoURL', defaultValue: 'https://github.com/Elaad-Adar/Elbit', description: 'Url of the GitHub repository')
        string(name: 'DockerHub_User', defaultValue: 'elaad5', description: 'your docker hub username')
        string(name: 'DockerHub_pwd', defaultValue: '', description: 'your docker hub password')
        string(name: 'tag', defaultValue: 'latest', description: 'tag you image')
    }
//     environment {
//         dockerhub = credentials('dockerhub')
//     }
    stages {
        stage("Clone Repo") {
            steps {
                script{
                    git branch: 'main', url: repoURL
                }
            }
        }
        stage("Build Docker Image") {
            steps {
                script{
                    // sh '''echo $dockerhub_PSW | docker login -u elaad5 --password-stdin'''
                    sh 'docker login -u ${DockerHub_User} -p ${DockerHub_pwd}'
                    sh 'docker build -t ${DockerHub_User}/flask-docker-app:${tag} .'
                }
            }
        }
        stage("Push Docker Image") {
            steps {
                sh '''
                docker push ${DockerHub_User}/flask-docker-app:${tag}
                '''
            }
        }
    }
}