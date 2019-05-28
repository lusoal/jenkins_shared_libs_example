#!groovy
package libs.git;

def String cloneAndCheckout(String REPO, String REPO_NAME, String BRANCH){
    script {
        sh "git clone --depth 1 ${REPO} && cd ${REPO_NAME} && git checkout ${BRANCH}"
        PWD = sh (
            script:  "cd ${REPO_NAME} && pwd",
            returnStdout: true
        ).trim()
        return PWD
    }
}

def String getShortCommitHash(String REPO_PWD) {
    script {
        shortCommit = sh (
            script:  "cd ${REPO_PWD} && git log -n 1 --pretty=format:'%h'",
            returnStdout: true
        ).trim()
        return shortCommit
    }
}

def void gitCloneBranchPR(gitCredentials, gitRepo, repoName) {
    checkout([$class: 'GitSCM',
                branches: [[name: '${sha1}']],
                doGenerateSubmoduleConfigurations: false,
                extensions: [],
                gitTool: 'Default',
                submoduleCfg: [],
                userRemoteConfigs: [[refspec: '+refs/heads/*:refs/remotes/origin/* +refs/pull/*:refs/remotes/origin/pr/*', url: "${gitRepo}"]]])
}

return this