#!groovy
def call(body) {
    //Parser Configuration Received from Pipeline
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    //Instanciate Objects from Libs
    def git = new libs.git.Git()

    pipeline {
        agent any

        environment {
            MY_ENV_VAR = "Test Environment"
        }

        stages {
            stage('Clone APP Repository') { 
                steps {
                    script {
                        dir("${FOLDER_NAME}"){
                            appRepoPwd = git.cloneAndCheckout("${REPO_URL}", "${REPO_NAME}", "${BRANCH}")
                        }
                    }
                }
            }
            post { 
                always { 
                    cleanWs()
                }
            }    
        }
    }
}