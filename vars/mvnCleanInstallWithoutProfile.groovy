def call(Map args) {
	sh """
	pwd
	cd `cat /tmp/filepath.txt`
	pwd
	mvn -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8 -Dmaven.test.skip=${args.skipTest} clean install
	"""
}