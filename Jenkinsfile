pipeline {
    tools{
        maven 'M2_HOME'
    }
   // environment { 
     //   registry = "styve01/CheminDeFer" 
      //  registryCredential = 'dockerhub_id' 
      //  dockerImage = '' 
   // }
    agent any

    stages {
        stage('Git') {
            steps {
                git url: 'https://github.com/Styvekamga/CheminDeFer.git', branch: 'Styve'
            }
        }
        stage('Maven') {
            steps {
                sh 'mvn clean install'
                sh 'mvn compile'
            }
        }

       stage('Maven Test') {
            steps {
                sh 'mvn test ' 
            }
        }
        stage('SonarQube') {
            steps {
                sh 'mvn sonar:sonar  -Dsonar.login=admin -Dsonar.password=sonar'  
            }
        } 
       stage('Nexus'){
            steps{
                sh 'mvn deploy -DskipStaging=true'
            }
        } 
       stage('Docker Enable CD'){
            steps{
                sh 'docker-compose --version'
            }
        } 
        stage('Building Docker image') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Deploy image') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        } 
        stage("Compose"){
            steps{
                sh 'docker-compose up'
            }
        }
    }

}