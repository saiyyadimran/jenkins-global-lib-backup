def call(Map pipelineParams) {
    pipeline {
        agent any
        stages {
            stage('Create-Docker-Images') {
                steps {
		sh "cp $WORKSPACE/docker/*.* ."
        def customImage = docker.build("${pipelineParams.gcrurl}/jenkins:${BUILD_NUMBER}", "--no-cache=true --force-rm --file docker/Dockerfile .")
        customImage.push()
        customImage.push('latest')
        sh "docker rmi ${pipelineParams.gcrurl}/jenkins:${BUILD_NUMBER}"
    echo "Finished creation of docker images of all tools and pushed to Google cloud Repo"

                }
            }
}
}
	}
