#!groovy
package libs;

def readResource(resourceName) {
    return libraryResource(resourceName)
}

def readJSONResource(resourceFile) {
    return readJSON(text: readResource(resourceName))
}

def readYAMLResource(resourceFile) {
    return readYaml(text: readResource(resourceFile))
}


return this
