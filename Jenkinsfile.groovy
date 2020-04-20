pipeline{
  agent any
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
        script{
          echo "Deployment ${WORKSPACE}"
          def TODAY = new Date()
          println("${TODAY}")
          echo "BUILD_ID ${BUILD_ID}"
          echo "JOB_NAME ${JOB_NAME}"
          echo "BUILD_NUMBER ${BUILD_NUMBER}"
          def appID = (env.BUILD_ID==null)? TODAY : env.BUILD_ID
          def appTag = env.JOB_NAME
          def appVer = (env.BUILD_NUMBER==null)?"LOCAL BUILD" : env.BUILD_NUMBER
          def folder = fileExists("${WORKSPACE}/tmp")
          if(folder){
            dir ("${WORKSPACE}/tmp") {
              deleteDir()
            }
          }
           dir("${WORKSPACE}/tmp"){
              writeFile file : "version.txt",text : "id :${appID}\ntag:${appTag}\nversion:${appVer}\n"
            }
          sh "ls -l ${WORKSPACE}/tmp/version.txt"
          sh "cat ${WORKSPACE}/tmp/version.txt"
        }
      }
    }
  }
}
