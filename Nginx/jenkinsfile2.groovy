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
        stage("edit nginx config") {
            steps {
                script{
                dir('Nginx') {
                    sh 'cat nginx.conf'
                    def f = new File('nginx.conf')
                    f.text = f.text.replaceAll('$remote_host', 'http://localhost:5000')
                    f.text = f.text.replaceAll('$remote_addr', '192.168.0.1')
                    f.write(nginx.conf)
                    sh 'cat nginx.conf'
                    }
                }
            }
        }
        stage("Build Docker Image") {
            steps {
                script{
                    // sh '''echo $dockerhub_PSW | docker login -u elaad5 --password-stdin'''
                    sh 'docker login -u ${DockerHub_User} -p ${DockerHub_pwd}'
                    sh 'docker build -t ${DockerHub_User}/my_nginx:${tag} .'
                }
            }
        }
        stage("Push Docker Image") {
            steps {
                sh '''
                docker push ${DockerHub_User}/my_nginx:${tag}
                '''
            }
        }
    }
}