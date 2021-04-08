def call(Map args) {
	def mvnHome = tool 'Maven'
	sh "cd ${WORKSPACE}"
	withCredentials([usernamePassword(
            credentialsId: args.sonarCredentialsId,
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh "'${mvnHome}/bin/mvn' -e -B clean verify sonar:sonar -Dsonar.host.url=${args.sonarServerUrl} -Dsonar.login=$USER -Dsonar.password=$PASS -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8 -Dspring.profiles.active=${args.activeBuildProfile} -Dsonar.projectKey=${args.projectKey} -Dsonar.projectName=${args.projectName} -P ${args.projectProfile}"
    }
}