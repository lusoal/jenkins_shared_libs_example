#!groovy
@Library('my_lib') _

gitCloneExample {
    FOLDER_NAME = "JenkinsShared"
    REPO_URL = "https://github.com/lusoal/jenkins_shared_libs_example.git"
    REPO_NAME = "jenkins_shared_libs_example"
    BRANCH = "master"
}