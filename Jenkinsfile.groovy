pipeline{
  agent any
  stages{
    stage('Compile stage'){
      echo 'compiling'
    }
    stage('Testing'){
      echo 'Testing'
    }
    stage('Deploy') {
      echo 'Deployment'
    }
  }
}
