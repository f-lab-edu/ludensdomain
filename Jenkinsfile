pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Poll') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean verify -DskipITs=true';
                }
            }
        }

        stage('Unit Test') {
            steps {
                script {
                    sh 'mvn surefire:test'
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Archive') {
            steps {
                archive '**/target/*.jar'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Steps before deployment is complete'
            }
        }
    }
}