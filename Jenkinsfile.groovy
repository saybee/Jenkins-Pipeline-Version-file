pipeline{
  agent any
  
  environment{
    WORKSPACE = '/Users/vedant/Documents'
    
  }
  stages{
    stage('Compile stage'){
      steps{
        echo 'compiling'
      }
    }
    stage('Testing'){
      steps{
        echo 'Testing'
      }
    }
    stage('Deploy') {
      steps{
        echo "Deployment ${WORKSPACE}"
      }
    }
  }
}
