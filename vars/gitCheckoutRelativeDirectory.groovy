def call(Map args) {
	checkout([
        $class: 'GitSCM',
        branches: [
			[name:  args.branch ]
		],
		extensions: [
			[$class: 'RelativeTargetDirectory', relativeTargetDir: args.relativeDirName]
		], 
        userRemoteConfigs: [
			[
				credentialsId: args.credentialsId,
				url: args.url
			]
		]
    ])
}