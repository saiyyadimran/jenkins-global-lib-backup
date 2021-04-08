def call(Map args) {
	checkout([
        $class: 'GitSCM',
        branches: [
			[name:  args.branch ]
		],
        userRemoteConfigs: [
			[
				credentialsId: args.credentialsId,
				url: args.url
			]
		]
    ])
}