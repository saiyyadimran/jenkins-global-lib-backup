def call(Map args) {
    podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ]) {

    node(POD_LABEL) {
        stage('Get a Maven project') {
            container('maven') {
                stage('stage 2') {
                    gitCheckout(
						branch: "master",
						url: "https://github.com/jojanmathew/jenkins-global-lib/",
						credentialsId: "GIT"
                        )
					}
                stage('stage 3') {
                    sh """
                    mvn -v
                    pwd
					ls -ltr
					cd samplemavenproject
					ls -ltr
					pwd
					pwd > /tmp/filepath.txt
                    """
                    mvnCleanInstallWithoutProfile(
					skipTest: true
					)
                }
            }
        }
    }
  }
}