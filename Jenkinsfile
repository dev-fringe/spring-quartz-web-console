pipeline {
  agent none

stages {
stage("Build") {
steps {
echo "Building.."
  bat "mvn clean install -Dlicense.skip=true"
}
}
stage("Test") {
steps {
echo "Testing.."
}
}
stage("Deploy") {
steps {
echo "Deploying...."
}
}
}

}
