import jenkins.model.*
jenkins = Jenkins.instance

def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {

			git branch: pipelineParams.branch , credentialsId: pipelineParams.credentialsId , url: pipelineParams.scmUrl

                }
            }
}
}
}

