def call(Map args) {
	app = docker.build("${args.remote}/${args.project}/${args.imageName}:${args.tag}", "${args.otherargs} --file ${args.filePath} .")
	docker.withRegistry("${GCR_URL}", "${GOOGLE_PROJECT_CRED_ID}") {
		app.push()
		app.push("latest")
	}
	sh "docker rmi ${args.remote}/${args.project}/${args.imageName}:${args.tag}"
	echo "Finished creation of docker images and pushed to GCR" 
}