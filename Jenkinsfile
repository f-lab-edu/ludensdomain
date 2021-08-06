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
                    sh 'mvn surefire:test'
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Deploy') {
            when {
                  allOf {
                    expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
                    branch 'develop'
                  }
              }
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: "ludens-deploy",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "target/*.jar",
                                    removePrefix: "target",
                                    remoteDirectory: "/",
                                    execCommand: "sh /scripts/ludens-deploy.sh"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}