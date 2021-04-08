import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.Domain;
import org.jenkinsci.plugins.plaincredentials.impl.FileCredentialsImpl;
import groovy.json.JsonSlurperClassic

def fileName = "terraform-key.json"

SystemCredentialsProvider.getInstance().getCredentials().stream().
  filter { cred -> cred instanceof FileCredentialsImpl }.
  map { fileCred -> (FileCredentialsImpl) fileCred }.
  filter { fileCred -> fileName.equals( fileCred.getFileName() ) }.
  forEach { fileCred -> 
    String s = new String( fileCred.getSecretBytes().getPlainData() )
    File file = new File("/tmp/terraform-key.json")
    file.write s
  }
  "/google-cloud-sdk/bin/gcloud auth  activate-service-account  --key-file=/tmp/terraform-key.json".execute()
  def auth_token = "/google-cloud-sdk/bin/gcloud auth print-access-token".execute().text.replaceAll("\r\n", "")
// get specific image tags as JSON with all information about each tag, including creation timestamp
def url = "curl -s -u _token:${auth_token} https://us.gcr.io/v2/readydevops/python/tags/list?n=10"
def gcr_tags_json = url.execute().text.replaceAll("\r\n", "")

// unwrap JSON to Groovy object
def data = new JsonSlurperClassic().parseText(gcr_tags_json)
// prepare an empty hash map to store sorted images later, the "key" will be a timestamp
def tags_by_date = [:]

// let's sort the images hash map by creation date?
def timestamps = data.manifest.collect{ it.value.timeCreatedMs }.sort()
data.manifest.each{ tags_by_date[it.value.timeCreatedMs] = it.value.tag[0] }


// remember we always must return a List in order for Jenkins to pick up the result
def sorted_tags = []
// simply put the image names into a List, but now it will be in strict order by timestamp (keys)
for(timestamp in timestamps){
    sorted_tags.push(tags_by_date[timestamp])
}

return(sorted_tags)