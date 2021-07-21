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
                    sh 'mvn clean package -DskipITs=true -DskipTests=true'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Unit Test') {
            steps {
                script {
                    sh 'mvn -Dspring.profiles.active=dev -DskipITs=true surefire:test'
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                echo 'deployment step'
            }
//             steps([$class: 'BapSshPromotionPublisherPlugin']) {
//                 continueOnError: false, failOnError: true,
//                 publishers: [
//                     sshPublisherDesc(
//                         configName: "ludens-deploy",
//                         verbose: true,
//                         transfers: [
//                             sshTransfer(
//                                 sourceFiles: "target/*.jar",
//                                 removePrefix: "target",
//                                 remoteDirectory: "/",
//                                 execCommand: "sh /root/scripts/ludens-deploy.sh"
//                             )
//                         ]
//                     )
//                 ]
//             }
        }
    }
}