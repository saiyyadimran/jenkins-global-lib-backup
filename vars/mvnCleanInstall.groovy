def call(Map args) {
	def mvnHome = tool 'Maven'
	sh "cd ${WORKSPACE}"
	sh "'${mvnHome}/bin/mvn' -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8 -Dmaven.test.skip=${args.skipTest} clean install -P ${args.profile}" 
}