#!/usr/bin/env groovy
def call(Map args) {
	withCredentials([file(credentialsId: env.GOOGLE_PROJECT_CRED_ID, variable: 'svcacct')]) {
		sh "gcloud auth activate-service-account --key-file=${svcacct}"
		pull_images = sh(returnStdout: true, script: "gcloud compute images list --project=env.GOOGLE_PROJECT_CRED_ID")
		echo pull_images
	}
}