pipeline {
  agent any
  stages {
    stage('Java Build') {
      parallel {
        stage('Java Build') {
          steps {
            echo 'Building..'
            bat 'mvn clean install -Dmaven.test.skip=true'
          }
        }

        stage('Node Build') {
          steps {
            bat 'ant build.xml dist'
          }
        }

      }
    }

    stage('Test') {
      steps {
        echo 'Testing..'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploying....'
      }
    }

  }
}