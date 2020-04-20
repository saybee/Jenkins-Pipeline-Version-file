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
          if(!fileExists("${WORKSPACE}/tmp")){
            dir("${WORKSPACE}/tmp"){
              writeFile file : "version.txt",text : "id :${appID}\ntag:${appTag}\nversion:${appVer}\n"
            }
          } else{
            sh "cd ${WORKSPACE}/tmp/"
            sh "rm -rf version.txt"
            writeFile file : "version.txt",text : "id :${appID}\ntag:${appTag}\nversion:${appVer}\n"
          }
          sh "ls -l ${WORKSPACE}/tmp/version.txt"
          sh "cat ${WORKSPACE}/tmp/version.txt"
        }
      }
    }
  }
}
