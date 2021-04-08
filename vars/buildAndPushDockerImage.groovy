def call(Map args) {
	sh """
		pwd
		cd `cat /tmp/filepath.txt`
		ls
		cd target
		ls
		cd `cat /tmp/filepath.txt`
		pwd
	"""
	app = docker.build("${args.remote}/${args.project}/${args.imageName}:${args.tag}", "${args.otherargs} --file ${args.filePath} ${args.contextPath}")
	echo "Image tagged"
	docker.withRegistry("${GCR_URL}", "gcr:${GOOGLE_PROJECT_CRED_ID}") {
		app.push()
		app.push("latest")
	}
	sh "docker rmi ${args.remote}/${args.project}/${args.imageName}:${args.tag}"
	//echo "Finished creation of docker images and pushed to GCR" 
}
