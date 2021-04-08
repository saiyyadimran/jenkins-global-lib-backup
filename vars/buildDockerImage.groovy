def call(Map args) {
sh """
echo "creating docker build"
cd `cat /tmp/filepath.txt`
"""
def customImage = docker.build("${args.repositoryURL}:${args.imageVersion}", "--no-cache=true --force-rm --file `cat /tmp/filepath.txt`/app/Dockerfile .")
customImage.push()
customImage.push('latest')
sh "docker rmi ${args.repositoryURL}:${args.imageVersion}"
echo "Finished creation of docker images and pushed to GCR" 
}
